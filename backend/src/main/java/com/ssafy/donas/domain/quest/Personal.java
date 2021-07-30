package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;

<<<<<<< HEAD
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
=======
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
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
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@DiscriminatorValue("P")
<<<<<<< HEAD
@Table(name="PersonalQuest")
public class Personal extends Quest{
	@Transient
	String test;
	
	public Personal() {}
	
	@Builder
	public Personal(String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt) {
		super(type, title, description, startAt, finishAt);
=======
@Entity
@Table(name="PersonalQuest")
public class Personal extends Quest{
	public Personal() {}
	
	public Personal(long userId, String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt) {
		super(userId, type, title, description, startAt, finishAt);
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
	}
}
