package com.ssafy.donas.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;


public class CommentResponse {
	@ApiModelProperty(value = "userId", required = true , position = 1)
	public long userId;
	@ApiModelProperty(value = "userNickname", required = true , position = 2)
	public String nickname;
	@ApiModelProperty(value = "userPicture", required = true , position = 3)
	public String picture;
	@ApiModelProperty(value = "commentId", required = true , position = 4)
	public long commentId;
	@ApiModelProperty(value = "content", required = true , position = 5)
	public String content;
	@ApiModelProperty(value = "created_at", required = true , position = 6)
	public LocalDateTime createdAt;
	@ApiModelProperty(value = "updated_at", required = false , position = 7)
	public LocalDateTime updatedAt;
}
