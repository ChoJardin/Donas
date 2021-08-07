package com.ssafy.donas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.domain.quest.RelayWait;
import com.ssafy.donas.repository.RelayWaitRepo;

@Service
@Transactional
public class RelayWaitService {
	
	@Autowired
	RelayWaitRepo relayWaitRepo;

	public void addWaitList(Relay relay, List<Long> nextUsers) {
		int idx = 1;
		for(Long user : nextUsers) {
			RelayWait rw = new RelayWait(relay, idx++, user);
			relayWaitRepo.save(rw);
		}
	}
	
	
}
