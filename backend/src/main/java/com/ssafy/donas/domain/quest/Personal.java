package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@DiscriminatorValue("P")
@Entity
@Table(name="PersonalQuest")
public class Personal extends Quest{
	public Personal() {}
	
	public Personal(long userId, String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt) {
		super(userId, type, title, description, startAt, finishAt);
	}
}
