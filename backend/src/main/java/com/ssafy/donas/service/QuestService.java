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
					q.getStartAt(), q.getFinishAt(), q.getMileage()));
		}

		return quests;
	}

	public long addPersonalQuest(String title, String description, Date startAt, Date finishAt,
			String picture, String certification, long mileage) {
		Personal quest = new Personal("P", title, description, startAt, finishAt, picture, certification, mileage);
		personalRepo.save(quest);

		return quest.getId();
	}

	public Quest addGroupQuest(String title, String description, Date startAt, Date finishAt, String picture,
			String certification, long mileage) {
		Group quest = new Group("G", title, description, startAt, finishAt, 0, picture, certification, mileage);
		groupRepo.save(quest);

		return quest;
	}

	public long addRelayQuest(String title, String description, Date startAt, Date finishAt, String picture,
			String certification, long mileage) {
		Relay relay = new Relay("R", title, description, startAt, finishAt, picture, certification, mileage, 1);
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
		if (type.equals("P")) {
			if (!checkPersonal(questId))
				return false;

			personalRepo.deleteById(questId);
			personalRepo.flush();
			return true;
		} else if (type.equals("G")) {
			if (!checkGroup(questId))
				return false;

			groupRepo.deleteById(questId);
			groupRepo.flush();
			return true;
		} else {
			if (!checkRelay(questId))
				return false;
			
			relayRepo.deleteById(questId);
			relayRepo.flush();
			return true;
		}
	}

}
