package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Like;

public interface LikeRepo extends JpaRepository<Like, Long>{
	List<Like> findLikeByArticleId(long articleId);
}
