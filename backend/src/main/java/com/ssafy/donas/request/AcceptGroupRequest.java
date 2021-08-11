package com.ssafy.donas.request;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AcceptGroupRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long alarmId;
	
	@ApiModelProperty(required = true)
    @NotNull
    long questId;
	
	@ApiModelProperty(required = true)
    @NotNull
    long userId;
	
	public long getQuestId() {
		return questId;
	}

	public long getUserId() {
		return userId;
	}

	public long getAlarmId() {
		return alarmId;
	}
}
