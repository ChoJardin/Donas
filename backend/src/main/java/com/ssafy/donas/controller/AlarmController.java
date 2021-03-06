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
	@ApiOperation(value="알림 리스트")
	public Object getAlarmList(@PathVariable long userId) {
		if(!userService.checkId(userId) && userId!=0) 
			return HttpStatus.NOT_FOUND;
		User user = userService.getUser(userId);

		List<Alarm> alarms = alarmService.getAlarms(user);		
		if(alarms.size()==0)
			return new ResponseEntity<>("알림없음",HttpStatus.OK);
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
	@ApiOperation(value="알림확인")
	public Object confirmAlarm(@RequestBody ComfirmAlarmRequest alarm) {
		if(!alarmService.checkAlarm(alarm.getId()))
			return HttpStatus.NOT_FOUND;		
		alarmService.update(alarm.getId(),alarm.getConfirm());		
		return HttpStatus.OK;
	}
	
	// 알림 전체 읽음	
	@PatchMapping("{userId}")
	@ApiOperation(value="알림 전체 읽음")
	public Object confirmAlarmAll(@PathVariable long userId) {		
		List<Alarm> alarms = alarmService.confirmAlarmAll(userService.getUser(userId));
		if(alarms.size()==0)
			return new ResponseEntity<>("알림 없음", HttpStatus.OK);
		
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
	 * Quest 알람 : 퀘스트 알람 리스트, 릴레이 수락/거절, 공동 수락/거절
	 * */
	@GetMapping("/quest/{userId}")
	@ApiOperation(value="퀘스트 알림 리스트")
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
	@ApiOperation(value="릴레이 수락")
	public Object acceptRelay(@RequestBody AcceptRelayRequest request) {
		long alarmId = request.getAlarmId();
		long questId = request.getQuestId();
		long userId = request.getUserId();
		int relayOrder = request.getRelayOrder();
		if(!questService.checkQuest(questId))
			return HttpStatus.NOT_FOUND;
		System.out.println("아이디 있고");
		if(!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		System.out.println("유저있고");
		if(!questAlarmService.checkQuestAlarm(alarmId))
			return HttpStatus.NOT_FOUND;
		System.out.println("알림 있고");

		List<QuestParticipants> participants = questParticipantsService.participantsList(questService.getQuestById(questId));		
//		// 퀘스트에 참여중인 유저에게 추가 참여자 알림
//		for(QuestParticipants qp : participants) {
//			User user =  userService.getUser(userId);
//			Quest quest = qp.getQuest();
//			questAlarmService.addQuestAlarm(qp.getId(), quest,user.getNickname(), user.getNickname()+"님이 "+"\""+quest.getTitle()+"\""+"에 참여하였습니다.", LocalDateTime.now().plusHours(9));
//			
//		}
		
		// 참여중인 퀘스트에 추가
		questParticipantsService.addParticipant(userId, questId);
		System.out.println("참여");
		// 대기 주자 테이블에서 해당 순서 주자들 제거
		relayWaitService.deleteByRelayAndRelayOrder(questService.getQuestById(questId), relayOrder);
		
		// 릴레이 현재 주자 번호 업데이트
		relayService.updateUserOrder(questId, relayOrder);
		
		// 알람 응답 칼럼 수락으로 업데이트
		questAlarmService.updateConfirm(alarmId, 2);
		
		return HttpStatus.OK;
	}
	
	@PatchMapping("/relay")
	@ApiOperation(value="릴레이 거절")
	public Object rejectRelay(@RequestParam long alarmId, @RequestParam long userId) {
		if(!questAlarmService.checkQuestAlarm(alarmId))
			return HttpStatus.NOT_FOUND;
		
		// 알람 응답 칼럼 거절으로 업데이트
		questAlarmService.updateConfirm(alarmId, 3);
		
//		// 마지막 대기 주자였다면 퀘스트 종료
//		Relay relay = relayService.getById(questAlarmService.getAlarm(alarmId).getQuest().getId());
//		if(relayWaitService.checkLastWait(relay, userService.getUser(userId))) {
//			questService.quitQuest(relay.getId(), -1);
//			
//			// ***퀘스트 종료 알림 : 퀘스트에 참여한 모든 사람에게 종료(실패) 알림***
//		}
		
		return HttpStatus.OK;
	}	
	
	@PostMapping("/group")
	@ApiOperation(value="그룹 수락")
	public Object acceptGroup(@RequestBody AcceptGroupRequest request) {
		long alarmId = request.getAlarmId();
		long questId = request.getQuestId();
		long userId = request.getUserId();
		
		if(!questService.checkQuest(questId) || !userService.checkId(userId) || !questAlarmService.checkQuestAlarm(alarmId))
			return HttpStatus.NOT_FOUND;
		List<QuestParticipants> participants = questParticipantsService.participantsList(questService.getQuestById(questId));
		
//		// 퀘스트에 참여중인 유저에게 추가 참여자 알림
//		User user =  userService.getUser(userId);
//		for(QuestParticipants qp : participants) {
//			Quest quest = qp.getQuest();
//			questAlarmService.addQuestAlarm(qp.getUser().getId(), quest,user.getNickname(), user.getNickname()+"님이 "+"\""+quest.getTitle()+"\""+" 퀘스트 참여에 수락하였습니다.", LocalDateTime.now().plusHours(9));			
//		}
		
		// 참여중인 퀘스트에 추가
		questParticipantsService.addParticipant(userId, questId);
		System.out.println("참여를 하는거지?");
		// 알람 응답 칼럼 수락으로 업데이트
		questAlarmService.updateConfirm(alarmId, 2);
		
		return HttpStatus.OK;
	}
	
	@PatchMapping("/group")
	@ApiOperation(value="그룹 거절")
	public Object rejectGroup(@RequestParam long alarmId, @RequestParam long userId) {
		if(!questAlarmService.checkQuestAlarm(alarmId))
			return HttpStatus.NOT_FOUND;
		List<QuestParticipants> participants = questParticipantsService.participantsList(questService.getQuestById(questAlarmService.getAlarm(alarmId).getQuest().getId()));
		
		// 알람 응답 칼럼 거절으로 업데이트
		questAlarmService.updateConfirm(alarmId, 3);
		
		// 퀘스트에 참여중인 유저에게 거절의사 알림
		User user =  userService.getUser(userId);
		for(QuestParticipants qp : participants) {
			Quest quest = qp.getQuest();
			questAlarmService.addQuestAlarm(qp.getUser().getId(), quest,user.getNickname(), user.getNickname()+"님이 "+"\""+quest.getTitle()+"\""+"퀘스트 참여를 거절하였습니다.", LocalDateTime.now().plusHours(9));			
		}		
		return HttpStatus.OK;
	}	
	
}
