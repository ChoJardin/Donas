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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Cash {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "charity_id")
	private Charity charity;
	
	@Column(nullable = false)
	private long amount;
	
	@Column(insertable = false, updatable = false)
	@ColumnDefault("CURRENT_TIMESTAMP()")
	private LocalDateTime time;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable = false)
	private String accountNum;
	
	@Column(nullable = false)
	private String bank;
	
	public Cash() {}
	
	@Builder
	public Cash(long amount, User user) {
		this.amount = amount;
		this.user = user;
	}
	
	@Builder
	public Cash(long amount, Charity charity,LocalDateTime time, String name, User user, String accountNum, String bank) {
		super();
		this.amount = amount;
		this.charity = charity;
		this.time = time;
		this.name = name;
		this.user = user;
		this.accountNum = accountNum;
		this.bank = bank;
	}

	
}
