package com.ssafy.donas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.response.IdResponse;
import com.ssafy.donas.response.ProfileResponse;
import com.ssafy.donas.service.FollowService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	UserService userService;

	@Autowired
	FollowService followService;
	
	@GetMapping("/userid")
	@ApiOperation(value = "닉네임으로 id 가져오기")
	public Object getIdByNickname(@RequestParam String nickname) {
		long id = userService.getIdByNickname(nickname);
		
		if(id == -1)
			return HttpStatus.NOT_FOUND;
		
		final IdResponse result = new IdResponse();
		ResponseEntity response = null;
		
		result.id = id;
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/mine/{id}")
	@ApiOperation(value = "본인 프로필 보기")
	public Object myProfile(@PathVariable long id) {
		User user = userService.getUser(id);
		
		if(user == null)
			return HttpStatus.NOT_FOUND;
		
		final ProfileResponse result = new ProfileResponse();
		ResponseEntity response = null;
		
		// User 정보
		result.nickname = user.getNickname();
		result.picture = user.getPicture();
		result.description = user.getDescription();
		result.questCnt = user.getQuestCnt();
		
		// Follow 정보
		result.follower = user.getFollowers().size();
		result.following = user.getFollowees().size();
		result.isFollowing = false;
		
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

}
