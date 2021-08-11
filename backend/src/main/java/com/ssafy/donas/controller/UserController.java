package com.ssafy.donas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.request.CheckPasswordRequest;
import com.ssafy.donas.request.SigninRequest;
import com.ssafy.donas.request.SignoutRequest;
import com.ssafy.donas.request.SignupRequest;
import com.ssafy.donas.request.UserInfoRequest;
import com.ssafy.donas.response.LoginResponse;
import com.ssafy.donas.response.MypageResponse;
import com.ssafy.donas.service.MileageService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	MileageService mileageService;

	@PostMapping("/signin")
	@ApiOperation(value = "로그인")
	public Object login(@Valid @RequestBody SigninRequest request) {
		final LoginResponse result = new LoginResponse();
		ResponseEntity response = null;
		
		String email = request.getEmail();
		String password = request.getPassword();
		String token = request.getToken();
		User user = userService.checkPassword(email, password, token);
		
		if(user==null)
			return HttpStatus.NOT_FOUND;
		
		// 로그인 아이디별 토큰 저장	
		
		result.id = user.getId();
		result.nickname = user.getNickname();
		result.questCnt = user.getQuestCnt();
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}
	
	@DeleteMapping("/signout")
	@ApiOperation(value = "로그아웃")
	public Object logout(@Valid @RequestBody SignoutRequest request) {
		long userId = request.getUserId();		
		String token = request.getToken();
		if(!userService.logOut(userId, token))
			return HttpStatus.NOT_FOUND;			
		return HttpStatus.OK;
	}
	
	@GetMapping("/email")
	@ApiOperation(value = "이메일 중복 확인")
	public Object checkEmail(@RequestParam String email) {
		if(userService.checkEmail(email))
			return HttpStatus.CONFLICT;
		
		return HttpStatus.OK;
	}
	
	@GetMapping("/nickname")
	@ApiOperation(value = "닉네임 중복 확인")
	public Object checkNickname(@RequestParam String nickname) {
		// 수정함
		if(userService.checkNickname(nickname) || "edit".equals(nickname.toLowerCase()) || "undefined".equals(nickname.toLowerCase()))
			return HttpStatus.CONFLICT;
		
		return HttpStatus.OK;
	}
	
	@PostMapping("/signup")
	@ApiOperation(value = "회원가입")
	public Object join(@Valid @RequestBody SignupRequest request) {		
		if(!userService.join(request.getEmail(), request.getPassword(), request.getNickname()))
			return HttpStatus.CONFLICT;
		
		return HttpStatus.OK;
	}	
	
	@PatchMapping("/nickname/{id}")
	@ApiOperation(value = "닉네임 추가")
	public Object addNickname(@PathVariable long id, @RequestBody UserInfoRequest request) {
		if(!userService.addNickname(id, request.getNickname()))
			return HttpStatus.CONFLICT;
		
		return HttpStatus.OK;
	}
	
	@PostMapping("/password")
	@ApiOperation(value = "비밀번호 설정")
	public Object checkPassword(@RequestBody CheckPasswordRequest request) {
		// 비밀번호 확인
		if(!userService.checkPassword(request.getId(), request.getPassword()))
			return HttpStatus.NOT_FOUND;
		
		// 비밀번호 설정
		userService.updatePassword(request.getId(), request.getNewPassword());
		
		return HttpStatus.OK;
	}
	
	@PatchMapping("/profile/{id}")
	@ApiOperation(value = "회원 정보 변경")
	public Object updateUserInfo(@PathVariable long id, @RequestBody UserInfoRequest request) {
		if(!userService.updateUserInfo(id, request.getNickname(), request.getPicture(), request.getDescription()))
			return HttpStatus.BAD_REQUEST;
		
		return HttpStatus.OK;
	}
	@GetMapping("/mypage/{id}")
	@ApiOperation(value = "마이페이지")
	public Object showMypage(@PathVariable long id) {
		User user = userService.getUser(id);		
		if(user == null)
			return HttpStatus.NOT_FOUND;		
		final MypageResponse result = new MypageResponse();
		ResponseEntity response = null;	
		result.nickname = user.getNickname();
		result.email = user.getEmail();
		result.picture = user.getPicture();
		result.description = user.getDescription();
		result.mileage = user.getMileage();
		result.questCnt = user.getQuestCnt();
		result.questPercent = user.getQuestPercent();
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}	
	
	@PatchMapping("/mileage/{userId}")
	@ApiOperation(value = "마일리지 차감")
	public Object minusMileage(@PathVariable long userId, @RequestParam long amount) {
		if(!userService.checkId(userId))
			return new ResponseEntity<>("유저 없음",HttpStatus.NOT_FOUND);
				
		mileageService.minusMileage(userId, amount);
		
		return HttpStatus.OK;
	}
	
}
