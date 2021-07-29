package com.ssafy.donas.request;

import java.time.LocalDateTime;

import javax.validation.Valid;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class ArticleRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long articleId;
	
	@ApiModelProperty(required = true)
    @NotNull
    long userId;
	
	@ApiModelProperty(required = true)
    @NotNull
    long questId;
	
	@ApiModelProperty(required = true)
    @NotNull
    String content;
	
	@ApiModelProperty(required = true)
    @NotNull
    String image;
	
	@ApiModelProperty(required = true)
    @NotNull
    LocalDateTime createdAt;
	
	@ApiModelProperty(required = false)
    @NotNull
    LocalDateTime updatedAt;
	
	@ApiModelProperty(required = true)
    @NotNull
    String type;
}
