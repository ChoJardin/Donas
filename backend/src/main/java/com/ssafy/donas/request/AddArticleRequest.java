package com.ssafy.donas.request;

import java.time.LocalDateTime;

import javax.validation.Valid;

import com.ssafy.donas.domain.quest.Quest;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class AddArticleRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long userId;
	
	@ApiModelProperty(required = true)
    @NotNull
    long questId;
	
	@ApiModelProperty(required = true)
    @NotNull
    String image;
	
	@ApiModelProperty(required = true)
    @NotNull
    String content;
	
	@ApiModelProperty(required = true)
    @NotNull
    String type;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getQuestId() {
		return questId;
	}

	public void setQuestId(long questId) {
		this.questId = questId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
