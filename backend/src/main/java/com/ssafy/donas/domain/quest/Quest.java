package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import com.ssafy.donas.domain.Follow;
import com.ssafy.donas.domain.Search;
import com.ssafy.donas.domain.User;

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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public abstract class Quest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Column(name = "start_at", nullable = false)
	private Date startAt;
	
	@Column(name = "finish_at", nullable = false)
	private Date finishAt;
	
	@OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Article> articles = new ArrayList<Article>();
	
	@OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<QuestParticipants> participants = new ArrayList<>();
	
	@OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserLikeQuests> likeUsers = new ArrayList<>();
	
	public Quest() {}
	
	public Quest(String type, String title, String description, Date startAt, Date finishAt) {
		this.type = type;
		this.title = title;
		this.description = description;
		this.startAt = startAt;
		this.finishAt = finishAt;
	}

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

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getFinishAt() {
		return finishAt;
	}

	public void setFinishAt(Date finishAt) {
		this.finishAt = finishAt;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<QuestParticipants> getParticipants() {
		return participants;
	}

	public void setParticipants(List<QuestParticipants> participants) {
		this.participants = participants;
	}

	public List<UserLikeQuests> getLikeUsers() {
		return likeUsers;
	}

	public void setLikeUsers(List<UserLikeQuests> likeUsers) {
		this.likeUsers = likeUsers;
	}
}
