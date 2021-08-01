package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import com.ssafy.donas.domain.quest.Quest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleInfo {
	private long id;
	private String image;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String type;

	public ArticleInfo() {}

	@Builder
	public ArticleInfo(long id, String image, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String type) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.image = image;
		this.content = content;
		this.type = type;
	}
}
