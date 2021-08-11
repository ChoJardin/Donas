package com.ssafy.donas.request;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class CashRequest {
	
	@ApiModelProperty(required = true)
	@NotNull
	long userId;
	
	@ApiModelProperty(required = true)
	@NotNull
	long amount;
	
	@ApiModelProperty(required = true)
	@NotNull
	String accountNum;
	
	@ApiModelProperty(required = true)
	@NotNull
	String banck;

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

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getBanck() {
		return banck;
	}

	public void setBanck(String banck) {
		this.banck = banck;
	}

}
