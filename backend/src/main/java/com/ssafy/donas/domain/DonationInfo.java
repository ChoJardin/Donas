package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class DonationInfo {
	private long id;
	private String charityName;
	private long amount;
	
	
	public DonationInfo(long id, String charityName, long amount) {
		this.id = id;
		this.charityName = charityName;
		this.amount = amount;
	}
}
