package com.ssafy.donas.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import java.util.*;

import io.swagger.annotations.ApiModelProperty;

public class AddRelayQuestRequest {
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
	String picture;
	
	@ApiModelProperty(required = true)
    @NotNull
	String certification;
	
	@ApiModelProperty(required = true)
    @NotNull
	long mileage;
	
	@ApiModelProperty(required = true)
    @NotNull
	List<Long> nextList;
	
	@ApiModelProperty(required = true)
    @NotNull
	int targetCnt;
	

	public long getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartAt() {
		return startAt;
	}

	public String getPicture() {
		return picture;
	}

	public String getCertification() {
		return certification;
	}

	public long getMileage() {
		return mileage;
	}

	public List<Long> getNextList() {
		return nextList;
	}

	public int getTargetCnt() {
		return targetCnt;
	}

}
