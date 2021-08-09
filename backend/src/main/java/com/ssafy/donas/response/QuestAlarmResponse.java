package com.ssafy.donas.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class QuestAlarmResponse {
	@ApiModelProperty(value="id",required = true, position = 1)
	public long id;
	@ApiModelProperty(value ="userId", required = true , position = 2)
	public long receiveId;
	@ApiModelProperty(value = "sendName", required = true, position = 3)
	public String sendName;
	@ApiModelProperty(value = "questId", required = true, position = 4)
	public long questId;
	@ApiModelProperty(value="content", required = true, position = 5)
	public String content;
	@ApiModelProperty(value="sendTime",required = true, position = 6)
	public LocalDateTime sendTime;
}
