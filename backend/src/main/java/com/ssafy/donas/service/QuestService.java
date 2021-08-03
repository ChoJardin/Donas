package com.ssafy.donas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestInfo;
import com.ssafy.donas.domain.quest.QuestParticipants;
import com.ssafy.donas.repository.UserRepo;
import com.ssafy.donas.repository.quest.QuestRepo;

@Service
@Transactional
public class QuestService {
	@Autowired
	QuestRepo questRepo;
	
	@Autowired
	UserRepo userRepo;
	
	public boolean checkQuest(long id) {
		Optional<Quest> quest = questRepo.findById(id);
		if(quest.isEmpty())
			return false;
		return true;
	}
	
	public Quest getQuestById(long id) {
		if(checkQuest(id))
			return null;
		return questRepo.findById(id).get();
	}
	
	public List<QuestInfo> getQuestsByUserId(long id){
		User user = userRepo.getById(id);
		List<QuestParticipants> questSummaries = user.getMyQuests();

		List<QuestInfo> quests = new ArrayList<>();
		for (QuestParticipants qs : questSummaries) {
			Quest q = qs.getQuest();
			quests.add(new QuestInfo(q.getId(), q.getType(), q.getTitle(), q.getDescription(), q.getStartAt(), q.getFinishAt()));
		}

		return quests;
	}
	
}
