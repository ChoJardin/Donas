package com.ssafy.donas.service;

<<<<<<< HEAD
import java.util.List;
=======
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.User;
<<<<<<< HEAD
import com.ssafy.donas.repository.ArticleRepo;


=======
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.repository.ArticleRepo;

>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
@Service
@Transactional
public class ArticleService {
	@Autowired
	UserService userService;

	@Autowired
	ArticleRepo articleRepo;

	public boolean checkArticle(long articleId) {
<<<<<<< HEAD
		Article article = articleRepo.getById(articleId);
		if(article == null)
			return false;
		return true;
	}
	
=======
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
	
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
	public List<Article> getArticlesByUser(User user){
		return articleRepo.findArticleByUser(user);
	}
	
	public Article getArticleById(long id) {
<<<<<<< HEAD
		return articleRepo.getById(id);
	}
=======
		if(!checkArticle(id))
			return null;
		return articleRepo.getById(id);
	}
	
	public List<Article> getOrderedFollowingArticleByUser(User user) {
		return articleRepo.findTop5ByUserOrderByCreatedAt(user);
	}
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349

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
