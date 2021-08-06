package com.ssafy.donas.request;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class UserInfoRequest {
	@ApiModelProperty(required = true)
	String nickname;

	@ApiModelProperty(required = true)
	String picture;
	
	@ApiModelProperty(required = true)
	String description;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
