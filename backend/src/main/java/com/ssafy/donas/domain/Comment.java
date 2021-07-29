package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String content;
	
	@Column(name = "user_id", nullable = false)
	private long userId;
	
	@ManyToOne(targetEntity=Article.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id", referencedColumnName = "id")
	private Article article;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = true)
	private LocalDateTime updatedAt;
	
	@Column(name = "reply_to")
	@ColumnDefault("0")
	private long replyTo;

	public Comment() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUser_id() {
		return userId;
	}

	public void setUser_id(long userId) {
		this.userId = userId;
	}

	public Article getArticleId() {
		return article;
	}

	public void setArticle_id(Article article) {
		this.article = article;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public long getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(long replyTo) {
		this.replyTo = replyTo;
	}


	@Builder
	public Comment(String content, long userId, Article article, LocalDateTime createdAt,
			LocalDateTime updatedAt, long replyTo) {
		this.content = content;
		this.userId = userId;
		this.article = article;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.replyTo = replyTo;
	}
}
