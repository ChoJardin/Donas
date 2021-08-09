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

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
	
	@Column(name = "finish_at", nullable = true)
	private Date finishAt;

	@Column(nullable = true)
	private String picture;
	
	@Column(nullable = true)
	private String certification;
	
	@Column(nullable = false)
	private long mileage;
	
	@Column(nullable = false)
	@ColumnDefault("0")
	private int success;
	
	@OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Article> articles = new ArrayList<Article>();
	
	@OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<QuestParticipants> participants = new ArrayList<>();
	
	@OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserLikeQuests> likeUsers = new ArrayList<>();
	
	public Quest() {}
	
//	public Quest(String type, String title, String description, Date startAt, Date finishAt) {
//		this.type = type;
//		this.title = title;
//		this.description = description;
//		this.startAt = startAt;
//		this.finishAt = finishAt;
//	}
	
//	public Quest(String type, String title, String description, String picture, Date startAt, Date finishAt) {
//		this.type = type;
//		this.title = title;
//		this.description = description;
//		this.picture = picture;
//		this.startAt = startAt;
//		this.finishAt = finishAt;
//	}

	public Quest(String type, String title, String description, Date startAt, Date finishAt, String picture,
			String certification, long mileage) {
		super();
		this.type = type;
		this.title = title;
		this.description = description;
		this.startAt = startAt;
		this.finishAt = finishAt;
		this.picture = picture;
		this.certification = certification;
		this.mileage = mileage;
	}
	
}
