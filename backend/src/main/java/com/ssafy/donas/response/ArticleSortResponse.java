package com.ssafy.donas.response;

import io.swagger.annotations.ApiModelProperty;

public class ArticleSortResponse {
	@ApiModelProperty(value = "id", required = true , position = 1)
	public long id;
	@ApiModelProperty(value = "content", position = 2)
	public String content;
	@ApiModelProperty(value = "image", position = 3)
	public String image;
	@ApiModelProperty(value = "questId",position = 4)
	public long questId;
	@ApiModelProperty(value = "type", position = 5)
	public String type;
	@ApiModelProperty(value = "questTitle", position = 6)
	public String questTitle;
}
