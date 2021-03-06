package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Message;
import com.ssafy.donas.domain.MessageInfo;
import com.ssafy.donas.domain.MessageRoom;
import com.ssafy.donas.domain.MsgInfo;
import com.ssafy.donas.repository.MessageRoomRepo;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.AlarmRepo;
import com.ssafy.donas.repository.MessageRepo;
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
	
	// 채팅방 번호
	
	public long roomId(User user1, User user2) {
		MessageRoom room = messageRoomRepo.findMessageRoomByUser1AndUser2(user1, user2);
		if(room==null) {
			room = messageRoomRepo.findMessageRoomByUser1AndUser2(user2, user1);
			if(room==null)
				return 0;
		}
		return room.getId();
	}
	
	// 채팅 보내기
	public boolean sendMessage(long sendId, long receivedId, String content, LocalDateTime time) {
		User sendUser = userRepo.getById(sendId);
		User receivedUser = userRepo.getById(receivedId);
		MessageRoom room1 = messageRoomRepo.findMessageRoomByUser1AndUser2(sendUser, receivedUser);
		if(room1==null)	{			
			room1 = messageRoomRepo.findMessageRoomByUser1AndUser2(receivedUser,sendUser);		
			// 메세지 보낸 적있는지 확인
			if(room1==null) {
				// 새로운 메세지방 만들기
				room1 = messageRoomRepo.save(new MessageRoom(sendUser,receivedUser));

			}
		}		
		if(messageRepo.save(new Message(content,0,sendUser,receivedUser,time,room1))==null)
			return false;
		pushService.searchReceivedUser(receivedUser, sendUser.getNickname()+" : "+content, time);
		return true;
	}
		
	// 채팅방 리스트
	public List<MsgInfo> messageList(long userId){
		User user = userRepo.getById(userId);
		List<MessageRoom> list = messageRoomRepo.findMessageRoomByUser1OrUser2(user, user);
		// 채팅방 목록
		// 채팅방 id, 상대방 id, 상대방 닉네임, 최근 메세지
		List<MsgInfo> msgInfo = new ArrayList<MsgInfo>();
		for(MessageRoom msR : list) {
			List<Message> msg = msR.getMsg();		
			Collections.sort(msg, new Comparator<Message>() {
				@Override
				public int compare(Message o1, Message o2) {
					if(o2.getTime().isAfter(o1.getTime()))
						return 1;
					return -1;
				}			
			});
			int own = msg.get(0).getSendUser().equals(user) ? 1: 0;
			if(msR.getUser1().equals(user))
				msgInfo.add(new MsgInfo(msR.getId(),msg.get(0).getConfirm(),msR.getUser2().getId(),msR.getUser2().getNickname(),msR.getUser2().getPicture(),msg.get(0).getContent(),msg.get(0).getTime(),own));
			else
				msgInfo.add(new MsgInfo(msR.getId(),msg.get(0).getConfirm(),msR.getUser1().getId(),msR.getUser1().getNickname(),msR.getUser1().getPicture(),msg.get(0).getContent(),msg.get(0).getTime(),own));
		}
		
		Collections.sort(msgInfo,new Comparator<MsgInfo>() {

			@Override
			public int compare(MsgInfo o1, MsgInfo o2) {
				if(o2.getLastTime().isAfter(o1.getLastTime())) {
					return 1;
				}
				return -1;
			}			
		});
	return msgInfo;
		
	}
	
	// 채팅 내용
	public List<MessageInfo> showMessage(User user, User other,long roomId){
		List<MessageInfo> messages = new ArrayList<MessageInfo>();
		MessageRoom room1 = messageRoomRepo.getById(roomId);
		for(Message msg : room1.getMsg()) {
			if(msg.getSendUser().equals(other) && msg.getConfirm()==0)
				msg.setConfirm(1);
			if(msg.getSendUser().equals(user))
				messages.add(new MessageInfo(msg.getId(),msg.getContent(),1,msg.getTime()));
			else
				messages.add(new MessageInfo(msg.getId(),msg.getContent(),0,msg.getTime()));
		}
		
		return messages;
		
	}


}
