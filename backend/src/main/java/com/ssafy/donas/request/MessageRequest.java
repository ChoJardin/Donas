package com.ssafy.donas.request;

import javax.validation.Valid;

import com.google.firebase.database.annotations.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class MessageRequest {
	@ApiModelProperty(required = true)
	@NotNull
	long sendId;
	
	@ApiModelProperty(required = true)
	@NotNull
	long receiveId;
	
	@ApiModelProperty(required = true)
	@NotNull
	String content;

	public long getSendId() {
		return sendId;
	}

	public void setSendId(long sendId) {
		this.sendId = sendId;
	}

	public long getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(long receiveId) {
		this.receiveId = receiveId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
