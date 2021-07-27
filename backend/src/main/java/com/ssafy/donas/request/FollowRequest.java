package com.ssafy.donas.request;

import javax.validation.Valid;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class FollowRequest {
	@ApiModelProperty(required = true)
    @NotNull
    long followerId;
    
    @ApiModelProperty(required = true)
    @NotNull
    long followeeId;

	public long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(long followerId) {
		this.followerId = followerId;
	}

	public long getFolloweeId() {
		return followeeId;
	}

	public void setFolloweeId(long followeeId) {
		this.followeeId = followeeId;
	}
    
}
