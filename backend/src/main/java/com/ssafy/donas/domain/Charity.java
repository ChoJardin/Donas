package com.ssafy.donas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Charity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length=20)
	private String name;
	
	@Column(nullable = false)
	@ColumnDefault("0")
	private int total;
	
	@Column(nullable = false)
	@ColumnDefault("0")
	private int quarter;
	
	public Charity () {}
	
	@Builder
	public Charity (String name) {
		this.name = name;
	}
}
