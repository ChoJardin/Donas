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
	
	public List<Article> getArticlesByUser(User user){
		return articleRepo.findArticleByUser(user);
	}
	
	public List<ArticleInfo> getArticleInfosByUser(User user){
		List<Article> articles = getArticlesByUser(user);
		
		List<ArticleInfo> infos = new ArrayList<ArticleInfo>();
		for(Article a : articles)
			infos.add(new ArticleInfo(a.getId(), a.getImage(), a.getContent(), a.getCreatedAt(), a.getUpdatedAt(), a.getType()));
		
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

//	@Transactional
//    public List<BoardResult> getBoard(){
//        List<BoardEntity> entityList = boardRepository.findAll();
//        List<BoardResult> results = entityList.stream().map(boardEntity -> {
//            BoardResult boardResult = new BoardResult();
//            boardResult.setContent(boardEntity.getContent());
//            boardResult.setUsername(boardEntity.getUsername());
//            boardResult.setTitle(boardEntity.getTitle());
//            boardResult.setSeq(boardEntity.getSeq());
//            return boardResult;
//        }).collect(Collectors.toList());
//
//        return results;
//    }

}
