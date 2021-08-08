package com.ssafy.donas.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class AlarmResponse {
	@ApiModelProperty(value="id",required = true,position = 1)
	public long id;	
	@ApiModelProperty(value ="receiveId", required = true , position = 2)
	public long receiveId;
	@ApiModelProperty(value="sendName",required = true,position = 3)
	public String sendName;
	@ApiModelProperty(value="articleId", required = true, position = 4)
	public long articleId;
	@ApiModelProperty(value="content", required = true, position = 5)
	public String content;
	@ApiModelProperty(value="sendTime",required = true, position = 6)
	public LocalDateTime sendTime;
	@ApiModelProperty(value="confirm", required = true, position = 7)
	public int confirm;
}
