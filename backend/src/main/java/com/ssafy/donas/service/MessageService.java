package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Message;
import com.ssafy.donas.domain.Messageroom;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.AlarmRepo;
import com.ssafy.donas.repository.MessageRepo;
import com.ssafy.donas.repository.MessageRoomRepo;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class MessageService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AlarmRepo alarmRepo;
	
	@Autowired
	MessageRepo messageRepo;
	
	@Autowired
	MessageRoomRepo messageRoomRepo;
	
	@Autowired
	PushService pushService;
	
	// 새로운 채팅방 들어가기
	// 원래 있으면 기존 채팅방으로 들어가기
	// 처음일 때 새로 방 만들기
	public List<Message> enterChattingRoom(long sendId, long receivedId){
		User sendUser = userRepo.getById(sendId);
		User receivedUser = userRepo.getById(receivedId);
		
		List<Message> messages = messageRepo.findChatBySendUserAndReceivedUser(sendUser, receivedUser);

		return messages;		
	}
	// 채팅 보내기
	public boolean sendChat(long sendId, long receivedId, String content, LocalDateTime time) {
		User sendUser = userRepo.getById(sendId);
		User receivedUser = userRepo.getById(receivedId);
		Messageroom room1 = messageRoomRepo.findMessageRoomByUser1AndUser2(sendUser, receivedUser);
		if(room1==null)	{			
			room1 = messageRoomRepo.findMessageRoomByUser1AndUser2(receivedUser,sendUser);		
			// 메세지 보낸 적있는지 확인
			if(room1==null) {
				// 새로운 메세지방 만들기
				messageRoomRepo.save(new Messageroom(sendUser,receivedUser));
			}
		}		
		if(messageRepo.save(new Message(content,sendUser,receivedUser,time,room1))==null)
			return false;
		return true;
	}
		
	// 채팅방 리스트
	
	
	
	//

}
