package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ArticleShortInfo {
	private long id;
	private String image;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String makerName;
	private String makerImage;
	
	@Builder
	public ArticleShortInfo(long id, String image, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String makerName, String makerImage) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.image = image;
		this.content = content;
		this.makerName = makerName;
		this.makerImage = makerImage;
	}

}
