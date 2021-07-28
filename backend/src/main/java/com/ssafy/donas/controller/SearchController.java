package com.ssafy.donas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Search;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.response.FollowResponse;
import com.ssafy.donas.response.MypageResponse;
import com.ssafy.donas.response.RecentWordResponse;
import com.ssafy.donas.response.SearchResponse;
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

	@GetMapping("/recent/{id}")
	@ApiOperation(value = "최근 검색어")
	public Object getRecentWords(@PathVariable long id) {
		User user = userService.getUser(id);

		if (user == null)
			return HttpStatus.NOT_FOUND;

		final List<RecentWordResponse> results = new ArrayList<>();
		ResponseEntity response = null;

		// 최근 검색어 5개만 가져오기
		for (int i = 0; i < 5; i++) {
			if (i >= user.getSearchWords().size())
				break;

			RecentWordResponse result = new RecentWordResponse();
			Search s = user.getSearchWords().get(i);
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
		
		int start = offset*5;
		// 자동완성 검색어 5개만 가져오기
		for (int i = start; i < start+5; i++) {
			if (i >= list.size())
				break;

			SearchResponse result = new SearchResponse();
			User user = list.get(i);
			result.nickname = user.getNickname();
			result.picture = user.getPicture();
			result.description = user.getDescription();

			results.add(result);
		}

		response = new ResponseEntity<>(results, HttpStatus.OK);
		return response;
	}
	

}
