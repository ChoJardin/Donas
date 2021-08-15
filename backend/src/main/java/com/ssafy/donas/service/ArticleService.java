package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.ssafy.donas.repository.FollowRepo;
import com.ssafy.donas.repository.quest.QuestRepo;

@Service
@Transactional
public class ArticleService {
	@Autowired
	UserService userService;

	@Autowired
	FollowRepo followRepo; 
	
	@Autowired
	ArticleRepo articleRepo;
	
	@Autowired
	QuestRepo questRepo;

	public boolean checkArticle(long articleId) {
		Optional<Article> article = articleRepo.findById(articleId);
		if(article.isEmpty()) {
			System.out.println(articleId);
			return false;
		}
		return true;
	}
	
	public Article add(User user, Quest quest, String image, String content, String type) {
		Article article = new Article(user, quest, image, content, LocalDateTime.now().plusHours(9), null, type);
		articleRepo.save(article);
		user.getArticles().add(article);
		
		return article;
	}
	
	public void update(long articleId, String content) {
		Article article = articleRepo.findById(articleId).get();
		article.setContent(content);
	}
	
	// 버그 : entity 못찾음
	public void delete(long articleId) {
		articleRepo.deleteById(articleId);
		articleRepo.flush();
	}
	
	// 퀘스트 내의 게시물 가져오기
	public List<Article> getArticlesByQuest(Quest quest){
		return articleRepo.findArticleByQuest(quest);
	}
	
	public List<Article> getArticlesByUser(User user){
		return articleRepo.findArticleByUser(user);
	}
	
	// 퀘스트 내의 게시물들 정보 리스트
	public List<ArticleInfo> getArticleInfoByQuest(long questId,User user){
		List<Article> articles = getArticlesByQuest(questRepo.getById(questId));
		
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
			infos.add(new ArticleInfo(a.getId(),a.getQuest().getId(), a.getImage(), a.getContent(), a.getCreatedAt(), a.getUpdatedAt(), a.getType(), isLike, a.getLikes().size(), a.getComments().size(), a.getQuest().getTitle(),a.getUser().getNickname(),a.getUser().getPicture()));
		}		
		return infos;
		
	}
	
	// 유저의 게시물 가져오기
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
			infos.add(new ArticleInfo(a.getId(),a.getQuest().getId(), a.getImage(), a.getContent(), a.getCreatedAt(), a.getUpdatedAt(), a.getType(), isLike, a.getLikes().size(), a.getComments().size(), a.getQuest().getTitle(),a.getUser().getNickname(),a.getUser().getPicture()));
		}		
		return infos;
	}
	
	// 다른 사람의 게시물들 확인
	public List<ArticleInfo> getArticleInfosByUser(User ownUser, User otherUser){
		List<Article> articles = getArticlesByUser(ownUser);
		
		List<ArticleInfo> infos = new ArrayList<ArticleInfo>();
		for(Article a : articles) {
			// 유저가 해당 게시글에 하트를 눌렀는지 여부 확인
			boolean isLike = false;
			for(Like like: a.getLikes()) {
				if(like.getUser() == otherUser) {
					isLike = true;
					break;
				}
			}
			infos.add(new ArticleInfo(a.getId(),a.getQuest().getId(), a.getImage(), a.getContent(), a.getCreatedAt(), a.getUpdatedAt(), a.getType(), isLike, a.getLikes().size(), a.getComments().size(), a.getQuest().getTitle(),a.getUser().getNickname(),a.getUser().getPicture()));
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

	public List<ArticleInfo> getArticleInfoByUserAndType(long userId, String type, String own ) {
		User presentUser = userService.getUser(userId);		
		List<ArticleInfo> articles = new ArrayList<ArticleInfo>();
		List<Article> own_articles = null;
		if(own.equals("mine")) {
			if(type.equals("A")) {
				own_articles = articleRepo.findArticleByUser(presentUser);

			}else {
				own_articles = articleRepo.findArticleByUserAndType(presentUser, type);

			}
			for(Article a : own_articles) {
				User user = a.getUser();
				// 유저가 해당 게시글에 하트를 눌렀는지 여부 확인
				boolean isLike = false;
				for(Like like: a.getLikes()) {
					if(like.getUser() == user) {
						isLike = true;
						break;
					}
				}
				articles.add(new ArticleInfo(a.getId(),a.getQuest().getId(), a.getImage(), a.getContent(), a.getCreatedAt(), a.getUpdatedAt(), a.getType(),isLike,a.getLikes().size(), a.getComments().size(),a.getQuest().getTitle(),user.getNickname(),user.getPicture()));
			}
		}else if(own.equals("other")){
			List<Long> followee_ids = followRepo.getFollowers(presentUser);
			for(long fd : followee_ids) {
				User followee = userService.getUser(fd);
				if(type.equals("A")) {
					own_articles = articleRepo.findArticleByUser(followee);
					for(Article a : own_articles) {
						User user = a.getUser();
						// 유저가 해당 게시글에 하트를 눌렀는지 여부 확인
						boolean isLike = false;
						for(Like like: a.getLikes()) {
							if(like.getUser() == user) {
								isLike = true;
								break;
							}
						}
						articles.add(new ArticleInfo(a.getId(),a.getQuest().getId(), a.getImage(), a.getContent(), a.getCreatedAt(), a.getUpdatedAt(), a.getType(),isLike,a.getLikes().size(), a.getComments().size(),a.getQuest().getTitle(),user.getNickname(),user.getPicture()));
					}
				}else {
					own_articles =articleRepo.findArticleByUserAndType(followee, type);
					for(Article a : own_articles) {
						User user = a.getUser();
						// 유저가 해당 게시글에 하트를 눌렀는지 여부 확인
						boolean isLike = false;
						for(Like like: a.getLikes()) {
							if(like.getUser() == user) {
								isLike = true;
								break;
							}
						}
						articles.add(new ArticleInfo(a.getId(),a.getQuest().getId(), a.getImage(), a.getContent(), a.getCreatedAt(), a.getUpdatedAt(), a.getType(),isLike,a.getLikes().size(), a.getComments().size(),a.getQuest().getTitle(),user.getNickname(),user.getPicture()));

					}
				}

			}
		}
		Collections.sort(articles, new Comparator<ArticleInfo>() {
			@Override
			public int compare(ArticleInfo o1, ArticleInfo o2) {
				return o2.getCreatedAt().compareTo(o1.getCreatedAt());
			}
		});	
		return articles;
	}

}
