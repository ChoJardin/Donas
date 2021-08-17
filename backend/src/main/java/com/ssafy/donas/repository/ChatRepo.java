package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Chat;
import com.ssafy.donas.domain.User;

public interface ChatRepo extends JpaRepository<Chat, Long>{
	
	List<Chat> findChatBySendIdandReceivedId(long sendId, long receivedId);

}
