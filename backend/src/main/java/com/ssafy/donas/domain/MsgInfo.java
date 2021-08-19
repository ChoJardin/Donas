package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class MsgInfo {
	
	private long id;
	private int confirm;
	private long otherId;
	private String otherName;
	private String otherPicture;
	private String content;
	private LocalDateTime lastTime;
	private int own;
	
	@Builder
	public MsgInfo(long id,int confirm, long otherId,String otherName, String otherPicture, String content, LocalDateTime lastTime,int own) {
		this.id = id;
		this.confirm = confirm;
		this.otherId = otherId;
		this.otherName = otherName;
		this.otherPicture = otherPicture;
		this.content = content;
		this.lastTime = lastTime;
		this.own = own;
	}
}
