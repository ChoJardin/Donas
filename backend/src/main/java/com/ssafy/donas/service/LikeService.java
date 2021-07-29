package com.ssafy.donas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Like;
import com.ssafy.donas.repository.LikeRepo;

@Service
@Transactional
public class LikeService {
	@Autowired
	LikeRepo likeRepo;
	
	public boolean checkLike(long likeId) {
		Like like = likeRepo.getById(likeId);
		if(like == null)
			return false;
		return true;
	}
	
	public boolean addLike(long userId, long articleId) {
		Like like = new Like(userId, articleId);
		likeRepo.save(like);
		//like 목록에 추가하는 함수 추가
		return true;
	}

	
	public void delete(long likeId) {
		likeRepo.deleteById(likeId);
	}

	public List<Like> getLikes(long articleId) {
		return likeRepo.findLikeByArticleId(articleId);
	}
}
