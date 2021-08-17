package com.ssafy.donas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.MessageRoom;
import com.ssafy.donas.domain.User;

public interface MessageRoomRepo extends JpaRepository<MessageRoom, Long>{
	MessageRoom findMessageRoomByUser1AndUser2(User user1, User user2);
	List<MessageRoom> findMessageRoomByUser1OrUser2(User user1, User user2 );
}
