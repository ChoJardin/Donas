package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@DiscriminatorValue("G")
@Table(name="GroupQuest")
public class Group extends Quest{
	@Column(nullable = false)
	@ColumnDefault("0")
	private int percent;
	
	public Group() {}
	
	@Builder
	public Group(String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt, int percent) {
		super(type, title, description, startAt, finishAt);
		this.percent = percent;
	}
}
