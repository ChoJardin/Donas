package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.donas.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
//@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Table(name="Quest_alarm")
public class QuestAlarm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receive_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quest_id")
	private Quest quest;
	
	@Column(name="send_name", nullable = false)
	private String sendName;
	
	@Column(nullable = false, length=200)
	private String contents;

	@Column(name="send_time", nullable = false)
	@ColumnDefault("CURRENT_TIMESTAMP()")
	private LocalDateTime sendTime;

	// 0 : 읽음 | 1 : 안읽음	| 2 : 수락 |	3 : 거절
	@Column(nullable = false)
	@ColumnDefault("1")
	private int confirm;
	
	public QuestAlarm() {}

	@Builder
	public QuestAlarm(User user, Quest quest, String sendName, String contents, LocalDateTime sendTime) {
		super();
		this.user = user;
		this.quest = quest;
		this.sendName = sendName;
		this.contents = contents;
		this.sendTime = sendTime;
	}
	
}
