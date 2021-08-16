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
	private LocalDateTime time;
	private String name;
	private String charityName;
	private long amount;
	
	public DonationInfo(long id, LocalDateTime time, String name, String charityName, long amount) {
		this.id = id;
		this.time = time;
		this.name = name;
		this.charityName = charityName;
		this.amount = amount;
	}
	
	

}
