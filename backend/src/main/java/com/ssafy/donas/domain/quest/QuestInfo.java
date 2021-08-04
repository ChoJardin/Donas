package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestInfo {
	public long id;
	private String type;
	private String title;
	private String description;
	private LocalDateTime startAt;
	private LocalDateTime finishAt;
	
	public QuestInfo() {}
	
	@Builder
	public QuestInfo(long id, String type, String title, String description, LocalDateTime startAt, LocalDateTime finishAt) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.description = description;
		this.startAt = startAt;
		this.finishAt = finishAt;
	}
	
}
