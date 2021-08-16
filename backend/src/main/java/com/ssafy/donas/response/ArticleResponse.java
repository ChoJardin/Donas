package com.ssafy.donas.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class ArticleResponse {
	@ApiModelProperty(value = "id", required = true , position = 1)
	public long id;
	@ApiModelProperty(value = "createdAt", position = 2)
	public LocalDateTime createdAt;
	@ApiModelProperty(value = "updated_at", position = 3)
	public LocalDateTime updatedAt;
	@ApiModelProperty(value = "image", position = 4)
	public String image;
	@ApiModelProperty(value = "content", position = 5)
	public String content;
	@ApiModelProperty(value = "type", position = 6)
	public String type;
	@ApiModelProperty(value = "like", position = 7)
	public boolean like;
	@ApiModelProperty(value = "likeCnt", position = 8)
	public int heartCnt;
	@ApiModelProperty(value = "commentCnt", position = 9)
	public int commentCnt;
	@ApiModelProperty(value = "makerImage",position = 10)
	public String makerImage;
	@ApiModelProperty(value = "makerName", position = 11)
	public String makerName;
	@ApiModelProperty(value = "questId", required = true , position = 12)
	public long questId;
	@ApiModelProperty(value = "questTitle", position = 13)
	public String questTitle;
}