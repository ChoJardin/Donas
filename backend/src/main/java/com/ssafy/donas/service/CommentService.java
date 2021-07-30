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

<<<<<<< HEAD

=======
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
@Service
@Transactional
public class CommentService {
	@Autowired
	ArticleService articleService;
<<<<<<< HEAD
	
=======

>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
	@Autowired
	CommentRepo commentRepo;

	public boolean checkComment(long commentId) {
		Comment comment = commentRepo.getById(commentId);
<<<<<<< HEAD
		if(comment == null)
=======
		if (comment == null)
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
			return false;
		return true;
	}

<<<<<<< HEAD


	public boolean addComment(Article article, long userId, String content) {
		Comment comment = new Comment(content, userId, article, LocalDateTime.now(), LocalDateTime.now(), 0);
		commentRepo.save(comment);
		article.getComments().add(comment);
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
	
=======
	public void add(Article article, long userId, String content) {
		Comment comment = new Comment(content, userId, article, LocalDateTime.now(), LocalDateTime.now(), 0);
		commentRepo.save(comment);
		article.getComments().add(comment);
	}

	public void update(long commentId, String content) {
		Comment comment = commentRepo.findById(commentId).get();
		comment.setContent(content);
		comment.setUpdatedAt(LocalDateTime.now());
	}

>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
	public void delete(long commentId) {
		commentRepo.deleteById(commentId);
	}

	public List<Comment> getComments(Article article) {
		return commentRepo.findCommentByArticle(article);
	}
}
