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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ssafy.donas.domain.quest.QuestParticipants;
import com.ssafy.donas.domain.quest.UserLikeQuests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
//@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length=100)
	private String email;

	@Column(nullable = false, length=50)
	private String nickname;

	@Column(nullable = false, length=50)
	private String password;

	@Column(nullable = false)
	@ColumnDefault("0")
	private long mileage;

	@Column(insertable = false, updatable = false)
	@ColumnDefault("CURRENT_TIMESTAMP()")
	private LocalDateTime joindate;

	@Column(nullable = false, name="quest_cnt")
	@ColumnDefault("0")
	private int questCnt;

	@Column(nullable = false, name="quest_percent")
	@ColumnDefault("0")
	private int questPercent;

	@ColumnDefault("NULL")
	private String picture;
	
	@ColumnDefault("TRUE")
	private boolean status;
	
	@Column(nullable = true, length=100)
	private String description;

	@OneToMany(mappedBy = "followTo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Follow> follower = new ArrayList<Follow>();
	
	@OneToMany(mappedBy = "followFrom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Follow> following = new ArrayList<Follow>();
	
	@OneToMany(mappedBy = "searchUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Search> searchWords = new ArrayList<Search>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<QuestParticipants> myQuests = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserLikeQuests> likeQuests = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Article> articles = new ArrayList<Article>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Donation> donations = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Like> likeArticles = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Comment> comments = new ArrayList<>();
	
	public User() {}

	@Builder
	public User(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

}
