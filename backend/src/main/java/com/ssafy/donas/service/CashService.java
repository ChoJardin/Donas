package com.ssafy.donas.service;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.donas.domain.Cash;
import com.ssafy.donas.domain.CashInfo;
import com.ssafy.donas.repository.CashRepo;
import com.ssafy.donas.repository.CharityRepo;
import com.ssafy.donas.repository.UserRepo;

@Service
@Transactional
public class CashService {
	
	@Autowired
	CashRepo cashRepo;
	
	@Autowired
	UserRepo userRepo;
		
	@Autowired
	DonationService donationService;
	
	@Autowired
	CharityRepo charityRepo;
	
	public long getSumCashById(long userId) {
		
		
		return cashRepo.sumCashById(userRepo.getById(userId));
	}
	
	public boolean changeCash(long userId,LocalDateTime changeTime, String name, long amount, String accountNum, String bank, long charityId) {
		
		Cash cash = new Cash(amount,charityRepo.getById(charityId),changeTime.plusHours(9),name,userRepo.getById(userId),accountNum,bank);		
		if(cashRepo.save(cash) == null)
			return false;	
		
		return true;
	}
	public List<CashInfo> showCashList(long userId){
		List<Cash> cashList = cashRepo.findCashByUser(userRepo.getById(userId));
		if(cashList.size()==0)
			return null;
		
		List<CashInfo> cashInfo = new ArrayList<CashInfo>();	
		for(Cash c : cashList) {
			cashInfo.add(new CashInfo(c.getId(),c.getCharity().getName(),c.getTime(),c.getName(),c.getBank(),c.getAmount()));
		}		
		return cashInfo;
	}
		
}
