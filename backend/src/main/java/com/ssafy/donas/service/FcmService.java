package com.ssafy.donas.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
//import com.google.common.net.HttpHeaders;
import com.ssafy.donas.domain.FcmMessage;


@Service
@Transactional
public class FcmService {
	
	private final String API_URL=
			"https://fcm.googleapis.com/fcm/send";
//	private final String API_URL=
//			"https://fcm.googleapis.com/v1/projects/donas-d47d8/messages:send";
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public void sendMessageTo(String targetToken,String title, String body) throws IOException {
		String message = makeMessage(targetToken, title, body);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("body", message);
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "key=AAAAlALZIPc:APA91bFPmuJhhTAeltNqFrm5OpS0Y7FVJHTQrr685xhCdqZmM5QUVU3u1NAXp6NtgeVg0M6w88tDeGvL30aHOUIdL8o7nDQlT_Nr90CgVPjSpj6lGORaXmpaVBx6k4VQla_rsuP5ZVxL");

		HttpEntity<String> enti = new HttpEntity<String>(message,headers);

		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> response = rest.exchange(API_URL, HttpMethod.POST, enti, String.class); 
			}
	
	private String makeMessage(String targetToken,String title,String message) throws JsonProcessingException{
		FcmMessage fcmMessage = FcmMessage.builder()
						.notification(FcmMessage.Notification.builder()
								.title(title)
								.message(message)
								.build()
								)
						.to(targetToken)
								.build();
		
		return objectMapper.writeValueAsString(fcmMessage);
	}
}
