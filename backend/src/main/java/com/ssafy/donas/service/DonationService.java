package com.ssafy.donas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.repository.DonationRepo;

@Service
@Transactional
public class DonationService {
	
	@Autowired
	DonationRepo donationRepo;
	
	public long getSumDonation() {
		return donationRepo.sumDonation();		
	}
	
	public long getSumQuaDonation() {
		return donationRepo.sumQuarDonation();
	}

}
