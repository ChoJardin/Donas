package com.ssafy.donas.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Search;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.SearchRepo;

@Service
@Transactional
public class SearchService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SearchRepo searchRepo;

	public boolean addSearchWord(long id, String nickname) {
		User user = userService.getUser(id);
		
		if(user==null)
			return false;
		
		Search search = new Search(user, nickname);
		searchRepo.save(search);
		
		return true;
	}
	
}
