package com.ssafy.donas.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class CommentResponse {
	@ApiModelProperty(value = "id", required = true , position = 1)
	public long id;
	@ApiModelProperty(value = "content", required = true , position = 2)
	public String content;
	@ApiModelProperty(value = "created_at", required = true , position = 1)
	public LocalDateTime createdAt;
	@ApiModelProperty(value = "updated_at", required = false , position = 1)
	public LocalDateTime updatedAt;
	@ApiModelProperty(value = "reply_to", required = false , position = 1)
	public LocalDateTime replyTo;
}
