package com.ssafy.donas.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
	 * Quest ?????? : ??????, ?????? (????????? ??????)
	 */
	
	@GetMapping("/relay/success")
	@ApiOperation(value = "????????? ?????? ??????")
	public Object checkRelaySuccess(@RequestParam int order, @RequestParam long questId) {
		if(questService.getQuestById(questId)==null)
			return HttpStatus.NOT_FOUND;
		if(!questService.checkRelaySuccess(order, questId))
			return HttpStatus.NO_CONTENT;
		return HttpStatus.OK;
	}

	@PostMapping("/personal")
	@ApiOperation(value = "??????????????? ??????")
	public Object addPersonalQuest(@RequestBody AddPersonalQuestRequest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;

		if ("".equals(quest.getTitle()) || "".equals(quest.getDescription()) || "".equals(quest.getCertification()))
			return HttpStatus.NO_CONTENT;

		long questId = questService.addPersonalQuest(quest.getTitle(), quest.getDescription(), quest.getStartAt(),
				quest.getFinishAt(), quest.getPicture(), quest.getCertification(), quest.getMileage(),quest.getMinArticleCount());
		questParticipantsService.addParticipant(quest.getUserId(), questId);

		return HttpStatus.OK;
	}
	
	@PostMapping("/personal/participant")
	@ApiOperation(value = "??????????????? ??????")
	public Object particiPersonalQuest(@RequestBody ParticiPersonalQuest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;		
		if(questService.getQuestById(quest.getQuestId())==null)
			return HttpStatus.BAD_REQUEST;
		if(questParticipantsService.participantByQuest(questService.getQuestById(quest.getQuestId()),userService.getUser(quest.getUserId()))!=null)
			return HttpStatus.CONFLICT;
		questParticipantsService.addParticipant(quest.getUserId(), quest.getQuestId());
		
		Quest nowQuest = questService.getQuestById(quest.getQuestId());

		final QuestDetailResponse response = new QuestDetailResponse();
		response.setId(nowQuest.getId());
		response.setTitle(nowQuest.getTitle());
		response.setDescription(nowQuest.getDescription());
		response.setPicture(nowQuest.getPicture());
		response.setType(nowQuest.getType());
		response.setStartAt(nowQuest.getStartAt());
		response.setFinishAt(nowQuest.getFinishAt());
		response.setMileage(nowQuest.getMileage());
		response.setPercent(nowQuest.getPercent());
		response.setCertification(nowQuest.getCertification());
		response.setSuccess(nowQuest.getSuccess());

		// ???????????? ?????? ????????? ?????????
		List<UserInfo> users = new ArrayList<>();

		List<QuestParticipants> participants = nowQuest.getParticipants();
		for (QuestParticipants qp : participants) {
			User user = qp.getUser();
			users.add(new UserInfo(user.getId(), user.getNickname(), user.getPicture(), user.getDescription()));
		}
		response.setUsers(users);

		// ????????? ????????? ?????????
		// ????????? ?????? ???????????? ????????? ????????? ???????????? ?????? ????????? ????????? ?????????
		response.setArticles(articleService.getArticleInfoByQuest(quest.getQuestId(), userService.getUser(quest.getUserId())));

		// ???????????? ?????? ?????? ?????? & ?????? ?????? ?????? ?????????
		if ("R".equals(nowQuest.getType())) {
			Relay relay = relayService.getById(quest.getQuestId());
			response.setTargetCnt(relay.getTargetCnt());
			response.setNowCnt(relay.getOrder());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/group")
	@ApiOperation(value = "??????????????? ??????")
	public Object addGroupQuest(@RequestBody AddGroupQuestRequest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;

		if ("".equals(quest.getTitle()) || "".equals(quest.getDescription()) || "".equals(quest.getCertification()))
			return HttpStatus.NO_CONTENT;
		System.out.println(quest.getFinishAt());
		Quest groupQuest = questService.addGroupQuest(quest.getTitle(), quest.getDescription(), quest.getStartAt(),
				quest.getFinishAt(), quest.getPicture(), quest.getCertification(), quest.getMileage(),
				quest.getParticipants().size() + 1,quest.getMinArticleCount());

		List<String> participantUsers = quest.getParticipants();
		List<User> participants = new ArrayList<>();
		for (String p : participantUsers) {
			long id = userService.getIdByNickname(p);
			if (id == -1)
				return HttpStatus.NOT_FOUND;

			participants.add(userService.getUser(id));

			// ??????????????? ?????? ??????
			questAlarmService.addQuestAlarm(id, groupQuest, userService.getUser(quest.getUserId()).getNickname(),
					"[?????? ????????? ??????] " + groupQuest.getTitle(), LocalDateTime.now().plusHours(9));
		}

		// ????????? ???????????? DB??? ???????????? (???????????? ???????????? ??????!)
		questParticipantsService.addParticipant(quest.getUserId(), groupQuest.getId());

//		questParticipantsService.addParticipants(userService.getUser(quest.getUserId()), participants, groupQuest);

		return HttpStatus.OK;
	}

	@PostMapping("/relay")
	@ApiOperation(value = "?????????????????? ??????")
	public Object addRelayQuest(@RequestBody AddRelayQuestRequest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;

		if ("".equals(quest.getTitle()) || "".equals(quest.getDescription()) || "".equals(quest.getCertification()))
			return HttpStatus.NO_CONTENT;

		long questId = questService.addRelayQuest(quest.getTitle(), quest.getDescription(), quest.getStartAt(),quest.getFinishAt(),
				quest.getPicture(), quest.getCertification(), quest.getMileage(), quest.getTargetCnt());
		questParticipantsService.addParticipant(quest.getUserId(), questId);

		return HttpStatus.OK;
	}

	/*
	 * Quest ?????? : ??????
	 */
	@PatchMapping
	@ApiOperation(value = "????????? ?????? (title, description ??????)")
	public Object updateQuest(@RequestBody UpdateQuestRequest quest) {
		if (!questService.checkQuest(quest.getQuestId()))
			return HttpStatus.NOT_FOUND;

		questService.updateQuest(quest.getQuestId(), quest.getTitle(), quest.getDescription());

		return HttpStatus.OK;
	}

	/*
	 * Quest ?????? : ??????
	 */
	@DeleteMapping
	@ApiOperation(value = "????????? ??????")
	public Object deleteQuest(@RequestParam long questId) {
		if (!questService.delete(questId))
			return HttpStatus.NOT_FOUND;

		return HttpStatus.OK;
	}

	@GetMapping("/count")
	@ApiOperation(value = "?????? ???????????? ?????? ?????? ????????? ??????")
	public Object getAllQuestCnt() {
		Long questCnt = questService.getAllQuestCnt();
		return new ResponseEntity<>(questCnt, HttpStatus.OK);
	}

	/*
	 * ????????? Quest ?????? : all, ??????, ?????????
	 */
	@GetMapping("/{userId}")
	@ApiOperation(value = "?????? ??? ???????????? ?????? ????????? ????????????")
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
		Collections.sort(result,new Comparator<QuestResponse>() {

			@Override
			public int compare(QuestResponse o1, QuestResponse o2) {
				if(o2.startAt.before(o1.startAt))
					return 1;
				return -1;
			}
		});
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/personal/{userId}")
	@ApiOperation(value = "????????? ???????????? ?????? ????????? ????????????")
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
		Collections.sort(result,new Comparator<QuestResponse>() {

			@Override
			public int compare(QuestResponse o1, QuestResponse o2) {
				if(o2.startAt.before(o1.startAt))
					return 1;
				return -1;
			}
		});
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/group/{userId}")
	@ApiOperation(value = "????????? ???????????? ?????? ????????? ????????????")
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
		Collections.sort(result,new Comparator<QuestResponse>() {

			@Override
			public int compare(QuestResponse o1, QuestResponse o2) {
				if(o2.startAt.before(o1.startAt))
					return 1;
				return -1;
			}
		});
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/relay/{userId}")
	@ApiOperation(value = "????????? ???????????? ????????? ????????? ????????????")
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
		Collections.sort(result,new Comparator<QuestResponse>() {

			@Override
			public int compare(QuestResponse o1, QuestResponse o2) {
				if(o2.startAt.before(o1.startAt))
					return 1;
				return -1;
			}
		});
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// ????????? ??????, ??????, ?????? ??? ?????????
	// ?????? ?????? ???????????? ???????????? ????????? ????????????
	@GetMapping("/participant")
	@ApiOperation(value = "????????? ?????? ????????? ?????? ???????????? ????????????")
	public Object getQuestByTime(@RequestParam long userId, @RequestParam String status) {
		
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		
		Date time = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.HOUR, 9);
		time = cal.getTime();
		List<QuestResponse> result = new ArrayList<QuestResponse>();
		List<QuestInfo> quests = questService.getQuestInfoByUserId(userId);
		
		if(quests.size()==0)
			return new ResponseEntity<>("????????? ???????????? ??????", HttpStatus.NOT_FOUND);
		
		// ?????? ?????? ?????????
		if(status.equals("p")) {
			for(QuestInfo q : quests) {
				System.out.println(q.getStartAt());
				System.out.println(q.getFinishAt());
				System.out.println(time);
				if(questService.getQuestById(q.getId()).getSuccess()!=1 && q.getStartAt().before(time) && q.getFinishAt().after(time)) {
					System.out.println("??? ??? ???????");
					QuestResponse qr = new QuestResponse();
					qr.id = q.getId();
					qr.title = q.getTitle();
					qr.description = q.getDescription();
					qr.picture = q.getPicture();
					qr.startAt = q.getStartAt();
					qr.finishAt = q.getFinishAt();
					qr.type = q.getType();
					qr.mileage = q.getMileage();
					qr.success = q.getSuccess();
					result.add(qr);
				}
			}
		}
		// ????????? ?????????
		else if(status.equals("c")) {
			for(QuestInfo q : quests) {					
				if(q.getFinishAt().before(time) ||(("R").equals(q.getType()) && questService.getQuestById(q.getId()).getSuccess()==1)) {
					QuestResponse qr = new QuestResponse();
					qr.id = q.getId();
					qr.title = q.getTitle();
					qr.description = q.getDescription();
					qr.picture = q.getPicture();
					qr.startAt = q.getStartAt();
					qr.finishAt = q.getFinishAt();
					qr.type = q.getType();
					qr.mileage = q.getMileage();
					qr.success = q.getSuccess();
					result.add(qr);
				}
			}
		}
		// ?????? ??? ?????????
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
					qr.success = q.getSuccess();
					result.add(qr);
				}
			}
		}
		Collections.sort(result,new Comparator<QuestResponse>() {

			@Override
			public int compare(QuestResponse o1, QuestResponse o2) {
				if(o2.startAt.before(o1.startAt))
					return 1;
				return -1;
			}
		});

		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	/*
	 * Relay ????????? : ?????? ?????? ??????
	 */
	@PostMapping("/relay/next")
	@ApiOperation(value = "????????? ????????? ?????? ?????? ??????")
	public Object setNextList(@RequestBody RelayNextListRequest request) {
		if (!questService.checkRelay(request.getQuestId()))
			return HttpStatus.NOT_FOUND;

		Quest relay = questService.getQuestById(request.getQuestId());
		int order = relayService.getById(request.getQuestId()).getOrder() + 1; // ?????? ???????????? +1

		relayWaitService.addWaitList(relay, request.getNextId(), order);
		
		User sender = userService.getUser(request.getUserId());
		LocalDateTime time = LocalDateTime.now().plusHours(9);
		// ?????? ???????????? ??????
		questAlarmService.addQuestAlarm(request.getNextId(), relay, sender.getNickname(),
				"[????????? ????????? ??????] " + relay.getTitle(), time);

		return HttpStatus.OK;
	}

	/*
	 * Quest ?????? ????????? : ??????, ??????, ?????????
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "????????? ?????? ??????")
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

		// ???????????? ?????? ????????? ?????????
		List<UserInfo> users = new ArrayList<>();

		List<QuestParticipants> participants = quest.getParticipants();
		for (QuestParticipants qp : participants) {
			User user = qp.getUser();
			users.add(new UserInfo(user.getId(), user.getNickname(), user.getPicture(), user.getDescription()));
		}
		response.setUsers(users);

		// ????????? ????????? ?????????
		// ????????? ?????? ???????????? ????????? ????????? ???????????? ?????? ????????? ????????? ?????????
		response.setArticles(articleService.getArticleInfoByQuest(questid, userService.getUser(myid)));

		// ???????????? ?????? ?????? ?????? & ?????? ?????? ?????? ?????????
		if ("R".equals(quest.getType())) {
			Relay relay = relayService.getById(questid);
			response.setTargetCnt(relay.getTargetCnt());
			response.setNowCnt(relay.getOrder());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * ?????? ?????????
	 */
	@GetMapping
	@ApiOperation(value = "?????? ????????? ??????")
	public Object getAllQuests() {
		Date time = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.HOUR, 9);
		time = cal.getTime();
		return new ResponseEntity<>(questService.findAll(time), HttpStatus.OK);
	}

}
