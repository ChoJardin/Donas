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
	
	// 인터셉터로 바꾸면 좋을듯?
	public boolean checkId(long id) {
		Optional<User> testUser = userRepo.findById(id);
		if(testUser.isEmpty())
			return false;
		
		return true;
	}

	public boolean addNickname(long id, String nickname) {
		if(!checkId(id))
			return false;
		
		User user = userRepo.findById(id).get();
		user.setNickname(nickname);
		
		return true;
	}

	public boolean checkPassword(long id, String password) {
		Optional<User> user = userRepo.findUserByIdAndPassword(id, password);
		if(user.isPresent())
			return true;
		
		return false;
	}

	public boolean updateUserInfo(long id, String nickname, String picture, String description) {	
		if(!checkId(id))
			return false;

		User user = userRepo.findById(id).get();
		user.setNickname(nickname);
		user.setPicture(picture);
		user.setDescription(description);
		
		return true;
	}

	public User getUser(long id) {
		if(!checkId(id))
			return null;
		
		return userRepo.getById(id);
	}

}
