package com.ssafy.donas.request;

import javax.validation.Valid;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class ComfirmAlarmRequest {
	@ApiModelProperty(required = true)
	@NotNull
	long alarmId;
	
	@ApiModelProperty(required = true)
	@NotNull
	int confirm;

	public long getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(long alarmId) {
		this.alarmId = alarmId;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}	
	
	

}
