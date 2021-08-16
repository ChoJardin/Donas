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
	
	// 기부단체 목록
	public List<Charity> getCharityList(){
		List<Charity> charities = charityRepo.findAll();
		if(charities.isEmpty())
			return null;
		return charities;
	}
	
	// 현재 기부된 총 금액
	public long getSumDonation() {
		if(donationRepo.count()!=0)
			return donationRepo.sumDonation();
		else
			return 0;	
	}
	// 각 유저별 기부 총액
	public long getSumDonationById(long userId) {
		return donationRepo.sumDonationById(userRepo.getById(userId));
	}
	// 분기별 기부 총액
	public long getSumQuaDonation() {
		if(donationRepo.count()!=0)
			return donationRepo.sumQuarDonation();
		else
			return 0;
	}
	
	// 기부내역 저장, 기부한 단체 total에 추가
	public boolean setDonation(long amount,String name, LocalDateTime time, long userId,long charityId) {
		User user = userRepo.getById(userId);
		if(user.getMileage()<amount)
			return false;
		Donation donation = new Donation(amount,time,name,user,charityRepo.getById(charityId));
		if(donationRepo.save(donation) == null) {
			return false;
		}
		Optional<Charity> charity =  charityRepo.findById(charityId);
		charity.ifPresent(presentCharity->{
			presentCharity.setTotal(presentCharity.getTotal()+amount);
		});		
		
		return true;
	}
	
	// 기부내역 확인
	public List<DonationInfo> showDonationList(long userId){
		List<Donation> donationList = donationRepo.findDonationByUser(userRepo.getById(userId));
		if(donationList.size()==0)
			return null;
		List<DonationInfo> donationInfo = new ArrayList<DonationInfo>();
		
		for(Donation d : donationList) {
			donationInfo.add(new DonationInfo(d.getId(),d.getTime(),d.getName(),d.getCharity().getName(),d.getAmount()));
		}
		
		return donationInfo;
	}



}
