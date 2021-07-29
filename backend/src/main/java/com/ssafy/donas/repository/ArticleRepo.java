package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.User;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long>{
	List<Article> findArticleByUser(User user);	
}
