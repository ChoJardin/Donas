package com.ssafy.donas.controller;

import javax.validation.Valid;

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

import com.ssafy.donas.domain.SigninRequest;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.response.LoginResponse;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signin")
	@ApiOperation(value = "로그인")
	public Object login(@Valid @RequestBody SigninRequest request) {
		final LoginResponse result = new LoginResponse();
		ResponseEntity response = null;
		
		String email = request.getEmail();
		String password = request.getPassword();

		if (!userService.checkEmail(email)) {
			result.id = -1;
			result.nickname = "";
			result.questCnt = -1;
			response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
			return response;
		}
		
		User user = userService.checkPassword(email, password);
		
		if(user==null) {
			result.id = -1;
			result.nickname = "";
			result.questCnt = -1;
			response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
			return response;
		}

		result.id = user.getId();
		result.nickname = user.getNickname();
		result.questCnt = user.getQuestCnt();
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}
	
	@GetMapping("/checkem")
	@ApiOperation(value = "이메일 중복 확인")
	public Object checkEmail(@RequestParam String email) {
		if(userService.checkEmail(email))
			return HttpStatus.CONFLICT;
		
		return HttpStatus.OK;
	}
	
	@GetMapping("/checknn")
	@ApiOperation(value = "닉네임 중복 확인")
	public Object checkNickname(@RequestParam String nickname) {
		if(userService.checkNickname(nickname))
			return HttpStatus.CONFLICT;
		
		return HttpStatus.OK;
	}
	
	
	
}
