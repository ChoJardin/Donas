package com.ssafy.donas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ssafy.donas.domain.Alarm;
import com.ssafy.donas.domain.User;

public interface AlarmRepo extends JpaRepository<Alarm, Long>{
	
	List<Alarm> findAlarmByUser(User user);
	
	Optional<Alarm> findAlarmById(long id);
}
