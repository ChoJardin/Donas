package com.ssafy.donas.request;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AcceptRelayRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long alarmId;
	
	@ApiModelProperty(required = true)
    @NotNull
    long questId;
	
	@ApiModelProperty(required = true)
    @NotNull
    long userId;
	
	@ApiModelProperty(required = true)
    @NotNull
    int relayOrder;

	public long getQuestId() {
		return questId;
	}

	public long getUserId() {
		return userId;
	}

	public int getRelayOrder() {
		return relayOrder;
	}

	public long getAlarmId() {
		return alarmId;
	}

}
