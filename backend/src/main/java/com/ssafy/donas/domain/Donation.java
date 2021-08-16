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

@Entity
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Donation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "charity_id")
	private Charity charity;
	
	@Column(nullable = false, name = "cash_out")
	@ColumnDefault("0")
	private int cashOut;
	
	public Donation() {}

	public Donation(long amount, LocalDateTime time, String name, User user, Charity charity) {
		super();
		this.amount = amount;
		this.time = time;
		this.name = name;
		this.user = user;
		this.charity = charity;
	}
	
	
	
}
