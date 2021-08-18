package com.ssafy.donas.request;

import java.util.Date;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class AddPersonalQuestRequest {
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
	Date startAt;
	
	@ApiModelProperty(required = true)
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
	Date finishAt;
	
	@ApiModelProperty(required = true)
    @NotNull
	String picture;
	
	@ApiModelProperty(required = true)
    @NotNull
	String certification;
	
	@ApiModelProperty(required = true)
    @NotNull
	long mileage;
	
	@ApiModelProperty(required = true)
    @NotNull
	int minArticleCount;

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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public long getMileage() {
		return mileage;
	}

	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	public int getMinArticleCount() {
		return minArticleCount;
	}

	public void setMinArticleCount(int minArticleCount) {
		this.minArticleCount = minArticleCount;
	}
	
}
