package com.ssafy.donas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Charity;
import com.ssafy.donas.domain.Donation;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.CharityRepo;
import com.ssafy.donas.repository.DonationRepo;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class DonationService {
	
	@Autowired
	DonationRepo donationRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CharityRepo charityRepo;
	
	public boolean getCharity(long charityId) {
		if(charityRepo.getById(charityId)==null)
			return false;
		return true;		
	}	
	
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
	
	public boolean setDonation(long amount,long userId,long charityId) {
		User user = userRepo.getById(userId);
		if(user.getMileage()<amount)
			return false;
		
		if(donationRepo.save(new Donation(amount,user,charityRepo.getById(charityId))) == null) {
			return false;
		}
		return true;
	}

}
