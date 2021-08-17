package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestAlarm;
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
	QuestAlarmRepo questAlarmRepo;
	
	@Autowired
	PushService pushService;
	
	public boolean checkQuestAlarm(long id) {
		Optional<QuestAlarm> alarm = questAlarmRepo.findQuestAlarmById(id);
		if(alarm.isEmpty())
			return false;
		return true;
	}
	
	public boolean addQuestAlarm(long receiverId, Quest quest, String sendName, String contents, LocalDateTime sendTime) {
		Optional<User> receiver = userRepo.findById(receiverId);
		if(receiver.isEmpty())
			return false;
		
		QuestAlarm questAlarm = new QuestAlarm(receiver.get(), quest, sendName, contents, sendTime);
		questAlarmRepo.save(questAlarm);
		
		receiver.ifPresent(selectUser->{
			pushService.searchReceivedUser(selectUser, contents, sendTime);
		});
		
		return true;		
	}
	// 퀘스트 알림 리스트 가져오기
	public List<QuestAlarm> getAlarms(User user){
		List<QuestAlarm> questAlarms = questAlarmRepo.findQuestAlarmByUser(user);
		Collections.sort(questAlarms,new Comparator<QuestAlarm>() {

			@Override
			public int compare(QuestAlarm o1, QuestAlarm o2) {
				if(o2.getSendTime().isAfter(o1.getSendTime()))
					return 1;
				return -1;
			}			
		});
		return questAlarms;
	}

	public void updateConfirm(long alarmId, int confirm) {
		QuestAlarm questAlarm = questAlarmRepo.getById(alarmId);
		questAlarm.setConfirm(confirm);
		
	}
	
	public QuestAlarm getAlarm(long alarmId) {
		return questAlarmRepo.getById(alarmId);
	}
	
	/*
	 * Relay 퀘스트 : 이틀 동안 요청 미응답 시 다음 주자에게 알림 
	 * */
	@Scheduled(cron = "0 0 0 * * *")
	public void removeNotReply() {
		// 미응답인 퀘스트알림 받아오기
		List<QuestAlarm> questAlarms = questAlarmRepo.findAllByConfirm(0);
		
		for(QuestAlarm qa : questAlarms) {
			String questType = qa.getQuest().getType();
			// 그룹 퀘스트
			if("G".equals(questType)) {
				
			}
			// 릴레이 퀘스트
			else if("R".equals(questType)) {
				
			}
		}
	}

}
