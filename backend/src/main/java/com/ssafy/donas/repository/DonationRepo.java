package com.ssafy.donas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.donas.domain.Donation;

public interface DonationRepo extends JpaRepository<Donation, Long>{
	
	@Query(value = "SELECT sum(amount) FROM Donation")
	public Long sumDonation();
	@Query(value = "SELECT sum(quarter) FROM Charity")
	public Long sumQuarDonation();
}
