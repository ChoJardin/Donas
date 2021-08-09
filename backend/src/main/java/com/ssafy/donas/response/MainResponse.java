package com.ssafy.donas.response;

import java.util.List;

import com.ssafy.donas.domain.quest.Quest;

import io.swagger.annotations.ApiModelProperty;

public class MainResponse {
	@ApiModelProperty(value = "questCnt", position = 1)
	public long questCnt;
	@ApiModelProperty(value = "mileage", position = 2)
	public long mileage;
	@ApiModelProperty(value = "quarter", position = 3)
	public long quarter;
	@ApiModelProperty(value = "questP", position = 4)
	public List<Quest> questP;
	@ApiModelProperty(value = "questG", position = 5)
	public List<Quest> questG;
	@ApiModelProperty(value = "questR", position = 6)
	public List<Quest> questR;
}
