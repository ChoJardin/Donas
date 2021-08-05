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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id", referencedColumnName = "id")
	private Article article;
	
	@Column(name = "created_at", nullable = false)
	@ColumnDefault("CURRENT_TIMESTAMP()")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = true)
	private LocalDateTime updatedAt;
	
	@Column(name = "reply_to")
	@ColumnDefault("0")
	private long replyTo;

	public Comment() {}
	
	@Builder
	public Comment(String content, User user, Article article, LocalDateTime createdAt,
			LocalDateTime updatedAt, long replyTo) {
		this.content = content;
		this.user = user;
		this.article = article;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.replyTo = replyTo;
	}
}
