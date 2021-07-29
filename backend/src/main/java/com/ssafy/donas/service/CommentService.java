package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.Comment;
import com.ssafy.donas.repository.CommentRepo;

@Service
@Transactional
public class CommentService {
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CommentRepo commentRepo;

	public boolean checkComment(long commentId) {
		Comment comment = commentRepo.getById(commentId);
		if(comment == null)
			return false;
		return true;
	}
	public List<Comment> findCommentByArticle(long articleId){
		if(!articleService.checkArticle(articleId))
			return null;
		return commentRepo.findCommentByArticleId(articleId);
	}

	//replyTo, updatedAt 관련 초기값 수정 필요
	public boolean addComment(long articleId, long userId, String content) {
		Comment comment = new Comment(content, userId, articleId, LocalDateTime.now(), LocalDateTime.now(), userId);
		commentRepo.save(comment);
		return true;
	}
	
	public boolean update(long commentId, String content) {
		if(content == "")
			return false;
		Comment comment = commentRepo.findById(commentId).get();
		comment.setContent(content);
		comment.setUpdatedAt(LocalDateTime.now());
		return true;
	}
	
	public void delete(long commentId) {
		commentRepo.deleteById(commentId);
	}
}
