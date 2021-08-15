package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CashInfo {
	
	private long id;
	private String charityName;
	private LocalDateTime time;
	private String name;
	private String bank;
	private long amount;
	

	public CashInfo(long id, String charityName, LocalDateTime time, String name, String bank, long amount) {
		super();
		this.id = id;
		this.charityName = charityName;
		this.time = time;
		this.name = name;
		this.bank = bank;
		this.amount = amount;
	}
	


}
