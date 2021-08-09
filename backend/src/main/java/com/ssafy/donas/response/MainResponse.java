package com.ssafy.donas.response;

import java.util.List;


import com.ssafy.donas.domain.quest.QuestInfo;
import com.ssafy.donas.domain.quest.QuestMainInfo;

import io.swagger.annotations.ApiModelProperty;

public class MainResponse {
	@ApiModelProperty(value = "questCnt", position = 1)
	public long questCnt;
	@ApiModelProperty(value = "mileage", position = 2)
	public long donation;
	@ApiModelProperty(value = "quarter", position = 3)
	public String quarter;
	@ApiModelProperty(value = "questP", position = 4)
	public List<QuestMainInfo> questP;
	@ApiModelProperty(value = "questG", position = 5)
	public List<QuestMainInfo> questG;
	@ApiModelProperty(value = "questR", position = 6)
	public List<QuestMainInfo> questR;
}
