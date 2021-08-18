package com.ssafy.donas.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class LikeInfo {
	private long id;
	private String nickname;
	private String picture;
	public LikeInfo(long id, String nickname, String picture) {

		this.id = id;
		this.nickname = nickname;
		this.picture = picture;
	}
}
