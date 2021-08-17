package com.ssafy.donas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Chat;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.AlarmRepo;
import com.ssafy.donas.repository.ChatRepo;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class ChatService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AlarmRepo alarmRepo;
	
	@Autowired
	ChatRepo chatRepo;
	
	@Autowired
	PushService pushService;
	
	// 새로운 채팅방 들어가기
	// 원래 있으면 기존 채팅방으로 들어가기
	// 처음일 때 새로 방 만들기
	public List<Chat> enterChattingRoom(User sendUser, User receivedUser){
		List<Chat> chatting = null;
//		chatRepo.fi
		return chatting;		
	}
	
		
	// 채팅방 리스트
	
	// 채팅 보내기
	
	
	//

}
