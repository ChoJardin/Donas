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
	
	@Column(name = "confirm")
	@ColumnDefault("0")
	private int confirm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "send_id")
	private User sendUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receive_id")
	private User receivedUser;
	
	@Column(nullable = false, updatable = false)
	@ColumnDefault("CURRENT_TIMESTAMP()")
	private LocalDateTime time;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private MessageRoom messageRoom;
	
	public Message() {}

	public Message(String content,int confirm, User sendUser, User receivedUser, LocalDateTime time, MessageRoom messageRoom) {
		this.content = content;
		this.confirm = confirm;
		this.sendUser = sendUser;
		this.receivedUser = receivedUser;
		this.time = time;
		this.messageRoom = messageRoom;
	}	
	
}
