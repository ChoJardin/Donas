package com.ssafy.donas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.ArticleRepo;


@Service
@Transactional
public class ArticleService {
	@Autowired
	UserService userService;

	@Autowired
	ArticleRepo articleRepo;

	public boolean checkArticle(long articleId) {
		Article article = articleRepo.getById(articleId);
		if(article == null)
			return false;
		return true;
	}
	
	public List<Article> getArticlesByUser(User user){
		return articleRepo.findArticleByUser(user);
	}
	
	public Article getArticleById(long id) {
		return articleRepo.getById(id);
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
