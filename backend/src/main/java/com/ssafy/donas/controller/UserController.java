package com.ssafy.donas.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.SigninRequest;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.response.BasicResponse;
import com.ssafy.donas.response.ErrorResponse;
import com.ssafy.donas.response.LoginResponse;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
		final ErrorResponse error = new ErrorResponse();
		ResponseEntity response = null;
		
		String email = request.getEmail();
		String password = request.getPassword();

		if (!userService.checkEmail(email)) {
			error.reason = "email";
			response = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
			return response;
		}
		
		User user = userService.checkPassword(email, password);
		
		if(user==null) {
			error.reason = "password";
			response = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
			return response;
		}

		result.nickname = user.getNickname();
		result.questCnt = user.getQuestCnt();
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}

}
