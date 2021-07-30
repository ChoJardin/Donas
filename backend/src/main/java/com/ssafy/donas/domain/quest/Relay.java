package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
@Table(name="RelayQuest")
public class Relay extends Quest{
	@Column(name = "user_order", nullable = false)
	private int order;
	@Column(nullable = false)
	private String userStatus;
	
	public Relay() {}
	
	@Builder
	public Relay(String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt, int order, String userStatus) {
		super(type, title, description, startAt, finishAt);
		this.order = order;
		this.userStatus = userStatus;
	}

}
