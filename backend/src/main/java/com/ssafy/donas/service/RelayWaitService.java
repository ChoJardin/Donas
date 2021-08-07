package com.ssafy.donas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.domain.quest.RelayWait;
import com.ssafy.donas.repository.quest.RelayWaitRepo;
import java.time.*;

@Service
@Transactional
public class RelayWaitService {
	
	@Autowired
	RelayWaitRepo relayWaitRepo;

	public void addWaitList(Quest relay, List<Long> nextUsers) {
		int idx = 1;
		for(Long user : nextUsers) {
			RelayWait rw = new RelayWait((Relay)relay, idx++, user);
			relayWaitRepo.save(rw);
		}
	}
	
	public void updateDeadline(Quest relay, int waitRank, LocalDateTime time) {
		RelayWait rw = relayWaitRepo.findByRelay(relay);
		rw.setDeadline(time.plusDays(1));
	}
	
	
}
