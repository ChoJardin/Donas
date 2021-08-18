package com.ssafy.donas.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.MsgInfo;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.request.MessageRequest;
import com.ssafy.donas.response.MessageResponse;
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
	
	@GetMapping("/list")
	@ApiOperation(value = "메세지 리스트")
	public Object messageList(@RequestParam long userId) {
		List<MsgInfo> result = messageService.messageList(userId);		
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	@GetMapping("/content")
	@ApiOperation(value = "메세지 내용 보이기")
	public Object showMessages(@RequestParam long userId,@RequestParam long otherId ) {
		User user = userService.getUser(userId);
		User other = userService.getUser(otherId);
		if(user==null || other==null)
			return HttpStatus.NOT_FOUND;
		MessageResponse result = new MessageResponse();
		result.id = messageService.roomId(user, other);
		if(result.id==-1)
			return HttpStatus.NOT_FOUND;
		result.otherName = other.getNickname();
		result.otherPicture = other.getPicture();
		result.messages = messageService.showMessage(user, other);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
