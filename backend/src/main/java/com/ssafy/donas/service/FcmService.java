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
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public void sendMessageTo(String targetToken,String title, String body) throws IOException {
		String message = makeMessage(targetToken, title, body);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("body", message);
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "key=AAAAcu-alS0:APA91bGmRJ_HMEKTqqxv4PZFzOBuaEK_5Vc5xupiQaXZ_fu5t4D2FHqU2abmbbKknfJfGJEGxBVefwKgNGJQRARyqFVxKX3OBtlvbQOm-CUl4fjYk0S7dqe4pme5O-wGvrTvWU8N9mu-");

		HttpEntity<String> enti = new HttpEntity<String>(message,headers);

		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> response = rest.exchange(API_URL, HttpMethod.POST, enti, String.class); 
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
			}
	
	private String makeMessage(String targetToken,String title,String message) throws JsonProcessingException{
		FcmMessage fcmMessage = FcmMessage.builder()
						.data(FcmMessage.Data.builder()
								.title(title)
								.message(message)
								.build()
								)
						.to(targetToken)
								.build();
		
		return objectMapper.writeValueAsString(fcmMessage);
	}


}
