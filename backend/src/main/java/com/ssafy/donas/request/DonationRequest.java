package com.ssafy.donas.request;

import javax.validation.Valid;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class DonationRequest {
	@ApiModelProperty(required = true)
	@NotNull
	long userId;
	
	@ApiModelProperty(required = true)
	@NotNull
	long amount;
	
	@ApiModelProperty(required = true)
	@NotNull
	long charityId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getCharityId() {
		return charityId;
	}

	public void setCharityId(long charityId) {
		this.charityId = charityId;
	}
	
	
}
