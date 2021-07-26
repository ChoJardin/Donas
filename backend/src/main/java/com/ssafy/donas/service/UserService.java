package com.ssafy.donas.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	public User checkPassword(String email, String password) {
		User user = userRepo.findUserByEmailAndPassword(email, password);
		return user;
	}

	public boolean checkEmail(String email) {
		Optional<User> user = userRepo.findUserByEmail(email);
		if(user.isEmpty())
			return false;
		return true;
	}
	
	public boolean checkNickname(String nickname) {
		Optional<User> user = userRepo.findUserByNickname(nickname);
		if(user.isEmpty())
			return false;
		return true;
	}

	public boolean join(String email, String password, String nickname) {
		User user = new User(email, password, nickname);
		userRepo.save(user);
		
		return true;
	}

	public boolean addNickname(long id, String nickname) {
		User user = userRepo.findById(id);
		user.setNickname(nickname);
		
		return true;
	}

}
