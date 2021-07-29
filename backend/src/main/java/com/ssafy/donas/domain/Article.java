package com.ssafy.donas.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
	
	@ManyToOne(targetEntity=User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(targetEntity=Quest.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "quest_id", referencedColumnName = "id")
	private Quest quest;
	
	@Column(nullable = false)
	private String image;
	
	@Column(nullable = false)
	private String content;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = true)
	private LocalDateTime updatedAt;
	
	@Column(nullable = false)
	private String type;
	
	@OneToMany(mappedBy = "articleId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Like> lilkes = new ArrayList<Like>();
	
	@OneToMany(mappedBy = "articleId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	public Quest getQuest() {
		return quest;
	}

	public void setQuestId(Quest quest) {
		this.quest = quest;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Like> getLilkes() {
		return lilkes;
	}

	public void setLilkes(List<Like> lilkes) {
		this.lilkes = lilkes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
