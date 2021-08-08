package com.ssafy.donas.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class AlarmResponse {
	@ApiModelProperty(value ="userId", required = true , position = 1)
	public long receiveId;
	@ApiModelProperty(value="content", required = true, position = 2)
	public String content;
	@ApiModelProperty(value="sendTime",required = true, position = 3)
	public LocalDateTime sendTime;
	@ApiModelProperty(value="confirm", required = true, position = 4)
	public int confirm;
}
