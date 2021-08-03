package com.ssafy.donas.request;

import javax.validation.Valid;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class SignoutRequest {
	@ApiModelProperty(required=true)
	@NotNull
	long userId;
	
	@ApiModelProperty(required=true)
	@NotNull
	String token;

	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
