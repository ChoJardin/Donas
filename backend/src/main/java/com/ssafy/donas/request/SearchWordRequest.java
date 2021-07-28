package com.ssafy.donas.request;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class SearchWordRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long id; 
    
    @ApiModelProperty(required = true)
    @NotNull
    String nickname;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
}
