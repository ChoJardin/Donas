package com.ssafy.donas.repository.quest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.quest.Personal;

public interface PersonalRepo extends JpaRepository<Personal, Long>{
//	List<Personal> findPersonalByUserId(long userId);
}
