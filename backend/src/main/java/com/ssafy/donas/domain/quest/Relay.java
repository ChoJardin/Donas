package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

<<<<<<< HEAD
=======
import org.hibernate.annotations.ColumnDefault;

>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
<<<<<<< HEAD
import lombok.Data;

@Entity
@Data
//@NoArgsConstructor
=======
import lombok.Getter;


@Getter
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@DiscriminatorValue("R")
<<<<<<< HEAD
=======
@Entity
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
@Table(name="RelayQuest")
public class Relay extends Quest{
	@Column(name = "user_order", nullable = false)
	private int order;
	@Column(nullable = false)
	private String userStatus;
	
	public Relay() {}
	
<<<<<<< HEAD
	@Builder
	public Relay(String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt, int order, String userStatus) {
		super(type, title, description, startAt, finishAt);
=======
	public Relay(long userId, String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt, int order, String userStatus) {
		super(userId, type, title, description, startAt, finishAt);
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
		this.order = order;
		this.userStatus = userStatus;
	}

}
