package com.ssafy.donas.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.donas.domain.quest.Quest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
//@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Article{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quest_id", referencedColumnName = "id")
	private Quest quest;
	
	@Column(nullable = false)
	private String image;
	
	@Column(nullable = false)
	private String content;
	
	@Column(name = "created_at", nullable = false)
	@ColumnDefault("CURRENT_TIMESTAMP()")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = true)
	private LocalDateTime updatedAt;
	
	@Column(nullable = false)
	private String type;
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Like> likes = new ArrayList<>();
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<Comment>();
	
	public Article() {}
	
	@Builder
	public Article(User user, Quest quest, String image, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String type) {
		this.user = user;
		this.quest = quest;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.image = image;
		this.content = content;
		this.type = type;
	}

}