package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Group;
import com.ssafy.donas.domain.quest.Personal;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestInfo;
import com.ssafy.donas.domain.quest.QuestMainInfo;
import com.ssafy.donas.domain.quest.QuestParticipants;
import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.repository.QuestParticipantsRepo;
import com.ssafy.donas.repository.UserRepo;
import com.ssafy.donas.repository.quest.GroupRepo;
import com.ssafy.donas.repository.quest.PersonalRepo;
import com.ssafy.donas.repository.quest.QuestRepo;
import com.ssafy.donas.repository.quest.RelayRepo;

@Service
@Transactional
public class QuestService {
	@Autowired
	QuestRepo questRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	GroupRepo groupRepo;

	@Autowired
	PersonalRepo personalRepo;

	@Autowired
	RelayRepo relayRepo;

	@Autowired
	QuestParticipantsRepo qpRepo;
		
	public long getAllQuestCnt() {
		return questRepo.count();			
	}
	

	public long getProgressQuestCnt(Date time) {
		List<Quest> quests = questRepo.findAll();
		List<Quest> progressQuest = new ArrayList<Quest>();
		for(Quest q : quests) {
			if(q.getStartAt()==null || q.getFinishAt()==null)
				return -1;
			if(q.getStartAt().equals(time) ||q.getStartAt().after(time) && q.getFinishAt().equals(time) || q.getFinishAt().before(time))
				progressQuest.add(q);
		}
		return quests.size();
	}
	// 완료 퀘스트 성공/실패 여부
	public void checkQuestSuccess(long userId, Date time) {
		// 현재 기준 내가 속한 완료된 퀘스트 중 성공/실패여부 확인 안한 퀘스트 
		List<QuestParticipants> qp = qpRepo.findQuestParticipantsByUserAndSuccess(userRepo.getById(userId),0);
		if(qp.size()==0)
			return;
		for(QuestParticipants q : qp) {
			Quest quest = q.getQuest();
			// 확인 안된 완료 퀘스트 찾기
			double cnt = 0;
			if(quest.getFinishAt().before(time)) {
				List<Article> articles = quest.getArticles();
				System.out.println(articles.size());
				if(articles.size()==0)
					continue;
				// 나의 게시글 개수
				for(Article ac : articles) {
					if(ac.getUser().getId()==userId)
						cnt++;
				}
			}
			// 성공 최소 개수 이상 게시물 올렸나 확인
			double percent = (cnt*100)/(double)quest.getMinArticleCount();

			if(percent>=90)
				q.setSuccess(1);
			else
				q.setSuccess(2);
		}
	}	

	public boolean checkQuest(long id) {
		Optional<Quest> quest = questRepo.findById(id);
		if (quest.isEmpty())
			return false;
		return true;
	}

	public boolean checkPersonal(long id) {
		Optional<Personal> personal = personalRepo.findById(id);
		if (personal.isEmpty())
			return false;
		return true;
	}

	public boolean checkGroup(long id) {
		Optional<Group> group = groupRepo.findById(id);
		if (group.isEmpty())
			return false;
		return true;
	}
	
	public boolean checkRelay(long id) {
		Optional<Relay> relay = relayRepo.findById(id);
		if (relay.isEmpty())
			return false;
		return true;
	}

	public Quest getQuestById(long id) {
		if (!checkQuest(id))
			return null;

		return questRepo.findById(id).get();
	}

	public Personal getPersonalById(long id) {
		if (!checkPersonal(id))
			return null;
		return personalRepo.findById(id).get();
	}

	public Group getGroupById(long id) {
		if (!checkGroup(id))
			return null;
		return groupRepo.findById(id).get();
	}

	public List<QuestInfo> getQuestInfoByUserId(long id) {
		User user = userRepo.getById(id);
		List<QuestParticipants> questSummaries = user.getMyQuests();

		List<QuestInfo> quests = new ArrayList<>();
		for (QuestParticipants qs : questSummaries) {
			Quest q = qs.getQuest(); 
			quests.add(new QuestInfo(q.getId(), q.getType(), q.getTitle(), q.getDescription(), q.getPicture(),
					q.getStartAt(), q.getFinishAt(), q.getMileage(), q.getPercent()));
		}

		return quests;
	}

	public long addPersonalQuest(String title, String description, Date startAt, Date finishAt,
			String picture, String certification, long mileage, int minArticleCount) {
		Personal quest = new Personal("P", title, description, startAt, finishAt, picture, certification, mileage,minArticleCount);
		personalRepo.save(quest);

		return quest.getId();
	}

	public Quest addGroupQuest(String title, String description, Date startAt, Date finishAt, String picture,
			String certification, long mileage, int userCnt, int minArticleCount) {
		Group quest = new Group("G", title, description, startAt, finishAt, picture, certification, mileage, userCnt,minArticleCount);
		groupRepo.save(quest);
		return quest;
	}

	public long addRelayQuest(String title, String description, Date startAt, Date finishAt, String picture,
			String certification, long mileage, int targetCnt) {
		Relay relay = new Relay("R", title, description, startAt, finishAt, picture, certification, mileage, 1, targetCnt,0);
		relayRepo.save(relay);

		return relay.getId();
	}

	public void updateQuest(long questId, String title, String description) {
		Quest quest = getQuestById(questId);
		quest.setTitle(title);
		quest.setDescription(description);
	}

	public boolean delete(long questId) {
		if (!checkQuest(questId))
			return false;

		String type = getQuestById(questId).getType();
		List<QuestParticipants> questParticipants = null;
		if (type.equals("P")) {
			if (!checkPersonal(questId))
				return false;
			questParticipants = personalRepo.getById(questId).getParticipants();
			personalRepo.deleteById(questId);
			
			personalRepo.flush();

		} else if (type.equals("G")) {
			if (!checkGroup(questId))
				return false;
			questParticipants = groupRepo.getById(questId).getParticipants();
			groupRepo.deleteById(questId);
			groupRepo.flush();

		} else {
			if (!checkRelay(questId))
				return false;			
			questParticipants = relayRepo.getById(questId).getParticipants();
			relayRepo.deleteById(questId);
			relayRepo.flush();
		}
		if(questParticipants!=null) {
			for(QuestParticipants qp : questParticipants) {
				User user = qp.getUser();
				user.setQuestCnt(user.getQuestCnt()-1);
			}
		}
		return true;
	}

	public void quitQuest(long questId, int success) {
		Quest quest = questRepo.getById(questId);
		quest.setSuccess(success);
	}
	
	public List<QuestMainInfo> getQuestList(String type){
		List<Quest> quests = questRepo.findTop10ByTypeOrderByIdDesc(type);
		if(quests.size()==0)
			return null;
		List<QuestMainInfo> questInfo = new ArrayList<QuestMainInfo>();
		for(Quest q : quests) {
			questInfo.add(new QuestMainInfo(q.getId(),q.getTitle(),q.getPicture(),q.getDescription()));
		}
		return questInfo;
	}

	public List<QuestInfo> findAll() {
		List<QuestInfo> quests = new ArrayList<>();
		for(Quest q : questRepo.findAll()) {
			quests.add(new QuestInfo(q.getId(), q.getType(), q.getTitle(), q.getDescription(), q.getPicture(), q.getStartAt(), q.getFinishAt(), q.getMileage(), q.getPercent()));
		}	
		return quests;
	}




}
