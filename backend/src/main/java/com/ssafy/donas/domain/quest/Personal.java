package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@DiscriminatorValue("P")
@Table(name="Personal_quest")
public class Personal extends Quest{
	@Transient
	String test;
	
	public Personal() {}
	
	@Builder
	public Personal(String type, String title, String description, Date startAt, Date finishAt, String picture, String certification, long mileage) {
		super(type, title, description, startAt, finishAt, picture, certification, mileage, 0);
	}
}
