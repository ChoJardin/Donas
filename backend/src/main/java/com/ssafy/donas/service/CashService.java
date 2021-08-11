package com.ssafy.donas.service;



import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Cash;
import com.ssafy.donas.repository.CashRepo;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class CashService {
	
	@Autowired
	CashRepo cashRepo;
	
	@Autowired
	UserRepo userRepo;
	

	public boolean changeCash(long userId,LocalDateTime changeTime, long amount, String accountNum, String bank) {		
		if(cashRepo.save(new Cash(amount,changeTime,userRepo.getById(userId),accountNum,bank)) == null)
			return false;
		return true;
	}
	
		
}
