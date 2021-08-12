package com.ssafy.donas.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Follow;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.request.FollowRequest;
import com.ssafy.donas.response.AllProfileResponse;
import com.ssafy.donas.response.FollowResponse;
import com.ssafy.donas.response.IdResponse;
import com.ssafy.donas.response.ProfileResponse;
import com.ssafy.donas.service.AlarmService;
import com.ssafy.donas.service.ArticleService;
import com.ssafy.donas.service.FollowService;
import com.ssafy.donas.service.PushService;
import com.ssafy.donas.service.QuestService;
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
	
	@Autowired
	QuestService questService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	AlarmService alarmService;
	
	@Autowired
	PushService pushService;

	@GetMapping("/userid")
	@ApiOperation(value = "닉네임으로 id 가져오기")
	public Object getIdByNickname(@RequestParam String nickname) {
		long id = userService.getIdByNickname(nickname);

		if (id == -1)
			return HttpStatus.NOT_FOUND;

		final IdResponse result = new IdResponse();
		ResponseEntity response = null;

		result.id = id;
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/{nickname}")
	@ApiOperation(value = "개인 프로필 보기")
	public Object showProfile(@PathVariable String nickname, @RequestParam long myid) {
		User user = userService.getUser(userService.getIdByNickname(nickname));

		if (user == null)
			return HttpStatus.NOT_FOUND;

		final AllProfileResponse result = new AllProfileResponse();
		ResponseEntity response = null;
		
		// User 정보
		result.id = user.getId();
		result.nickname = user.getNickname();
		result.picture = user.getPicture();
		result.description = user.getDescription();
		
		// Quest 정보
		result.quests = questService.getQuestInfoByUserId(user.getId());
		result.questCnt = user.getQuestCnt();
		result.questPercent = user.getQuestPercent();
		
		// Follow 정보
		result.follower = user.getFollower().size();
		result.following = user.getFollowing().size();
		result.isFollowing = followService.isFollowing(userService.getUser(myid), user);

		//Article 정보
		result.articles = articleService.getArticleInfosByUser(user);
		
		response = new ResponseEntity<>(result, HttpStatus.OK);
		System.out.println(result.articles);
		return response;
	}

	@GetMapping("/mine/{id}")
	@ApiOperation(value = "본인 프로필 보기")
	public Object myProfile(@PathVariable long id) {
		User user = userService.getUser(id);

		if (user == null)
			return HttpStatus.NOT_FOUND;

		final ProfileResponse result = new ProfileResponse();
		ResponseEntity response = null;

		// User 정보
		result.nickname = user.getNickname();
		result.picture = user.getPicture();	
		result.description = user.getDescription();
		result.questCnt = user.getQuestCnt();

		// Follow 정보
		result.follower = user.getFollower().size();
		result.following = user.getFollowing().size();
		result.isFollowing = false;

		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
	
//	@GetMapping("/mine/")
	

	@GetMapping("/other")
	@ApiOperation(value = "타 유저 프로필 보기")
	public Object otherProfile(@RequestParam long myid, @RequestParam long otherid) {
		User me = userService.getUser(myid);
		User other = userService.getUser(otherid);

		if (me == null || other == null)
			return HttpStatus.NOT_FOUND;

		final ProfileResponse result = new ProfileResponse();
		ResponseEntity response = null;

		// User 정보
		result.nickname = other.getNickname();
		result.picture = other.getPicture();
		result.description = other.getDescription();
		result.questCnt = other.getQuestCnt();

		// Follow 정보
		result.follower = other.getFollower().size();
		result.following = other.getFollowing().size();
		result.isFollowing = followService.isFollowing(me, other);

		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/followings")
	@ApiOperation(value = "팔로잉(내가 팔로우한) 리스트")
	public Object followingList(@RequestParam long id) {
		User user = userService.getUser(id);

		if (user == null)
			return HttpStatus.NOT_FOUND;

		final List<FollowResponse> results = new ArrayList<FollowResponse>();
		ResponseEntity response = null;

		for (Follow f : user.getFollowing()) {
			FollowResponse result = new FollowResponse();
			User following = f.getFollowTo();
			result.id = following.getId();
			result.picture = following.getPicture();
			result.nickname = following.getNickname();

			results.add(result);
		}

		response = new ResponseEntity<>(results, HttpStatus.OK);
		return response;
	}

	@GetMapping("/followers")
	@ApiOperation(value = "팔로워(나를 팔로우한) 리스트")
	public Object followerList(@RequestParam long id) {
		User user = userService.getUser(id);

		if (user == null)
			return HttpStatus.NOT_FOUND;

		final List<FollowResponse> results = new ArrayList<FollowResponse>();
		ResponseEntity response = null;

		for (Follow f : user.getFollower()) {
			FollowResponse result = new FollowResponse();
			User follower = f.getFollowFrom();
			result.id = follower.getId();
			result.picture = follower.getPicture();
			result.nickname = follower.getNickname();

			results.add(result);
		}

		response = new ResponseEntity<>(results, HttpStatus.OK);
		return response;
	}

	@PostMapping("/follow")
	@ApiOperation(value = "팔로우 시작(추가)")
	public Object startFollow(@RequestBody FollowRequest request) {
		User follower = userService.getUser(request.getFollowerId());
		User followee = userService.getUser(request.getFolloweeId());

		if (!followService.addFollow(follower, followee))
			return HttpStatus.CONFLICT;
		
		if(!alarmService.addAlarm(followee,follower.getNickname(),-1, follower.getNickname()+"님이 회원님을 팔로우하기 시작했습니다.", LocalDateTime.now()))
			return HttpStatus.CONFLICT;

		return HttpStatus.OK;
	}
	
	
	

	@DeleteMapping("/follow")
	@ApiOperation(value = "팔로우 취소")
	public Object cancleFollow(@RequestParam long followerId, @RequestParam long followeeId) {
		User follower = userService.getUser(followerId);
		User followee = userService.getUser(followeeId);

		if (!followService.isFollowing(follower, followee))
			return HttpStatus.NOT_FOUND;

		followService.delete(follower, followee);

		return HttpStatus.OK;
	}

}
