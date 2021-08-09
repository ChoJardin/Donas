package com.ssafy.donas.domain.quest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestMainInfo {
	public long id;
	private String title;
	private String picture;
	private String description;
	
	public QuestMainInfo() {}
	
	public QuestMainInfo(long id, String title, String picture, String description) {
		super();
		this.id = id;
		this.title = title;
		this.picture = picture;
		this.description = description;
	}
}
