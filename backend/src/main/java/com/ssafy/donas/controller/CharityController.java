package com.ssafy.donas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Charity;
import com.ssafy.donas.response.CharityResponse;
import com.ssafy.donas.service.DonationService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/charity")
@RequiredArgsConstructor
public class CharityController {

	@Autowired
	DonationService donationService;
	
	@GetMapping
	@ApiOperation(value="기부단체 목록")
	public Object getCharityList() {
		System.out.println("단체 받으러 11111111111111");
		List<CharityResponse> result = new ArrayList<CharityResponse>();
		List<Charity> charities = donationService.getCharityList();
		if(charities==null)
			return new ResponseEntity<>("기부단체 없음",HttpStatus.NOT_FOUND);
		for(Charity ch : charities) {
			CharityResponse cr = new CharityResponse();
			cr.id = ch.getId();
			cr.name = ch.getName();
			cr.quarter = ch.getQuarter();
			cr.total = ch.getTotal();
			cr.description = ch.getDescription();
			cr.homepage = ch.getHomepage();
			cr.picture = ch.getPicture();
			cr.tag = ch.getTag();
			result.add(cr);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
