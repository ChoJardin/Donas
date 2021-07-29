package com.ssafy.donas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Quest;
import com.ssafy.donas.repository.QuestRepo;

@Service
@Transactional
public class QuestService {
	@Autowired
	QuestRepo questRepo;
	
//	public List<Quest> getQuestsByUserId(long UserId){
//		return questRepo.findAllByUserId(UserId);
//	}
}
