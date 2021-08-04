package com.ssafy.donas.request;

import javax.validation.Valid;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class UpdateQuestRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long questId;
	
	@ApiModelProperty(required = true)
    @NotNull
    String title;
	
	@ApiModelProperty(required = true)
    @NotNull
    String description;

	public long getQuestId() {
		return questId;
	}

	public void setQuestId(long questId) {
		this.questId = questId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
