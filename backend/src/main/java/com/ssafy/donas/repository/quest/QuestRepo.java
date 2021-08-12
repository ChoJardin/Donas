package com.ssafy.donas.repository.quest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.donas.domain.quest.Quest;
 
public interface QuestRepo extends JpaRepository<Quest, Long> {
	List<Quest> findTop10ByTypeOrderByIdDesc(String type);
	List<Quest> findQuestByType(String type);
	
	@Query(value = "SELECT * FROM Quest q WHERE q.startAt>= :date")
	List<Quest> findProgressQuestByDate(LocalDateTime date);
}
