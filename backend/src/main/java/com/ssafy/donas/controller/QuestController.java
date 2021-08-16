package com.ssafy.donas.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.ArticleInfo;
import com.ssafy.donas.domain.ArticleShortInfo;
import com.ssafy.donas.domain.Like;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.UserInfo;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestInfo;
import com.ssafy.donas.domain.quest.QuestParticipants;
import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.request.AddGroupQuestRequest;
import com.ssafy.donas.request.AddPersonalQuestRequest;
import com.ssafy.donas.request.AddRelayQuestRequest;
import com.ssafy.donas.request.ParticiPersonalQuest;
import com.ssafy.donas.request.RelayNextListRequest;
import com.ssafy.donas.request.UpdateQuestRequest;
import com.ssafy.donas.response.QuestDetailResponse;
import com.ssafy.donas.response.QuestResponse;
import com.ssafy.donas.service.ArticleService;
import com.ssafy.donas.service.QuestAlarmService;
import com.ssafy.donas.service.QuestParticipantsService;
import com.ssafy.donas.service.QuestService;
import com.ssafy.donas.service.RelayService;
import com.ssafy.donas.service.RelayWaitService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

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

	@Autowired
	RelayWaitService relayWaitService;

	@Autowired
	QuestAlarmService questAlarmService;

	@Autowired
	RelayService relayService;

	@Autowired
	ArticleService articleService;

	/*
	 * Quest 생성 : 개인, 공동 (릴레이 없음)
	 */

	@PostMapping("/personal")
	@ApiOperation(value = "개인퀘스트 생성")
	public Object addPersonalQuest(@RequestBody AddPersonalQuestRequest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;

		if ("".equals(quest.getTitle()) || "".equals(quest.getDescription()) || "".equals(quest.getCertification()))
			return HttpStatus.NO_CONTENT;

		long questId = questService.addPersonalQuest(quest.getTitle(), quest.getDescription(), quest.getStartAt(),
				quest.getFinishAt(), quest.getPicture(), quest.getCertification(), quest.getMileage());
		questParticipantsService.addParticipant(quest.getUserId(), questId);

		return HttpStatus.OK;
	}
	
	@PostMapping("/personal")
	@ApiOperation(value = "개인퀘스트 참여")
	public Object particiPersonalQuest(@RequestBody ParticiPersonalQuest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;		
		if(questService.getQuestById(quest.getQuestId())==null)
			return HttpStatus.NOT_FOUND;
		questParticipantsService.addParticipant(quest.getUserId(), quest.getQuestId());		
		return HttpStatus.OK;
	}

	@PostMapping("/group")
	@ApiOperation(value = "공동퀘스트 생성")
	public Object addGroupQuest(@RequestBody AddGroupQuestRequest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;

		if ("".equals(quest.getTitle()) || "".equals(quest.getDescription()) || "".equals(quest.getCertification()))
			return HttpStatus.NO_CONTENT;

		Quest groupQuest = questService.addGroupQuest(quest.getTitle(), quest.getDescription(), quest.getStartAt(),
				quest.getFinishAt(), quest.getPicture(), quest.getCertification(), quest.getMileage(),
				quest.getParticipants().size() + 1);

		List<String> participantUsers = quest.getParticipants();
		List<User> participants = new ArrayList<>();
		for (String p : participantUsers) {
			long id = userService.getIdByNickname(p);
			if (id == -1)
				return HttpStatus.NOT_FOUND;

			participants.add(userService.getUser(id));

			// 참가자에게 참여 요청
			questAlarmService.addQuestAlarm(id, groupQuest, userService.getUser(quest.getUserId()).getNickname(),
					"[공동 퀘스트 요청] " + groupQuest.getTitle(), LocalDateTime.now().plusHours(9));
		}

		// 퀘스트 생성자만 DB에 넣어두기 (나머지는 승락하면 넣기!)
		questParticipantsService.addParticipant(quest.getUserId(), groupQuest.getId());

//		questParticipantsService.addParticipants(userService.getUser(quest.getUserId()), participants, groupQuest);

		return HttpStatus.OK;
	}

	@PostMapping("/relay")
	@ApiOperation(value = "릴레이퀘스트 생성")
	public Object addRelayQuest(@RequestBody AddRelayQuestRequest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;

		if ("".equals(quest.getTitle()) || "".equals(quest.getDescription()) || "".equals(quest.getCertification()))
			return HttpStatus.NO_CONTENT;

		long questId = questService.addRelayQuest(quest.getTitle(), quest.getDescription(), quest.getStartAt(),
				quest.getPicture(), quest.getCertification(), quest.getMileage(), quest.getTargetCnt());
		questParticipantsService.addParticipant(quest.getUserId(), questId);

		return HttpStatus.OK;
	}

	/*
	 * Quest 수정 : 공통
	 */
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
	 */
	@DeleteMapping
	@ApiOperation(value = "퀘스트 삭제")
	public Object deleteQuest(@RequestParam long questId) {
		if (!questService.delete(questId))
			return HttpStatus.NOT_FOUND;

		return HttpStatus.OK;
	}

	@GetMapping("/count")
	@ApiOperation(value = "현재 생성되어 있는 전체 퀘스트 개수")
	public Object getAllQuestCnt() {
		Long questCnt = questService.getAllQuestCnt();
		return new ResponseEntity<>(questCnt, HttpStatus.OK);
	}

	/*
	 * 유저의 Quest 목록 : all, 개인, 릴레이
	 */
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

	// 유저의 진행, 완료, 시작 전 퀘스트
	// 현재 시간 기준으로 구분해서 리스트 가져오기
	@GetMapping("/participant")
	@ApiOperation(value = "유저의 참여 퀘스트 현재 기준으로 가져오기")
	public Object getQuestByTime(@RequestParam long userId, @RequestParam String status) {
		
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		
		Date time = new Date();
		List<QuestResponse> result = new ArrayList<QuestResponse>();
		List<QuestInfo> quests = questService.getQuestInfoByUserId(userId);
		
		if(quests.size()==0)
			return new ResponseEntity<>("참여한 퀘스트가 없음", HttpStatus.NOT_FOUND);
		
		// 진행 중인 퀘스트
		if(status.equals("p")) {
			for(QuestInfo q : quests) {					
				if(q.getStartAt().before(time) && q.getFinishAt().after(time)) {
					QuestResponse qr = new QuestResponse();
					qr.id = q.getId();
					qr.title = q.getTitle();
					qr.description = q.getDescription();
					qr.picture = q.getPicture();
					qr.startAt = q.getStartAt();
					qr.finishAt = q.getFinishAt();
					qr.type = q.getType();
					qr.mileage = q.getMileage();
					result.add(qr);
				}
			}
		}
		// 완료한 퀘스트
		else if(status.equals("c")) {
			for(QuestInfo q : quests) {					
				if(q.getFinishAt().before(time)) {
					QuestResponse qr = new QuestResponse();
					qr.id = q.getId();
					qr.title = q.getTitle();
					qr.description = q.getDescription();
					qr.picture = q.getPicture();
					qr.startAt = q.getStartAt();
					qr.finishAt = q.getFinishAt();
					qr.type = q.getType();
					qr.mileage = q.getMileage();
					result.add(qr);
				}
			}
		}
		// 시작 전 퀘스트
		else if(status.equals("b")) {
			for(QuestInfo q : quests) {					
				if(q.getStartAt().after(time)) {
					QuestResponse qr = new QuestResponse();
					qr.id = q.getId();
					qr.title = q.getTitle();
					qr.description = q.getDescription();
					qr.picture = q.getPicture();
					qr.startAt = q.getStartAt();
					qr.finishAt = q.getFinishAt();
					qr.type = q.getType();
					qr.mileage = q.getMileage();
					result.add(qr);
				}
			}
		}

		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	/*
	 * Relay 퀘스트 : 다음 주자 선정
	 */
	@PostMapping("/relay/next")
	@ApiOperation(value = "릴레이 퀘스트 다음 주자 선정")
	public Object setNextList(@RequestBody RelayNextListRequest request) {
		if (!questService.checkRelay(request.getQuestId()))
			return HttpStatus.NOT_FOUND;

		Quest relay = questService.getQuestById(request.getQuestId());
		int order = relayService.getById(request.getQuestId()).getOrder() + 1;

		relayWaitService.addWaitList(relay, request.getNextList(), order);

		User sender = userService.getUser(request.getUserId());

		// 두번째 주자에게 알람
		questAlarmService.addQuestAlarm(request.getNextList().get(0), relay, sender.getNickname(),
				"[릴레이 퀘스트 요청] " + relay.getTitle(), LocalDateTime.now());

		// 두번째 주자 알림 deadline 설정
		relayWaitService.updateDeadline(relay, request.getNextList().get(0), LocalDateTime.now());

		return HttpStatus.OK;
	}

	/*
	 * Quest 상세 페이지 : 개인, 공동, 릴레이
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "퀘스트 상세 정보")
	public Object getPersonalDetail(@RequestParam long questid, @RequestParam long myid) {
		if (!questService.checkQuest(questid))
			return HttpStatus.NOT_FOUND;
		
		if(!userService.checkId(myid) && myid!=0) 
			return HttpStatus.NOT_FOUND;

		Quest quest = questService.getQuestById(questid);

		final QuestDetailResponse response = new QuestDetailResponse();
		response.setId(quest.getId());
		response.setTitle(quest.getTitle());
		response.setDescription(quest.getDescription());
		response.setPicture(quest.getPicture());
		response.setType(quest.getType());
		response.setStartAt(quest.getStartAt());
		response.setFinishAt(quest.getFinishAt());
		response.setMileage(quest.getMileage());
		response.setPercent(quest.getPercent());
		response.setCertification(quest.getCertification());
		response.setSuccess(quest.getSuccess());

		// 참여하는 유저 리스트 보내기
		List<UserInfo> users = new ArrayList<>();

		List<QuestParticipants> participants = quest.getParticipants();
		for (QuestParticipants qp : participants) {
			User user = qp.getUser();
			users.add(new UserInfo(user.getId(), user.getNickname(), user.getPicture(), user.getDescription()));
		}
		response.setUsers(users);

		// 게시글 리스트 보내기
		// 퀘스트 내의 게시글을 보는데 유저가 좋아요를 누른 것인지 확인도 해야함
		response.setArticles(articleService.getArticleInfoByQuest(questid, userService.getUser(myid)));

		// 릴레이의 경우 목표 인원 & 현재 달성 인원 보내기
		if ("R".equals(quest.getType())) {
			Relay relay = relayService.getById(questid);
			response.setTargetCnt(relay.getTargetCnt());
			response.setNowCnt(relay.getOrder());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * 전체 퀘스트
	 */
	@GetMapping
	@ApiOperation(value = "모든 퀘스트 정보")
	public Object getAllQuests() {
		return new ResponseEntity<>(questService.findAll(), HttpStatus.OK);
	}

}
