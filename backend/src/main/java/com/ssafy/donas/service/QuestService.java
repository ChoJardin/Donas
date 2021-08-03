package com.ssafy.donas.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.quest.Group;
import com.ssafy.donas.domain.quest.Personal;
import com.ssafy.donas.domain.quest.Quest;
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
	GroupRepo groupRepo;

	@Autowired
	PersonalRepo personalRepo;

	@Autowired
	RelayRepo relayRepo;

	public boolean checkQuest(long id) {
		Optional<Quest> quest = questRepo.findById(id);
		if (quest.isEmpty())
			return false;
		return true;
	}

	public Quest getQuestById(long id) {
		if (checkQuest(id))
			return null;
		return questRepo.findById(id).get();
	}

//	public List<Relay> getRealysByUserId(long UserId){
//		return relayRepo.findRelayByUserId(UserId);
//	}

	public void addPersonalQuest(long userId, String title, String description, Date startAt,
			Date finishAt) {
		Personal quest = new Personal("Personal", title, description, startAt, finishAt);
		personalRepo.save(quest);
		//엔티티에 리스트 mapping 할거면 리스트에도 추가. 
	}
	
	public void addGroupQuest(long userId, String title, String description, Date startAt,
			Date finishAt) {
		Group quest = new Group("Group", title, description, startAt, finishAt, 0);
		groupRepo.save(quest);
		//엔티티에 리스트 mapping 할거면 리스트에도 추가. 
	}
}
