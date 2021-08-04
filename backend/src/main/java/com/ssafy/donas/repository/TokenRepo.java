package com.ssafy.donas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Token;

public interface TokenRepo extends JpaRepository<Token, Long>{

	Optional<Token> findTokenByTokenAndUser_id(String token,long userId);
	
	List<Token> findTokenByUser_id(long userId);
}
