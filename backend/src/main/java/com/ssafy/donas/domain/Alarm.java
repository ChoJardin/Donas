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
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receive_id",referencedColumnName ="id")
	private User user;
	
	@Column(name="send_name", nullable = false)
	private String sendName;
	
	@Column(name="article_id")
	private long articleId;
	
	@Column(nullable = false, length=200)
	private String contents;

	@Column(name="send_time", nullable = false)
	@ColumnDefault("CURRENT_TIMESTAMP()")
	private LocalDateTime sendTime;
	
	@Column(nullable = false)
	@ColumnDefault("0")
	private int confirm;



	public Alarm() {}


	@Builder
	public Alarm(User user, String sendName,long articleId,String contents, LocalDateTime sendTime, int confirm) {
		this.user = user;
		this.sendName = sendName;
		this.articleId = articleId;
		this.contents = contents;
		this.sendTime = sendTime;
		this.confirm = confirm;
	}

	
	
	

}
