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
	private long otherId;
	private String otherPicture;
	private String content;
	private LocalDateTime lastTime;
	
	@Builder
	public MsgInfo(long id, long otherId, String otherPicture, String content, LocalDateTime lastTime) {
		this.id = id;
		this.otherId = otherId;
		this.otherPicture = otherPicture;
		this.content = content;
		this.lastTime = lastTime;
	}
}
