package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.User;

<<<<<<< HEAD

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long>{
	List<Article> findArticleByUser(User user);	
=======
@Repository
public interface ArticleRepo extends JpaRepository<Article, Long>{
	List<Article> findArticleByUser(User user);
	List<Article> findTop5ByUserOrderByCreatedAt(User user);
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
}
