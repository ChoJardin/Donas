package com.ssafy.donas.response;

import java.util.List;

import com.ssafy.donas.domain.CashInfo;

import io.swagger.annotations.ApiModelProperty;

public class CashListResponse {
	@ApiModelProperty(value = "total", position = 1)
	public long total;
	@ApiModelProperty(value = "cashList",position = 2)
	public List<CashInfo> cashList;
}
