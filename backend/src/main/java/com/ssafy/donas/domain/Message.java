package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "content")
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "send_id")
	private User sendUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receive_id")
	private User receivedUser;
	
	@Column(nullable = false, updatable = false)
	@ColumnDefault("CURRENT_TIMESTAMP()")
	private LocalDateTime time;
	
	@Column(nullable = false, updatable = false)
	private Messageroom messageroom;

	public Message(String content, User sendUser, User receivedUser, LocalDateTime time, Messageroom messageroom) {
		this.content = content;
		this.sendUser = sendUser;
		this.receivedUser = receivedUser;
		this.time = time;
		this.messageroom = messageroom;
	}	
	
}
