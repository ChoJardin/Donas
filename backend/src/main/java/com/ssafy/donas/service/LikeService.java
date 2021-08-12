package com.ssafy.donas.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.Like;
import com.ssafy.donas.domain.User;
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
	
	public long checkLike( long userId,long articleId) {
		Optional<Like> like = likeRepo.findByArticleIdAndUserId(articleId, userId);		
		if(like.isPresent())
			return like.get().getId();
		else
			return -1;
	}
	

	
	
	public boolean addLike(User user, Article article) {
		Like like = new Like(user, article);
		likeRepo.save(like);
		article.getLikes().add(like);
		return true;
	}

	
	public void delete(long Id) {
		likeRepo.deleteById(Id);
	}

	public List<Like> getLikes(long articleId) {
		return likeRepo.findLikeByArticleId(articleId);
	}

}
