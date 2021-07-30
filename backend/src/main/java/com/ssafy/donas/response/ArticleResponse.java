package com.ssafy.donas.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class ArticleResponse {
	@ApiModelProperty(value = "id", required = true , position = 1)
	public long id;
	@ApiModelProperty(value = "quest_id", required = true , position = 2)
	public long questId;
	@ApiModelProperty(value = "image", position = 3)
	public String image;
	@ApiModelProperty(value = "content", position = 4)
	public String content;
	@ApiModelProperty(value = "createdAt", position = 5)
	public LocalDateTime createdAt;
	@ApiModelProperty(value = "updated_at", position = 6)
	public LocalDateTime updatedAt;
	@ApiModelProperty(value = "type", position = 7)
	public String type;
	@ApiModelProperty(value = "comment", position = 8)
	public int comment;
	@ApiModelProperty(value = "like", position = 9)
	public int like;
}