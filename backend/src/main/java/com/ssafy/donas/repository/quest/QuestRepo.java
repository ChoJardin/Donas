package com.ssafy.donas.repository.quest;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ssafy.donas.domain.quest.Quest;
 
public interface QuestRepo extends JpaRepository<Quest, Long> {
	List<Quest> findTop10ByTypeOrderByIdDesc(String type);
	List<Quest> findQuestByType(String type);

}
