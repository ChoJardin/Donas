package com.ssafy.donas.domain;


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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date joindate;

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
	
	private String description;

	@OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Follow> followers = new ArrayList<Follow>();
	
	@OneToMany(mappedBy = "followee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Follow> followees = new ArrayList<Follow>();
	
	@OneToMany(mappedBy = "searchUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Search> searchWords = new ArrayList<Search>();
	
	public User() {}

	@Builder
	public User(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

}
