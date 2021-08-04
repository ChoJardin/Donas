package com.ssafy.donas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Follow;
import com.ssafy.donas.domain.User;

public interface FollowRepo extends JpaRepository<Follow, Long>{
	Optional<Follow> findFollowByFollowerAndFollowee(User follower, User followee);
	List<User> findFolloweeByFollower(User follower);
	List<User> findFollowerByFollowee(User followee);
}
