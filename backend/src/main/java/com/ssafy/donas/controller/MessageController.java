package com.ssafy.donas.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.request.MessageRequest;
import com.ssafy.donas.service.MessageService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	
	@PostMapping
	@ApiOperation(value = "메세지 보내기")
	public Object sendMessage(@RequestBody MessageRequest request) {
		long sendId = request.getSendId();
		long receiveId = request.getReceiveId();
		String content = request.getContent();
		LocalDateTime sendTime = LocalDateTime.now().plusHours(9);		
		if(userService.getUser(sendId)==null || userService.getUser(receiveId)==null)
			return HttpStatus.NOT_FOUND;						
		if(!messageService.sendMessage(sendId, receiveId, content, sendTime))
			return HttpStatus.BAD_REQUEST;
		
		return HttpStatus.OK;
	}

}
