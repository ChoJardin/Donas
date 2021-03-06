package com.ssafy.donas.repository.quest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.domain.quest.RelayWait;

public interface RelayWaitRepo extends JpaRepository<RelayWait, Long>{
	RelayWait findByRelayAndUser(Quest relay, User user);
	void deleteByRelayAndRelayOrder(Quest relay, int order);
	int countByRelay(Relay relay);
}
