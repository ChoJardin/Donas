package com.ssafy.donas.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Follow;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.FollowRepo;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class FollowService {
	
	@Autowired
	FollowRepo followRepo;
	
	@Autowired
	UserRepo userRepo;

	public boolean isFollowing(User me, User other) {
		if(followRepo.findFollowByFollowerAndFollowee(me, other).isEmpty())
			return false;
		
		return true;
	}

	public boolean addFollow(User follower, User followee) {
		if(isFollowing(follower, followee))
			return false;
		
		Follow follow = new Follow(follower, followee);
		followRepo.save(follow);
		
		follower.getFollowees().add(follow);
		followee.getFollowers().add(follow);
		
		return true;
	}

	public void delete(User follower, User followee) {
		Optional<Follow> follow = followRepo.findFollowByFollowerAndFollowee(follower, followee);		
		
		follower.getFollowees().remove(follow.get());
		followee.getFollowers().remove(follow.get());
		
		follow.ifPresent(selectFollow ->{
			followRepo.delete(selectFollow);
		});
		
	}

}
