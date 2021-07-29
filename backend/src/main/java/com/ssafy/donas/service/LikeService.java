package com.ssafy.donas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Article;
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
	
	public boolean checkLikeByUserIdAndArticleId(long userId, long articleID) {
		//유저 아이디가 그 게시물에 좋아요를 눌렀는지 체크
		return true;
	}
	
	public boolean addLike(long userId, Article article) {
		Like like = new Like(userId, article);
		likeRepo.save(like);
		article.getLilkes().add(like);
		return true;
	}

	
	public void delete(long Id) {
		likeRepo.deleteById(Id);
	}

	public List<Like> getLikes(long articleId) {
		return likeRepo.findLikeByArticleId(articleId);
	}
}
