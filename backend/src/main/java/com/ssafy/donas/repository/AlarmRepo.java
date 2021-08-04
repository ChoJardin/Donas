package com.ssafy.donas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Alarm;

public interface AlarmRepo extends JpaRepository<Alarm, Long>{
	

}
