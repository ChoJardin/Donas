package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.donas.domain.Cash;
import com.ssafy.donas.domain.User;

public interface CashRepo extends JpaRepository<Cash, Long>{
	@Query(value = "SELECT sum(amount) FROM Cash WHERE user_id = :user")
	public Long sumCashById(@Param("user") User user);
	
	List<Cash> findCashByUser(User user); 
}
