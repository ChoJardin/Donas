package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.Comment;


public interface CommentRepo extends JpaRepository<Comment, Long>{
	List<Comment> findCommentByArticle(Article article);
}
