package com.ssafy.donas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.QuestAlarm;
import com.ssafy.donas.domain.User;

public interface QuestAlarmRepo extends JpaRepository<QuestAlarm, Long> {
	Optional<QuestAlarm> findQuestAlarmById(long id);
	List<QuestAlarm> findQuestAlarmByUser(User user);
}
