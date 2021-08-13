package com.ssafy.donas.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ssafy.donas.response.MainResponse;
import com.ssafy.donas.service.DonationService;
import com.ssafy.donas.service.QuestService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/main")
public class MainController {
	@Autowired
	QuestService questService;
	
	@Autowired
	UserService userService;
	@Autowired
	DonationService donationService;
	
	@GetMapping
	@ApiOperation(value = "메인페이지")
	public Object mainPage() {
		MainResponse result = new MainResponse();

		result.questCnt = questService.getAllQuestCnt();
		result.donation = donationService.getSumDonation();
		result.quarter = "";
		result.questP = questService.getQuestList("P");
		result.questG = questService.getQuestList("G");
		result.questR = questService.getQuestList("R");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
