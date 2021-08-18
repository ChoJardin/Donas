package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class MessageInfo {
	private long id;
	private String content;
	private int own;
	private LocalDateTime time;
	
	public MessageInfo(long id, String content, int own, LocalDateTime time) {
		super();
		this.id = id;
		this.content = content;
		this.own = own;
		this.time = time;
	}

}
