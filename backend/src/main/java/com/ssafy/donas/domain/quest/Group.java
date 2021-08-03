package com.ssafy.donas.domain.quest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

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
@DiscriminatorValue("G")
@Table(name="Group_quest")
public class Group extends Quest{
	@Column(nullable = false)
	@ColumnDefault("0")
	private int percent;
	
	public Group() {}
	
	@Builder
	public Group(String type, String title, String description, Date startAt, Date finishAt, int percent) {
		super(type, title, description, startAt, finishAt);
		this.percent = percent;
	}
}
