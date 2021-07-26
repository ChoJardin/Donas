package com.ssafy.donas.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@NoArgsConstructor
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

//	@OneToMany(fetch = FetchType.LAZY)
	
	public User() {
		
	}

	@Builder
	public User(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public long getMileage() {
		return mileage;
	}

	public Date getJoindate() {
		return joindate;
	}

	public int getQuestCnt() {
		return questCnt;
	}

	public int getQuestPercent() {
		return questPercent;
	}

	public String getPicture() {
		return picture;
	}

	public boolean isStatus() {
		return status;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
