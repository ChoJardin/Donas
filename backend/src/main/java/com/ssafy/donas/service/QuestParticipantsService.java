package com.ssafy.donas.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestParticipants;
import com.ssafy.donas.repository.QuestParticipantsRepo;
import com.ssafy.donas.repository.UserRepo;
import com.ssafy.donas.repository.quest.QuestRepo;

@Service
@Transactional
public class QuestParticipantsService {
	
	@Autowired
	QuestParticipantsRepo questParticipantsRepo;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	QuestRepo questRepo;
	
	public List<QuestParticipants> participantsList(Quest quest){
		List<QuestParticipants> userList = questParticipantsRepo.findQuestParticipantsByQuest(quest);
		return userList;
	}
	public User participantByQuest(Quest quest,User user) {
		
		List<QuestParticipants> participants = questParticipantsRepo.findQuestParticipantsByQuest(quest);
		for(QuestParticipants qp : participants) {
			if(qp.getUser().getId()==user.getId()) {
				return qp.getUser();
			}
		}
		return null;
	}
	
	public void addParticipants(User adminUser, List<User> participants, Quest groupQuest) {
		QuestParticipants admin = new QuestParticipants(adminUser, groupQuest);
		questParticipantsRepo.save(admin);
		for (User p : participants) {
			QuestParticipants qp = new QuestParticipants(p, groupQuest);
			questParticipantsRepo.save(qp);
		}
	}	
	
	public void addParticipant(long userId, long questId) {
		QuestParticipants qp = new QuestParticipants(userRepo.getById(userId), questRepo.getById(questId));
		questParticipantsRepo.save(qp);
		User user = userRepo.getById(userId);
		user.setQuestCnt(user.getQuestCnt()+1);
	}
	
	public int getQuestCntById(long userId, Date time) {
		List<QuestParticipants> questParticipants = questParticipantsRepo.findQuestParticipantsByUser(userRepo.getById(userId));
		int questCnt = 0;
		for(QuestParticipants qp : questParticipants) {
			 if(questRepo.getById(qp.getQuest().getId()).getStartAt().before(time) &&questRepo.getById(qp.getQuest().getId()).getFinishAt().after(time))
				 questCnt++;
		 }
		return questCnt;
	}	
}
