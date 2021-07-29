package com.ssafy.donas.request;

import javax.validation.Valid;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class LikeRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long userId;
	
	@ApiModelProperty(required = true)
    @NotNull
    long articleId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
}
