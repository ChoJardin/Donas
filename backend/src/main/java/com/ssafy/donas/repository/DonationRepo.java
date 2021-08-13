package com.ssafy.donas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.donas.domain.Donation;
import com.ssafy.donas.domain.User;

public interface DonationRepo extends JpaRepository<Donation, Long>{
	
	@Query(value = "SELECT sum(amount) FROM Donation")
	public Long sumDonation();
	@Query(value = "SELECT sum(quarter) FROM Charity")
	public Long sumQuarDonation();
	@Query(value = "SELECT sum(amount) FROM Donation WHERE user_id= :user")
	public Long sumDonationById(@Param("user") User user);
	List<Donation> findDonationByUser(User user);
}
