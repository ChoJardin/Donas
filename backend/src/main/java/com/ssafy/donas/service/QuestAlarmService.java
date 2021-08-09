package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.QuestAlarm;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.repository.QuestAlarmRepo;
import com.ssafy.donas.repository.UserRepo;
import com.ssafy.donas.repository.quest.QuestRepo;

@Service
@Transactional
public class QuestAlarmService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	QuestRepo questRepo;
	
	@Autowired
	QuestAlarmRepo qaRepo;
	
	@Autowired
	PushService pushService;
	
	public boolean checkQuestAlarm(long id) {
		Optional<QuestAlarm> alarm = qaRepo.findQuestAlarmById(id);
		if(alarm.isEmpty())
			return false;
		return true;
	}
	
	public boolean addQuestAlarm(long receiverId, Quest quest, String sendName, String contents, LocalDateTime sendTime) {
		Optional<User> receiver = userRepo.findById(receiverId);
		if(receiver.isEmpty())
			return false;
		
		QuestAlarm questAlarm = new QuestAlarm(receiver.get(), quest, sendName, contents, sendTime);
		qaRepo.save(questAlarm);
		
		receiver.ifPresent(selectUser->{
			pushService.searchReceivedUser(selectUser, contents, sendTime);
		});
		
		return true;		
	}
	
	public List<QuestAlarm> getAlarms(User user){
		return qaRepo.findQuestAlarmByUser(user);
	}
	
}
