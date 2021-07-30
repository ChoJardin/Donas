package com.ssafy.donas.response;

import java.util.List;

import com.ssafy.donas.domain.Comment;

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
	@ApiModelProperty(value = "type", position = 5)
	public String type;
	@ApiModelProperty(value = "comment", position = 6)
	public int comment;
	@ApiModelProperty(value = "like", position = 7)
	public int like;
	
}
