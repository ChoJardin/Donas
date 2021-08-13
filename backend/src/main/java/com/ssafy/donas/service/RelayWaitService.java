package com.ssafy.donas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.domain.quest.RelayWait;
import com.ssafy.donas.repository.UserRepo;
import com.ssafy.donas.repository.quest.RelayWaitRepo;
import java.time.*;

@Service
@Transactional
public class RelayWaitService {
	
	@Autowired
	RelayWaitRepo relayWaitRepo;
	
	@Autowired
	UserRepo userRepo;
	
	public void addWaitList(Quest relay, List<Long> nextUsers, int order) {
		int idx = 1;
		for(Long userId : nextUsers) {
			User user = userRepo.getById(userId);
			RelayWait rw = new RelayWait((Relay)relay, idx++, user, order);
			relayWaitRepo.save(rw);
		}
	}
	
	public void updateDeadline(Quest relay, long nextUserId, LocalDateTime time) {
		User user = userRepo.getById(nextUserId);
		RelayWait rw = relayWaitRepo.findByRelayAndUser(relay, user);
		rw.setDeadline(time.plusDays(2));
	}

	public void deleteByRelayAndRelayOrder(Quest questById, int order) {
		relayWaitRepo.deleteByRelayAndRelayOrder(questById, order);
	}
	
	public int countByRelay(Relay relay) {
		return relayWaitRepo.countByRelay(relay);
	}
	
	public RelayWait findByRelayAndUser(Relay relay, User user) {
		return relayWaitRepo.findByRelayAndUser(relay, user);
	}
	
	public boolean checkLastWait(Relay relay, User user) {
		if(countByRelay(relay) == findByRelayAndUser(relay, user).getWaitRank())
			return true;
		
		return false;
	}
	
	
}
