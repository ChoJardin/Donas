package com.ssafy.donas.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@Column(nullable = true)
	@ColumnDefault("0")
	private long total;
	
	@Column(nullable = true)
	@ColumnDefault("0")
	private int quarter;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private String picture;
	
	@Column(nullable = true)
	private String tag;
	
	@Column(nullable = true)
	private String tag2;
	
	@Column(nullable = true)
	private String homepage;
	
	@Column(nullable = true)
	private String bgPicture;
	
	@OneToMany(mappedBy = "charity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Donation> history = new ArrayList<>();
	
	public Charity () {}
	
	@Builder
	public Charity (String name) {
		this.name = name;
	}
}
