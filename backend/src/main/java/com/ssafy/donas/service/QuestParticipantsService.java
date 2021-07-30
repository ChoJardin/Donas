package com.ssafy.donas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.repository.QuestParticipantsRepo;

@Service
@Transactional
public class QuestParticipantsService {
	
	@Autowired
	QuestParticipantsRepo questParticipantsRepo;
	
	
}
