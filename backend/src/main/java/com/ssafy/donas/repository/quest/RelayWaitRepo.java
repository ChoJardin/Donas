package com.ssafy.donas.repository.quest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.RelayWait;

public interface RelayWaitRepo extends JpaRepository<RelayWait, Long>{
	// 제일 첫 번째 가져오기!
	RelayWait findByRelay(Quest relay);
}
