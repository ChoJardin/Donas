package com.ssafy.donas.service;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.repository.quest.GroupRepo;
import com.ssafy.donas.repository.quest.PersonalRepo;
import com.ssafy.donas.repository.quest.QuestRepo;
import com.ssafy.donas.repository.quest.RelayRepo;
<<<<<<< HEAD
 
=======

>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
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
	
<<<<<<< HEAD
=======
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
	
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
	public List<Quest> getQuestsByUserId(long UserId){
		return questRepo.findQuestByUserId(UserId);
	}
	
	public List<Relay> getRealysByUserId(long UserId){
		return relayRepo.findRelayByUserId(UserId);
	}
}
