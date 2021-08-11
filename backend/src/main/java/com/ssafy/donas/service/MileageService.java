package com.ssafy.donas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class MileageService {

	
	@Autowired
	UserRepo userRepo;
	
	public void minusMileage(long userId, long amount) {
		User user = userRepo.getById(userId);
		user.setMileage(amount);
	}
}
