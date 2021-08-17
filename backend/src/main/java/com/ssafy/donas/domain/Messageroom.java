package com.ssafy.donas.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Messageroom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userone_id")
	private User user1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usertwo_id")
	private User user2;
	
//	@OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<Message> msg = new ArrayList<>();
	
	public Messageroom() {}
	
	@Builder
	public Messageroom(User user1, User user2) {

		this.user1 = user1;
		this.user2 = user2;
	}
	
	
}
