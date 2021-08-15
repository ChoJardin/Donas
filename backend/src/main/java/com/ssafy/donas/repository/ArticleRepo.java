package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long>{
	List<Article> findArticleByQuestByIdDesc(Quest quest);
	List<Article> findArticleByUserOrderByIdDesc(User user);
	List<Article> findTop5ByUserOrderByCreatedAt(User user);
	List<Article> findArticleByUserAndTypeByIdDesc(User user, String type);
}
