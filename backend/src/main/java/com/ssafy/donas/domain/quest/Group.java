package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;
<<<<<<< HEAD
import java.util.Date;
import java.util.List;
=======
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonInclude;
<<<<<<< HEAD
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
=======

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@DiscriminatorValue("G")
<<<<<<< HEAD
=======
@Entity
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
@Table(name="GroupQuest")
public class Group extends Quest{
	@Column(nullable = false)
	@ColumnDefault("0")
	private int percent;
	
	public Group() {}
	
<<<<<<< HEAD
	@Builder
	public Group(String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt, int percent) {
		super(type, title, description, startAt, finishAt);
=======
	public Group(long userId, String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt, int percent) {
		super(userId, type, title, description, startAt, finishAt);
>>>>>>> 9da3d66f4e6bb0c17c8f017d5813d7a0500b0349
		this.percent = percent;
	}
}
