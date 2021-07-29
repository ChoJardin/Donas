package com.ssafy.donas.repository.quest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.quest.Group;

public interface GroupRepo extends JpaRepository<Group, Long>{
}
