package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ArticleInfo {
	private long id;
	private long questId;
	private String image;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String type;
	private boolean isLike;
	private int heartCnt;
	private int commentCnt;
	private String questTitle;
	private String makerName;
	private String makerImage;


	@Builder
	public ArticleInfo(long id, String image, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String type, boolean isLike, int heartCnt, int commentCnt) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.image = image;
		this.content = content;
		this.type = type;
		this.isLike = isLike;
		this.heartCnt = heartCnt;
		this.commentCnt = commentCnt;
	}
	
	@Builder
	public ArticleInfo(long id, long questId, String image, String content, LocalDateTime createdAt, LocalDateTime updatedAt,
			String type, boolean isLike, int heartCnt, int commentCnt, String questTitle, String makerName,
			String makerImage) {
		this.id = id;
		this.questId = questId;
		this.image = image;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.type = type;
		this.isLike = isLike;
		this.heartCnt = heartCnt;
		this.commentCnt = commentCnt;
		this.questTitle = questTitle;
		this.makerName = makerName;
		this.makerImage = makerImage;
	}
}
