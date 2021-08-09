package com.ssafy.donas.repository.quest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.quest.Relay;
 
public interface RelayRepo extends JpaRepository<Relay, Long>{
	Relay getById(long id);	
}
