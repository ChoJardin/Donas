package com.ssafy.donas.domain;

import java.time.LocalDateTime;
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

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class MessageRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user1_id")
	private User user1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user2_id")
	private User user2;
	
	@OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Message> messages = new ArrayList<Message>();

	public MessageRoom() {};
	
	public MessageRoom(User user1, User user2) {

		this.user1 = user1;
		this.user2 = user2;
	}
	
	
}
