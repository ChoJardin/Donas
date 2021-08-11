package com.ssafy.donas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Charity;
import com.ssafy.donas.domain.Donation;
import com.ssafy.donas.domain.DonationInfo;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.CharityRepo;
import com.ssafy.donas.repository.DonationRepo;
import com.ssafy.donas.repository.UserRepo;
import com.ssafy.donas.response.DonationListResponse;

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
		if(charityRepo.findById(charityId).isEmpty())
			return false;
//		if(charityRepo.getById(charityId)==null)
//			return false;
		return true;		
	}	
	
	public long getSumDonation() {
		if(donationRepo.count()!=0)
			return donationRepo.sumDonation();
		else
			return 0;	
	}
	
	public long getSumDonationById(long userId) {
		return donationRepo.sumDonationById(userRepo.getById(userId));
	}
	
	public long getSumQuaDonation() {
		if(donationRepo.count()!=0)
			return donationRepo.sumQuarDonation();
		else
			return 0;
	}
	
	public boolean setDonation(long amount,String name, LocalDateTime time, long userId,long charityId) {
		User user = userRepo.getById(userId);
		if(user.getMileage()<amount)
			return false;
		
		if(donationRepo.save(new Donation(amount,time,name,user,charityRepo.getById(charityId))) == null) {
			return false;
		}
		return true;
	}
	
	// 기부내역 확인
	public List<DonationInfo> showDonationList(long userId){
		List<Donation> donationList = donationRepo.findDonationByUser(userRepo.getById(userId));
		if(donationList.size()==0)
			return null;
		List<DonationInfo> donationInfo = new ArrayList<DonationInfo>();
		
		for(Donation d : donationList) {
			donationInfo.add(new DonationInfo(d.getId(),d.getName(),d.getCharity().getName(),d.getAmount()));
		}
		
		return donationInfo;
	}



}
