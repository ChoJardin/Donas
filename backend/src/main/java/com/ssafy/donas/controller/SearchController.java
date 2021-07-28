package com.ssafy.donas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Search;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.response.MypageResponse;
import com.ssafy.donas.response.RecentWordResponse;
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
		for(int i=0;i<5;i++) {
			if(i >= user.getSearchWords().size())
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

}
