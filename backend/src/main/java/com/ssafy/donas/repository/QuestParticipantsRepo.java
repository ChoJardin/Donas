package com.ssafy.donas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Like;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestParticipants;

public interface QuestParticipantsRepo extends JpaRepository<QuestParticipants, Long>{

	List<QuestParticipants> findQuestParticipantsByUserAndSuccess(User user, int success);
	List<QuestParticipants> findQuestParticipantsByQuest(Quest quest);
}
