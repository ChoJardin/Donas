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
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class CommentService {
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	UserRepo userRepo;

	public boolean checkComment(long commentId) {
		Comment comment = commentRepo.getById(commentId);
		if (comment == null)
			return false;
		return true;
	}

	public void add(Article article, long userId, String content) {
		Comment comment = new Comment(content, userRepo.getById(userId), article, LocalDateTime.now().plusHours(9), LocalDateTime.now().plusHours(9), 0);
		commentRepo.save(comment);
		
		article.getComments().add(comment);
	}

	public void update(long commentId, String content) {
		Comment comment = commentRepo.findById(commentId).get();
		comment.setContent(content);
		comment.setUpdatedAt(LocalDateTime.now().plusHours(9));
	}

	public void delete(long commentId) {
		commentRepo.deleteById(commentId);
	}

	public List<Comment> getComments(Article article) {
		return commentRepo.findCommentByArticle(article);
	}
}
