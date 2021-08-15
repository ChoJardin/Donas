package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.donas.domain.Token;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.TokenRepo;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	TokenRepo tokenRepo;

	public boolean logOut(long userId, String token) {
		Optional<Token> userToken = tokenRepo.findTokenByTokenAndUser_id(token, userId);
		if (userToken.isEmpty())
			return false;
		userToken.ifPresent(selectToken -> {
			tokenRepo.delete(selectToken);
		});
		return true;
	}

	public User checkPassword(String email, String password, String token) {
		User user = userRepo.findUserByEmailAndPassword(email, password);
		if (user == null) {
			return null;
		}
		
		Optional<Token> userToken = tokenRepo.findTokenByTokenAndUser_id(token, user.getId());
		if (userToken.isEmpty()) {
			Token tokenId = new Token(token, user);
			tokenRepo.save(tokenId);
		}
		return user;
	}

	public boolean checkEmail(String email) {
		Optional<User> user = userRepo.findUserByEmail(email);
		if (user.isEmpty())
			return false;
		return true;
	}

	public boolean checkNickname(String nickname) {
		Optional<User> user = userRepo.findUserByNickname(nickname);
		if (user.isEmpty())
			return false;
		return true;
	}

	public boolean join(String email, String password, String nickname) {
		User user = new User(email, password, nickname,LocalDateTime.now().plusHours(9));
		userRepo.save(user);

		return true;
	}

	// 인터셉터로 바꾸면 좋을듯?
	public boolean checkId(long id) {
		Optional<User> testUser = userRepo.findById(id);
		if (testUser.isEmpty())
			return false;
		return true;
	}

	public boolean addNickname(long id, String nickname) {
		if (!checkId(id))
			return false;

		User user = userRepo.findById(id).get();
		user.setNickname(nickname);

		return true;
	}

	public boolean checkPassword(long id, String password) {
		Optional<User> user = userRepo.findUserByIdAndPassword(id, password);
		if (user.isPresent())
			return true;

		return false;
	}

	public boolean updateUserInfo(long id, String nickname, String picture, String description) {
		if (!checkId(id))
			return false;

		User user = userRepo.findById(id).get();
		
		if (nickname != null)
			user.setNickname(nickname);
		
		user.setPicture(picture);
		user.setDescription(description);

		return true;
	}

	public User getUser(long id) {
		if (!checkId(id))
			return null;

		return userRepo.getById(id);
	}

	public long getIdByNickname(String nickname) {
		Optional<User> user = userRepo.findUserByNickname(nickname);
		if (user.isEmpty())
			return -1;

		return user.get().getId();
	}

	public List<User> findByNicknameStartsWith(String nickname) {
		return userRepo.findByNicknameStartsWith(nickname);
	}

	public void updatePassword(long id, String newPassword) {
		User user = userRepo.getById(id);
		user.setPassword(newPassword);
	}
	
	// 총 마일리지
	public long sumMileage() {
		return userRepo.count();
	}

}
