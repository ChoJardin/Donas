package com.ssafy.donas.service;

import java.util.ArrayList;
import java.util.List;
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
		
		follower.getFollowers().add(follow);
		followee.getFollowees().add(follow);
		
		return true;
	}

	public void delete(User follower, User followee) {
		Optional<Follow> follow = followRepo.findFollowByFollowerAndFollowee(follower, followee);		
		
		follower.getFollowers().remove(follow.get());
		followee.getFollowees().remove(follow.get());
		
		follow.ifPresent(selectFollow ->{
			followRepo.delete(selectFollow);
		});
		
	}
	
	public List<User> getFollowerList(User user){
		List<Long> follower_ids = followRepo.findByFollower(user);
		
		List<User> followers = new ArrayList<>();
		for(long user_id : follower_ids)
			followers.add(userRepo.getById(user_id));
		
		return followers;
	}
	
	public List<User> getFolloweeList(User user){
		List<Long> followee_ids = followRepo.findByFollowee(user);
		System.out.println(followee_ids);
		
		List<User> followees = new ArrayList<User>();
		for(long user_id : followee_ids)
			followees.add(userRepo.getById(user_id));
		
		return followees;
	}

}
