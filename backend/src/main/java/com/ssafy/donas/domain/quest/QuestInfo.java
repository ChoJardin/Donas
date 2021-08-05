package com.ssafy.donas.domain.quest;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestInfo {
	public long id;
	private String type;
	private String title;
	private String description;
	private String picture;
	private Date startAt;
	private Date finishAt;
	
	public QuestInfo() {}
	
	@Builder
	public QuestInfo(long id, String type, String title, String description, String picture, Date startAt, Date finishAt) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.description = description;
		this.picture = picture;
		this.startAt = startAt;
		this.finishAt = finishAt;
	}
	
}
