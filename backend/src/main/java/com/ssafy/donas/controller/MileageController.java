package com.ssafy.donas.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.User;
import com.ssafy.donas.request.CashRequest;
import com.ssafy.donas.request.DonationRequest;
import com.ssafy.donas.service.AlarmService;
import com.ssafy.donas.service.CashService;
import com.ssafy.donas.service.DonationService;
import com.ssafy.donas.service.MileageService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/mileage")
@RequiredArgsConstructor
public class MileageController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DonationService donationService;
	
	@Autowired
	CashService cashService;
	
	@Autowired
	MileageService mileageService;
	
	@Autowired
	AlarmService alarmService;
	
	
	@PostMapping("/donation")
	@ApiOperation(value="기부 금액 설정")
	public Object setDonation(@RequestBody DonationRequest donation) {
		
		long amount = donation.getAmount();
		String name = donation.getUserName();
		long userId = donation.getUserId();
		long charityId = donation.getCharityId();
		LocalDateTime time = LocalDateTime.now();
		
		if(!userService.checkId(userId))
			return new ResponseEntity<>("유저 없음", HttpStatus.NOT_FOUND);
		
		if(!donationService.getCharity(charityId))
			return new ResponseEntity<>("기부 단체 없음", HttpStatus.NOT_FOUND);
		
		if(!donationService.setDonation(amount, name,time,userId, charityId))
			return new ResponseEntity<>("마일리지 초과",HttpStatus.BAD_REQUEST);
		
		if(!mileageService.minusMileage(userId, amount))
			return new ResponseEntity<>("마일리지 초과", HttpStatus.BAD_REQUEST);
		
		User user = userService.getUser(userId);
		alarmService.addAlarm(user, user.getNickname(), -3, amount+"기부 완료", time);
		return HttpStatus.OK;
	}
	
	
	@PostMapping("/cash")
	@ApiOperation(value="현금화")
	public Object changeCash(@RequestBody CashRequest cash) {
		
		long userId = cash.getUserId();
		String name = cash.getUserName();
		long amount = cash.getAmount();
		String accountNum = cash.getAccountNum();
		String bank = cash.getBank();
		LocalDateTime time = LocalDateTime.now();
		
		if(!userService.checkId(userId))
			return new ResponseEntity<>("유저 없음", HttpStatus.NOT_FOUND);
		
		if(!cashService.changeCash(userId, time,name, amount, accountNum, bank))
			return HttpStatus.NOT_FOUND;
		
		if(!mileageService.minusMileage(userId, amount))
			return new ResponseEntity<>("마일리지 초과", HttpStatus.BAD_REQUEST);
		
		User user = userService.getUser(userId);
		alarmService.addAlarm(user, user.getNickname(), -2, amount+"현금화 완료", time);
		return HttpStatus.OK;
		
	}

}
