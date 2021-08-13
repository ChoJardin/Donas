package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
//@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@DiscriminatorValue("R")
@Table(name="Relay_quest")
public class Relay extends Quest{
	@Column(name = "user_order", nullable = false)
	private int order;
	
	@Column(name = "target_cnt")
	private int targetCnt;
	
//	@Column(name = "user_status", nullable = false)
//	private String userStatus;
	
	@OneToMany(mappedBy = "relay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RelayWait> wait = new ArrayList<>();
	
	public Relay() {}
	
	@Builder
	public Relay(String type, String title, String description, Date startAt, String picture, String certification, long mileage, int order, int targetCnt) {
		super(type, title, description, startAt, null, picture, certification, mileage);
		this.order = order;
		this.targetCnt = targetCnt;
//		this.userStatus = userStatus;
	}

}
