package com.ssafy.donas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Like;

public interface LikeRepo extends JpaRepository<Like, Long>{
	List<Like> findLikeByArticleId(long articleId);
	Optional<Like> findByArticleIdAndUserId(long articleId, long userId);
}
