package com.ssafy.donas.request;

import java.util.Date;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class AddPersonalAndGroupQuestRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long userId;
	
	@ApiModelProperty(required = true)
    @NotNull
    String title;
	
	@ApiModelProperty(required = true)
    @NotNull
    String description;
	
	@ApiModelProperty(required = true)
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date startAt;
	
	@ApiModelProperty(required = true)
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date finishAt;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getFinishAt() {
		return finishAt;
	}

	public void setFinishAt(Date finishAt) {
		this.finishAt = finishAt;
	}

}
