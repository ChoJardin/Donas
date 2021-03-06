package com.ssafy.donas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Follow;
import com.ssafy.donas.domain.Search;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.request.SearchWordRequest;
import com.ssafy.donas.response.FollowResponse;
import com.ssafy.donas.response.MypageResponse;
import com.ssafy.donas.response.RecentWordResponse;
import com.ssafy.donas.response.SearchResponse;
import com.ssafy.donas.service.FollowService;
import com.ssafy.donas.service.SearchService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	UserService userService;

	@Autowired
	SearchService searchService;
	
	@Autowired
	FollowService followService;

	@GetMapping("/recent/{id}")
	@ApiOperation(value = "최근 검색어")
	public Object getRecentWords(@PathVariable long id) {
		User user = userService.getUser(id);

		if (user == null)
			return HttpStatus.NOT_FOUND;

		final List<RecentWordResponse> results = new ArrayList<>();
		ResponseEntity response = null;

		int start = user.getSearchWords().size()-1;
		// 최근 검색어 5개만 가져오기(최신순으로)
		for (int i = start; i > start-5; i--) {
			if (i < 0)
				break;

			RecentWordResponse result = new RecentWordResponse();
			Search s = user.getSearchWords().get(i);
			result.id = userService.getIdByNickname(s.getNickname());
			result.nickname = s.getNickname();
			result.searchTime = s.getSearchTime();

			results.add(result);
		}

		response = new ResponseEntity<>(results, HttpStatus.OK);
		return response;
	}

	@GetMapping("/auto")
	@ApiOperation(value = "검색어 자동완성")
	public Object autoCompleteWords(@RequestParam String nickname) {
		List<User> list = userService.findByNicknameStartsWith(nickname);
		if (list.size() == 0)
			return HttpStatus.NO_CONTENT;

		final List<SearchResponse> results = new ArrayList<>();
		ResponseEntity response = null;

		// 자동완성 검색어 5개만 가져오기
		for (int i = 0; i < 5; i++) {
			if (i >= list.size())
				break;

			SearchResponse result = new SearchResponse();
			User user = list.get(i);
			result.id = user.getId();
			result.nickname = user.getNickname();
			result.picture = user.getPicture();
			result.description = user.getDescription();

			results.add(result);
		}

		response = new ResponseEntity<>(results, HttpStatus.OK);
		return response;
	}

	@GetMapping("/result")
	@ApiOperation(value = "검색 결과 반환")
	public Object getSearchResponse(@RequestParam String nickname, @RequestParam int offset) {
		// 검색 결과 반환
		List<User> list = userService.findByNicknameStartsWith(nickname);
		if (list.size() == 0)
			return HttpStatus.NO_CONTENT;

		final List<SearchResponse> results = new ArrayList<>();
		ResponseEntity response = null;

		int start = offset * 5;
		// 자동완성 검색어 5개만 가져오기
		for (int i = start; i < start + 5; i++) {
			if (i >= list.size())
				break;

			SearchResponse result = new SearchResponse();
			User user = list.get(i);
			result.id = user.getId();
			result.nickname = user.getNickname();
			result.picture = user.getPicture();
			result.description = user.getDescription();

			results.add(result);
		}

		response = new ResponseEntity<>(results, HttpStatus.OK);
		return response;
	}

	@PostMapping("/add")
	@ApiOperation(value = "검색어 저장")
	public Object addSearchWord(@Valid @RequestBody SearchWordRequest request) {
		long id = request.getId();
		String nickname = request.getNickname();
		
		User user = userService.getUser(id);

		if (!searchService.addSearchWord(id, nickname))
			return HttpStatus.NOT_FOUND;

		return HttpStatus.OK;
	}
	
	@GetMapping("/friends/{id}")
	@ApiOperation(value = "맞팔 리스트 불러오기")
	public Object getFriendsList(@PathVariable long id) {
		if(!userService.checkId(id))
			return HttpStatus.NOT_FOUND;

		User user = userService.getUser(id);
			
		List<User> followers = followService.getFollowerList(user);
		List<User> followees = followService.getFollowingList(user);
		
		final List<SearchResponse> results = new ArrayList<>();
		ResponseEntity response = null;
		
		for(User u : followers) {
			if(followees.contains(u)) {
				SearchResponse res = new SearchResponse();
				res.id = u.getId();
				res.nickname = u.getNickname();
				res.picture = u.getPicture();
				res.description = u.getDescription();
				results.add(res);
			}
		}
		
		response = new ResponseEntity<>(results, HttpStatus.OK);
		return response;
	}

}
