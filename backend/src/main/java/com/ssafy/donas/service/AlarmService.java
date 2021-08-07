package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Alarm;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.AlarmRepo;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class AlarmService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AlarmRepo alarmRepo;
	
	@Autowired
	PushService pushService;
	
	public boolean addAlarm(User receivedUser, String content, LocalDateTime sendTIme) {
		Optional<User> user = userRepo.findById(receivedUser.getId());
		if(user==null)
			return false;
		Alarm alarm = new Alarm(receivedUser,content,sendTIme,1);
		alarmRepo.save(alarm);
		user.ifPresent(selectUser->{
			pushService.searchReceivedUser(selectUser,content,sendTIme);
		});
		return true;		
	}
	
	public List<Alarm> getAlarms(User user){
		return alarmRepo.findAlarmByUser(user);
	}
	
	public void update(User receiveUser) {
		List<Alarm> alarms = alarmRepo.findAlarmByUser(receiveUser);
		for(Alarm alarm : alarms) {
			alarm.setConfirm(0);
		}
	}

}
