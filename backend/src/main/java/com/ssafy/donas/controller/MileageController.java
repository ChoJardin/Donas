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

import com.ssafy.donas.request.CashRequest;
import com.ssafy.donas.request.DonationRequest;
import com.ssafy.donas.service.CashService;
import com.ssafy.donas.service.DonationService;
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
	
	
	@PostMapping("/donation")
	@ApiOperation(value="기부 금액 설정")
	public Object setDonation(@RequestBody DonationRequest donation) {
		long amount = donation.getAmount();
		long userId = donation.getUserId();
		long charityId = donation.getCharityId();
		
		if(!userService.checkId(userId))
			return new ResponseEntity<>("유저 없음", HttpStatus.NOT_FOUND);
		
		if(!donationService.getCharity(charityId))
			return new ResponseEntity<>("기부 단체 없음", HttpStatus.NOT_FOUND);
		if(!donationService.setDonation(amount, userId, charityId))
			return new ResponseEntity<>("마일리지 초과",HttpStatus.BAD_REQUEST);
		
		return HttpStatus.OK;
	}
	
	
	@PostMapping("/cash")
	@ApiOperation(value="현금화")
	public Object changeCash(@RequestBody CashRequest cash) {
		long userId = cash.getUserId();
		long amount = cash.getAmount();
		String accountNum = cash.getAccountNum();
		String bank = cash.getBanck();
		
		if(!userService.checkId(userId))
			return new ResponseEntity<>("유저 없음", HttpStatus.NOT_FOUND);
		
		if(!cashService.changeCash(userId, LocalDateTime.now(), amount, accountNum, bank))
			return HttpStatus.NOT_FOUND;
		return HttpStatus.OK;
		
	}

}
