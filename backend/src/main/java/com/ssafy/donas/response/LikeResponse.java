package com.ssafy.donas.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class LikeResponse {
	@ApiModelProperty(value = "article_id", required = true , position = 1)
	public long articleId;
	@ApiModelProperty(value = "user_ids", required = true , position = 2)
	public List<IdResponse> userIds;
}
