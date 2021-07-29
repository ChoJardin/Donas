package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

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
@DiscriminatorValue("G")
@Entity
@Table(name="GroupQuest")
public class Group extends Quest{
	@Column(nullable = false)
	@ColumnDefault("0")
	private int percent;
	
	public Group() {}
	
	public Group(long userId, String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt, int percent) {
		super(userId, type, title, description, startAt, finishAt);
		this.percent = percent;
	}
}
