package com.ssafy.donas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Alarm;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.request.ComfirmAlarmRequest;
import com.ssafy.donas.response.AlarmResponse;
import com.ssafy.donas.service.AlarmService;
import com.ssafy.donas.service.FcmService;
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
	
	@GetMapping("/{userId}")
	@ApiOperation(value="알림 리스트")
	public Object getAlarmList(@PathVariable long userId) {
		User user = userService.getUser(userId);
		if(user==null)
			return HttpStatus.NO_CONTENT;
		List<Alarm> alarms = alarmService.getAlarms(user);		
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
//		alarmService.update(user);
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PatchMapping
	@ApiOperation(value="알림확인")
	public Object confirmAlarm(@RequestBody ComfirmAlarmRequest alarm) {
		if(alarmService.checkAlarm(alarm.getAlarmId()))
			return HttpStatus.NOT_FOUND;		
		alarmService.update(alarm.getAlarmId(),alarm.getConfirm());
		
		return HttpStatus.OK;
	}
}
