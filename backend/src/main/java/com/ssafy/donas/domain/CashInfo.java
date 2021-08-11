package com.ssafy.donas.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CashInfo {
	
	private long id;
	private String bank;
	private long amount;
	
	public CashInfo(long id, String bank, long amount) {
		this.id = id;
		this.bank = bank;
		this.amount = amount;
	}

}
