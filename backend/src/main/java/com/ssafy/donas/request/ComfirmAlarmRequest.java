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
	long id;
	
	@ApiModelProperty(required = true)
	@NotNull
	int confirm;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}	


	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	
	

}
