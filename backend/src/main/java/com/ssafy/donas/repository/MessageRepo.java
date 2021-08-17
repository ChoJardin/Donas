package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Message;
import com.ssafy.donas.domain.User;

public interface MessageRepo extends JpaRepository<Message, Long>{
	
	List<Message> findChatBySendUserAndReceivedUser(User sendUser, User receivedUser);

}
