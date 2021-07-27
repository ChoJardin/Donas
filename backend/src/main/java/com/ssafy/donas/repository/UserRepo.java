package com.ssafy.donas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findUserByEmailAndPassword(String email, String password);
//	User findById(long id);
	
	Optional<User> findUserByEmail(String email);
	Optional<User> findUserByNickname(String nickname);
	Optional<User> findUserByIdAndPassword(long id, String password);
	
}
