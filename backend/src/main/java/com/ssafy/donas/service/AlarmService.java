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
	
	public boolean checkAlarm(long id) {
		Optional<Alarm> alarm = alarmRepo.findAlarmById(id);
		if(alarm.isEmpty())
			return false;
		return true;
	}
	
	public boolean addAlarm(User receivedUser, String sendName,long articleId,String content, LocalDateTime sendTIme) {
		Optional<User> user = userRepo.findById(receivedUser.getId());
		if(user==null)
			return false;
		Alarm alarm = new Alarm(receivedUser,sendName,articleId,content,sendTIme,1);
		alarmRepo.save(alarm);
		user.ifPresent(selectUser->{
			pushService.searchReceivedUser(selectUser,content,sendTIme);
		});
		return true;		
	}
	
	public List<Alarm> getAlarms(User user){
		return alarmRepo.findAlarmByUser(user);
	}
	
	public void update(long id,int confirm) {
		Alarm alarm = alarmRepo.findAlarmById(id).get();
		if(alarm.getConfirm()==1)
			alarm.setConfirm(confirm);
	}

}
