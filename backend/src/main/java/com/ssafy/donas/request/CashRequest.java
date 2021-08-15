package com.ssafy.donas.request;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class CashRequest {
	
	@ApiModelProperty(required = true)
	@NotNull
	long userId;
	
	@ApiModelProperty(required = true)
	@NotNull
	long charityId;
	
	@ApiModelProperty(required = true)
	@NotNull
	long amount;
	
	@ApiModelProperty(required = true)
	@NotNull
	String userName;
	
	@ApiModelProperty(required = true)
	@NotNull
	String accountNum;
	
	@ApiModelProperty(required = true)
	@NotNull
	String bank;
	

	public long getCharityId() {
		return charityId;
	}

	public void setCharityId(long charityId) {
		this.charityId = charityId;
	}

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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
