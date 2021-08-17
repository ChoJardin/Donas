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
	private String otherImage;
	private String content;
	private LocalDateTime lastTime;
	
	@Builder
	public MsgInfo(long id, long otherId, String otherImage, String content, LocalDateTime lastTime) {
		super();
		this.id = id;
		this.otherId = otherId;
		this.otherImage = otherImage;
		this.content = content;
		this.lastTime = lastTime;
	}

}
