package com.ssafy.donas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Charity;

public interface CharityRepo extends JpaRepository<Charity, Long>{
	Optional<Charity> findById(long id);

}
