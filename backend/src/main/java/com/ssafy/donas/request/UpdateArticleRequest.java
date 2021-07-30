package com.ssafy.donas.request;

import java.time.LocalDateTime;

import javax.validation.Valid;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class UpdateArticleRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long articleId;

	@ApiModelProperty(required = true)
    @NotNull
    String content;

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
