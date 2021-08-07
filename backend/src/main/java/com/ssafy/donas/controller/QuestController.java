package com.ssafy.donas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Personal;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestInfo;
import com.ssafy.donas.domain.quest.QuestParticipants;
import com.ssafy.donas.request.AddGroupQuestRequest;
import com.ssafy.donas.request.AddPersonalQuestRequest;
import com.ssafy.donas.request.UpdateQuestRequest;
import com.ssafy.donas.response.QuestResponse;
import com.ssafy.donas.service.QuestParticipantsService;
import com.ssafy.donas.service.QuestService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/quest")
@RequiredArgsConstructor
public class QuestController {
	
	@Autowired
	UserService userService;

	@Autowired
	QuestService questService;
	
	@Autowired
	QuestParticipantsService questParticipantsService;

	/*
	 * Quest 생성 : 개인, 공동 (릴레이 없음)
	 * */
	@PostMapping("/personal")
	@ApiOperation(value = "개인퀘스트 생성")
	public Object addPersonalQuest(@RequestBody AddPersonalQuestRequest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;
		
		if ("".equals(quest.getTitle()) || "".equals(quest.getDescription()))
			return HttpStatus.NO_CONTENT;
		
		questService.addPersonalQuest(quest.getUserId(), quest.getTitle(), quest.getDescription(), quest.getStartAt(), quest.getFinishAt(), quest.getPicture(), quest.getCertification(), quest.getMileage());
		
		return HttpStatus.OK;
	}

	@PostMapping("/group")
	@ApiOperation(value = "공동퀘스트 생성")
	public Object addGroupQuest(@RequestBody AddGroupQuestRequest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;
		
		if ("".equals(quest.getTitle()) || "".equals(quest.getDescription()))
			return HttpStatus.NO_CONTENT;
		
		List<Long> participant_users = quest.getParticipants();
		List<User> participants = new ArrayList<>();
		for (long p : participant_users) {
			if (!userService.checkId(p))
				return HttpStatus.NOT_FOUND;
			
			participants.add(userService.getUser(p));
		}
		
		Quest groupQuest = questService.addGroupQuest(quest.getTitle(), quest.getDescription(), quest.getStartAt(), quest.getFinishAt(), quest.getPicture(), quest.getCertification(), quest.getMileage());
		questParticipantsService.addParticipants(userService.getUser(quest.getUserId()), participants, groupQuest);
		
		return HttpStatus.OK;
	}
	
//	@PostMapping("/relay")
//	@ApiOperation(value = "릴레이퀘스트 생성")
	
	
	
	/*
	 * Quest 수정 : 공통
	 * */
	@PatchMapping
	@ApiOperation(value = "퀘스트 수정 (title, description 수정)")
	public Object updateQuest(@RequestBody UpdateQuestRequest quest) {
		if (!questService.checkQuest(quest.getQuestId()))
			return HttpStatus.NOT_FOUND;
		
		questService.updateQuest(quest.getQuestId(), quest.getTitle(), quest.getDescription());
		
		return HttpStatus.OK;
	}

	/*
	 * Quest 삭제 : 공통
	 * */
	@DeleteMapping
	@ApiOperation(value = "퀘스트 삭제")
	public Object deleteQuest(@RequestParam long questId) {
		if(!questService.delete(questId))
			return HttpStatus.NOT_FOUND;
		
		return HttpStatus.OK;
	}
	
	/*
	 * 유저의 Quest 목록 : all, 개인, 릴레이
	 * */
	@GetMapping("/{userId}")
	@ApiOperation(value = "유저 별 참여중인 모든 퀘스트 가져오기")
	public Object getQuestByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;

		User user = userService.getUser(userId);
		List<QuestParticipants> questSummaries = user.getMyQuests();

		List<Quest> quests = new ArrayList<>();
		for (QuestParticipants qs : questSummaries)
			quests.add(qs.getQuest());

		List<QuestResponse> result = new ArrayList<>();
		for (Quest quest : quests) {
			QuestResponse res = new QuestResponse();
			res.id = quest.getId();
			res.title = quest.getTitle();
			res.description = quest.getDescription();
			res.type = quest.getType();
			res.startAt = quest.getStartAt();
			res.finishAt = quest.getFinishAt();
			res.picture = quest.getPicture();
			res.mileage = quest.getMileage();
			result.add(res);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/personal/{userId}")
	@ApiOperation(value = "유저별 참여중인 개인 퀘스트 가져오기")
	public Object getPersonalByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		
		List<QuestInfo> quests = questService.getQuestInfoByUserId(userId);

		final List<QuestResponse> result = new ArrayList<>();
		for (QuestInfo quest : quests) {
			if ("P".equals(quest.getType())) {
				QuestResponse qr = new QuestResponse();
				qr.id = quest.getId();
				qr.title = quest.getTitle();
				qr.description = quest.getDescription();
				qr.picture = quest.getPicture();
				qr.startAt = quest.getStartAt();
				qr.finishAt = quest.getFinishAt();
				qr.type = "P";
				qr.mileage = quest.getMileage();
				result.add(qr);
			}
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/group/{userId}")
	@ApiOperation(value = "유저별 참여중인 공동 퀘스트 가져오기")
	public Object getGroupByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		
		List<QuestInfo> quests = questService.getQuestInfoByUserId(userId);

		final List<QuestResponse> result = new ArrayList<>();
		for (QuestInfo quest : quests) {
			if ("G".equals(quest.getType())) {
				QuestResponse qr = new QuestResponse();
				qr.id = quest.getId();
				qr.title = quest.getTitle();
				qr.description = quest.getDescription();
				qr.picture = quest.getPicture();
				qr.startAt = quest.getStartAt();
				qr.finishAt = quest.getFinishAt();
				qr.type = "G";
				qr.mileage = quest.getMileage();
				result.add(qr);
			}
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/relay/{userId}")
	@ApiOperation(value = "유저별 참여중인 릴레이 퀘스트 가져오기")
	public Object getRelayByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;

		List<QuestInfo> quests = questService.getQuestInfoByUserId(userId);

		final List<QuestResponse> result = new ArrayList<>();
		for (QuestInfo quest : quests) {
			if ("R".equals(quest.getType())) {
				QuestResponse qr = new QuestResponse();
				qr.id = quest.getId();
				qr.title = quest.getTitle();
				qr.description = quest.getDescription();
				qr.picture = quest.getPicture();
				qr.startAt = quest.getStartAt();
				qr.finishAt = quest.getFinishAt();
				qr.type = "R";
				qr.mileage = quest.getMileage();
				result.add(qr);
			}
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
