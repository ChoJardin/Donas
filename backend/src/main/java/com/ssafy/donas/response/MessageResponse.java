package com.ssafy.donas.response;

import java.util.List;

import com.ssafy.donas.domain.MessageInfo;

import io.swagger.annotations.ApiModelProperty;

public class MessageResponse {
	@ApiModelProperty(value = "id",required = true, position =1)
	public long id;
	@ApiModelProperty(value = "other", required = true, position =2)
	public String otherName;
	@ApiModelProperty(value = "otherPicture", required = true, position = 3)
	public String otherPicture;
	@ApiModelProperty(value = "messages",required = true, position =4)
	public List<MessageInfo> messages;
}
