package com.ssafy.donas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Like;
import com.ssafy.donas.domain.quest.QuestParticipants;

public interface QuestParticipantsRepo extends JpaRepository<QuestParticipants, Long>{

}
