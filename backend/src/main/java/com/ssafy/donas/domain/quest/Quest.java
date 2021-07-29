package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.donas.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public abstract class Quest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_id", nullable = false)
	private long userId;

	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Column(name = "starts_at", nullable = false)
	private LocalDateTime startAt;
	
	@Column(name = "finish_at", nullable = false)
	private LocalDateTime finishAt;
	
	@OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Article> articles = new ArrayList<Article>();
	
	public Quest() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}

	public LocalDateTime getFinishAt() {
		return finishAt;
	}

	public void setFinishAt(LocalDateTime finishAt) {
		this.finishAt = finishAt;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Quest(long userId, String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt) {
		this.userId = userId;
		this.type = type;
		this.title = title;
		this.description = description;
		this.startAt = startAt;
		this.finishAt = finishAt;
	}
	
}
