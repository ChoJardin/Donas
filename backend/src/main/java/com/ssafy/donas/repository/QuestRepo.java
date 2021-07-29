package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ssafy.donas.domain.Quest;

public interface QuestRepo extends JpaRepository<Quest, Long> {
//	public List<Quest> findQuestByUserId(long userId);
}
