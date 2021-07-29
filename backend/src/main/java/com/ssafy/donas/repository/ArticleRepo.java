package com.ssafy.donas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.donas.domain.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long>{
//	List<Article> findArticleByUserid(long userId);	
}
