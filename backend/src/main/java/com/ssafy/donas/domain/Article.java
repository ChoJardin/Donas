package com.ssafy.donas.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Date;
=======
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
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
<<<<<<< HEAD
=======
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.donas.domain.quest.Quest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
<<<<<<< HEAD
=======
import lombok.Getter;
import lombok.NoArgsConstructor;
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349

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
	
<<<<<<< HEAD
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
=======
	@ManyToOne(targetEntity=User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(targetEntity=Quest.class, fetch = FetchType.LAZY)
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
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
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Like> lilkes = new ArrayList<Like>();
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<Comment>();
	
	public Article() {}
<<<<<<< HEAD
	
=======
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
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

<<<<<<< HEAD
}
=======
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
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
