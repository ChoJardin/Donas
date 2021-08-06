package com.ssafy.donas.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.donas.domain.Follow;
import com.ssafy.donas.domain.User;

public interface FollowRepo extends JpaRepository<Follow, Long>{
	Optional<Follow> findFollowByFollowerAndFollowee(User follower, User followee);
	
	@Query(value = "SELECT f.followee_id FROM Follow AS f WHERE f.follower_id = :follower", nativeQuery=true)
	List<Long> findByFollower(@Param("follower") User follower);
	
	@Query(value = "SELECT f.follower_id FROM Follow AS f WHERE f.followee_id = :followee", nativeQuery=true)
	List<Long> findByFollowee(@Param("followee") User followee);
}
