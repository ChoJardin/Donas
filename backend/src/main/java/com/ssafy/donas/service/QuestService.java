package com.ssafy.donas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.Relay;
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
	
	public List<Quest> getQuestsByUserId(long UserId){
		return questRepo.findQuestByUserId(UserId);
	}
	
	public List<Relay> getRealysByUserId(long UserId){
		return relayRepo.findRelayByUserId(UserId);
	}
}
