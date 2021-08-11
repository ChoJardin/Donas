package com.ssafy.donas.response;

import java.util.List;

import com.ssafy.donas.domain.DonationInfo;

import io.swagger.annotations.ApiModelProperty;

public class DonationListResponse {
	@ApiModelProperty(value = "total", position = 1)
	public long total;
	@ApiModelProperty(value = "donationList",position = 2)
	public List<DonationInfo> donationList;

}
