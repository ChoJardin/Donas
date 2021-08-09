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
		if(donationRepo.count()!=0)
			return donationRepo.sumDonation();
		else
			return 0;	
	}
	
	public long getSumQuaDonation() {
		if(donationRepo.count()!=0)
			return donationRepo.sumQuarDonation();
		else
			return 0;
	}

}
