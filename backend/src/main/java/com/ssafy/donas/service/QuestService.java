package com.ssafy.donas.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Group;
import com.ssafy.donas.domain.quest.Personal;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestInfo;
import com.ssafy.donas.domain.quest.QuestParticipants;
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
	
	public boolean checkQuest(long id) {
		Optional<Quest> quest = questRepo.findById(id);
		if(quest.isEmpty())
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
	
	public Quest getQuestById(long id) {
		if(!checkQuest(id))
			return null;
		
		return questRepo.findById(id).get();
	}
	
	public Personal getPersonalById(long id) {
		if(!checkPersonal(id))
			return null;
		return personalRepo.findById(id).get();
	}
	
	public Group getGroupById(long id) {
		if(!checkGroup(id))
			return null;
		return groupRepo.findById(id).get();
	}
	
	public List<QuestInfo> getQuestInfoByUserId(long id){
		User user = userRepo.getById(id);
		List<QuestParticipants> questSummaries = user.getMyQuests();

		List<QuestInfo> quests = new ArrayList<>();
		for (QuestParticipants qs : questSummaries) {
			Quest q = qs.getQuest();
			quests.add(new QuestInfo(q.getId(), q.getType(), q.getTitle(), q.getDescription(), q.getPicture(), q.getStartAt(), q.getFinishAt()));
		}

		return quests;
	}
	
//	public List<Personal> getPersonalsByUserId(long userId){
//		return personalRepo.findPersonalByUserId(userId);
//	}
	
	public void addPersonalQuest(long userId, String title, String description, Date startAt,
			Date finishAt) {
		Personal quest = new Personal("P", title, description, startAt, finishAt);
		System.out.println("뭐여");
		personalRepo.save(quest);
		System.out.println("뭐ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ여");
		
		// 유저의 참여중인 퀘스트 리스트 업데이트
		User user = userRepo.getById(userId);
		QuestParticipants qp = new QuestParticipants(user, quest);
		user.getMyQuests().add(qp);
	}
	
	public Quest addGroupQuest(String title, String description, Date startAt,
			Date finishAt) {
		Group quest = new Group("G", title, description, startAt, finishAt, 0);
		groupRepo.save(quest);
		
		return quest;
	}
	
	public void updateQuest(long questId, String title, String description) {
		Quest quest = getQuestById(questId);
		quest.setTitle(title);
		quest.setDescription(description);
	}
	
	public boolean delete(long questId) {
		if(!checkQuest(questId))
			return false;
		
		String type = getQuestById(questId).getType();
		if (type.equals("P")) {
			if(!checkPersonal(questId))
				return false;
			
			personalRepo.deleteById(questId);
			personalRepo.flush();
			return true;
		} else if (type.equals("G")) {
			if(!checkGroup(questId))
				return false;
			
			groupRepo.deleteById(questId);
			personalRepo.flush();
			return true;
		}
		else {
			// 릴레이 퀘스트 삭제 부분 만들어야 함
			return false;
		}
	}
	
}
