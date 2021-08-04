package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.ArticleInfo;
import com.ssafy.donas.domain.Like;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.repository.ArticleRepo;

@Service
@Transactional
public class ArticleService {
	@Autowired
	UserService userService;

	@Autowired
	ArticleRepo articleRepo;

	public boolean checkArticle(long articleId) {
		Optional<Article> article = articleRepo.findById(articleId);
		if(article.isEmpty()) {
			System.out.println(articleId);
			return false;
		}
		return true;
	}
	
	public void add(User user, Quest quest, String image, String content, String type) {
		Article article = new Article(user, quest, image, content, LocalDateTime.now(), null, type);
		articleRepo.save(article);
		user.getArticles().add(article);
	}
	
	public void update(long articleId, String content) {
		Article article = articleRepo.findById(articleId).get();
		article.setContent(content);
	}
	
	public void delete(long articleId) {
		articleRepo.deleteById(articleId);
	}
	
	public List<Article> getArticlesByUser(User user){
		return articleRepo.findArticleByUser(user);
	}
	
	public List<ArticleInfo> getArticleInfosByUser(User user){
		List<Article> articles = getArticlesByUser(user);
		
		List<ArticleInfo> infos = new ArrayList<ArticleInfo>();
		for(Article a : articles) {
			// 유저가 해당 게시글에 하트를 눌렀는지 여부 확인
			boolean isLike = false;
			for(Like like: a.getLikes()) {
				if(like.getUser() == user) {
					isLike = true;
					break;
				}
			}
			
			infos.add(new ArticleInfo(a.getId(), a.getImage(), a.getContent(), a.getCreatedAt(), a.getUpdatedAt(), a.getType(), isLike, a.getLikes().size(), a.getComments().size()));
		}
		
		return infos;
	}
	
	public Article getArticleById(long id) {
		if(!checkArticle(id))
			return null;
		return articleRepo.getById(id);
	}
	
	public List<Article> getOrderedFollowingArticleByUser(User user) {
		return articleRepo.findTop5ByUserOrderByCreatedAt(user);
	}

}
