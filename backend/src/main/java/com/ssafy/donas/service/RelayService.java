package com.ssafy.donas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.repository.quest.RelayRepo;

@Service
@Transactional
public class RelayService {
	
	@Autowired
	RelayRepo relayRepo;

	public void updateUserOrder(long questId, int relayOrder) {
		Relay relay = relayRepo.getById(questId);
		relay.setOrder(relayOrder);
	}
	
	public Relay getById(long id) {
		return relayRepo.getById(id);
	}
	
}
