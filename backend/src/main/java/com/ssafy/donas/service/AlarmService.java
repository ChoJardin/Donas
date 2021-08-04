package com.ssafy.donas.service;

import java.time.LocalDateTime;
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
		user.ifPresent(selectUser->{
			System.out.println(selectUser.getId());
			Alarm alarm = new Alarm(selectUser,content,sendTIme);
			System.out.println(alarm.getContents());
			System.out.println(alarm.getUser().getId());
			alarmRepo.save(alarm);
			
			pushService.searchReceivedUser(selectUser,content,sendTIme);
			
		});
		return true;		
	}

}
