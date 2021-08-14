package com.ssafy.donas.domain;

import com.google.firebase.messaging.Notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class FcmMessage {
		private Notification notification;
		private Data data;
		private String to;		

	@Builder
	@AllArgsConstructor
	@Getter
	public static class Data{
		private String title;
		private String message;
	}
	
	@Builder
	@AllArgsConstructor
	@Getter
	public static class Notification{
		private String title;
		private String message;
	}

}
