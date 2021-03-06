package com.ssafy.donas.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Alarm;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestAlarm;
import com.ssafy.donas.domain.quest.QuestParticipants;
import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.request.AcceptGroupRequest;
import com.ssafy.donas.request.AcceptRelayRequest;
import com.ssafy.donas.request.ComfirmAlarmRequest;
import com.ssafy.donas.response.AlarmResponse;
import com.ssafy.donas.response.QuestAlarmResponse;
import com.ssafy.donas.service.AlarmService;
import com.ssafy.donas.service.FcmService;
import com.ssafy.donas.service.QuestAlarmService;
import com.ssafy.donas.service.QuestParticipantsService;
import com.ssafy.donas.service.QuestService;
import com.ssafy.donas.service.RelayService;
import com.ssafy.donas.service.RelayWaitService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/alarm")
@RequiredArgsConstructor
public class AlarmController {
	
	@Autowired
	AlarmService alarmService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FcmService fcmService;
	
	@Autowired
	QuestAlarmService questAlarmService;
	
	@Autowired
	QuestParticipantsService questParticipantsService;
	
	@Autowired
	RelayWaitService relayWaitService;
	
	@Autowired
	QuestService questService;
	
	@Autowired
	RelayService relayService;
	
	@GetMapping("/{userId}")
	@ApiOperation(value="?????? ?????????")
	public Object getAlarmList(@PathVariable long userId) {
		if(!userService.checkId(userId) && userId!=0) 
			return HttpStatus.NOT_FOUND;
		User user = userService.getUser(userId);

		List<Alarm> alarms = alarmService.getAlarms(user);		
		if(alarms.size()==0)
			return new ResponseEntity<>("????????????",HttpStatus.OK);
		final List<AlarmResponse> result = new ArrayList<AlarmResponse>();
		for(Alarm alarm : alarms) {
			AlarmResponse res = new AlarmResponse();
			res.id = alarm.getId();
			res.receiveId = alarm.getUser().getId();
			res.sendName = alarm.getSendName();
			res.articleId = alarm.getArticleId();
			res.content = alarm.getContents();
			res.sendTime = alarm.getSendTime();
			res.confirm = alarm.getConfirm();
			result.add(res);
		}
		Date time = new Date();
		questService.checkQuestSuccess(userId, time);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PatchMapping
	@ApiOperation(value="????????????")
	public Object confirmAlarm(@RequestBody ComfirmAlarmRequest alarm) {
		if(!alarmService.checkAlarm(alarm.getId()))
			return HttpStatus.NOT_FOUND;		
		alarmService.update(alarm.getId(),alarm.getConfirm());		
		return HttpStatus.OK;
	}
	
	// ?????? ?????? ??????	
	@PatchMapping("{userId}")
	@ApiOperation(value="?????? ?????? ??????")
	public Object confirmAlarmAll(@PathVariable long userId) {		
		List<Alarm> alarms = alarmService.confirmAlarmAll(userService.getUser(userId));
		if(alarms.size()==0)
			return new ResponseEntity<>("?????? ??????", HttpStatus.OK);
		
		final List<AlarmResponse> result = new ArrayList<AlarmResponse>();
		for(Alarm alarm : alarms) {
			AlarmResponse res = new AlarmResponse();
			res.id = alarm.getId();
			res.receiveId = alarm.getUser().getId();
			res.sendName = alarm.getSendName();
			res.articleId = alarm.getArticleId();
			res.content = alarm.getContents();
			res.sendTime = alarm.getSendTime();
			res.confirm = alarm.getConfirm();
			result.add(res);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	/*
	 * Quest ?????? : ????????? ?????? ?????????, ????????? ??????/??????, ?????? ??????/??????
	 * */
	@GetMapping("/quest/{userId}")
	@ApiOperation(value="????????? ?????? ?????????")
	public Object getQuestAlarmList(@PathVariable long userId) {
		if(!userService.checkId(userId) && userId!=0) 
			return HttpStatus.NOT_FOUND;
		User user = userService.getUser(userId);
		
		List<QuestAlarm> questAlarms = questAlarmService.getAlarms(user);
		final List<QuestAlarmResponse> result = new ArrayList<QuestAlarmResponse>();
		
		for(QuestAlarm alarm : questAlarms) {
			QuestAlarmResponse res = new QuestAlarmResponse();
			res.id = alarm.getId();
			res.receiveId = alarm.getUser().getId();
			res.sendName = alarm.getSendName();
			res.content = alarm.getContents();
			res.sendTime = alarm.getSendTime();
			res.questId = alarm.getQuest().getId();
			res.confirm = alarm.getConfirm();
			result.add(res);
		}
		Date time = new Date();
		questService.checkQuestSuccess(userId, time);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/relay")
	@ApiOperation(value="????????? ??????")
	public Object acceptRelay(@RequestBody AcceptRelayRequest request) {
		long alarmId = request.getAlarmId();
		long questId = request.getQuestId();
		long userId = request.getUserId();
		int relayOrder = request.getRelayOrder();
		if(!questService.checkQuest(questId))
			return HttpStatus.NOT_FOUND;
		System.out.println("????????? ??????");
		if(!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		System.out.println("????????????");
		if(!questAlarmService.checkQuestAlarm(alarmId))
			return HttpStatus.NOT_FOUND;
		System.out.println("?????? ??????");

		List<QuestParticipants> participants = questParticipantsService.participantsList(questService.getQuestById(questId));		
//		// ???????????? ???????????? ???????????? ?????? ????????? ??????
//		for(QuestParticipants qp : participants) {
//			User user =  userService.getUser(userId);
//			Quest quest = qp.getQuest();
//			questAlarmService.addQuestAlarm(qp.getId(), quest,user.getNickname(), user.getNickname()+"?????? "+"\""+quest.getTitle()+"\""+"??? ?????????????????????.", LocalDateTime.now().plusHours(9));
//			
//		}
		
		// ???????????? ???????????? ??????
		questParticipantsService.addParticipant(userId, questId);
		System.out.println("??????");
		// ?????? ?????? ??????????????? ?????? ?????? ????????? ??????
		relayWaitService.deleteByRelayAndRelayOrder(questService.getQuestById(questId), relayOrder);
		
		// ????????? ?????? ?????? ?????? ????????????
		relayService.updateUserOrder(questId, relayOrder);
		
		// ?????? ?????? ?????? ???????????? ????????????
		questAlarmService.updateConfirm(alarmId, 2);
		
		return HttpStatus.OK;
	}
	
	@PatchMapping("/relay")
	@ApiOperation(value="????????? ??????")
	public Object rejectRelay(@RequestParam long alarmId, @RequestParam long userId) {
		if(!questAlarmService.checkQuestAlarm(alarmId))
			return HttpStatus.NOT_FOUND;
		
		// ?????? ?????? ?????? ???????????? ????????????
		questAlarmService.updateConfirm(alarmId, 3);
		
//		// ????????? ?????? ??????????????? ????????? ??????
//		Relay relay = relayService.getById(questAlarmService.getAlarm(alarmId).getQuest().getId());
//		if(relayWaitService.checkLastWait(relay, userService.getUser(userId))) {
//			questService.quitQuest(relay.getId(), -1);
//			
//			// ***????????? ?????? ?????? : ???????????? ????????? ?????? ???????????? ??????(??????) ??????***
//		}
		
		return HttpStatus.OK;
	}	
	
	@PostMapping("/group")
	@ApiOperation(value="?????? ??????")
	public Object acceptGroup(@RequestBody AcceptGroupRequest request) {
		long alarmId = request.getAlarmId();
		long questId = request.getQuestId();
		long userId = request.getUserId();
		
		if(!questService.checkQuest(questId) || !userService.checkId(userId) || !questAlarmService.checkQuestAlarm(alarmId))
			return HttpStatus.NOT_FOUND;
		List<QuestParticipants> participants = questParticipantsService.participantsList(questService.getQuestById(questId));
		
//		// ???????????? ???????????? ???????????? ?????? ????????? ??????
//		User user =  userService.getUser(userId);
//		for(QuestParticipants qp : participants) {
//			Quest quest = qp.getQuest();
//			questAlarmService.addQuestAlarm(qp.getUser().getId(), quest,user.getNickname(), user.getNickname()+"?????? "+"\""+quest.getTitle()+"\""+" ????????? ????????? ?????????????????????.", LocalDateTime.now().plusHours(9));			
//		}
		
		// ???????????? ???????????? ??????
		questParticipantsService.addParticipant(userId, questId);
		System.out.println("????????? ?????????????");
		// ?????? ?????? ?????? ???????????? ????????????
		questAlarmService.updateConfirm(alarmId, 2);
		
		return HttpStatus.OK;
	}
	
	@PatchMapping("/group")
	@ApiOperation(value="?????? ??????")
	public Object rejectGroup(@RequestParam long alarmId, @RequestParam long userId) {
		if(!questAlarmService.checkQuestAlarm(alarmId))
			return HttpStatus.NOT_FOUND;
		List<QuestParticipants> participants = questParticipantsService.participantsList(questService.getQuestById(questAlarmService.getAlarm(alarmId).getQuest().getId()));
		
		// ?????? ?????? ?????? ???????????? ????????????
		questAlarmService.updateConfirm(alarmId, 3);
		
		// ???????????? ???????????? ???????????? ???????????? ??????
		User user =  userService.getUser(userId);
		for(QuestParticipants qp : participants) {
			Quest quest = qp.getQuest();
			questAlarmService.addQuestAlarm(qp.getUser().getId(), quest,user.getNickname(), user.getNickname()+"?????? "+"\""+quest.getTitle()+"\""+"????????? ????????? ?????????????????????.", LocalDateTime.now().plusHours(9));			
		}		
		return HttpStatus.OK;
	}	
	
}
