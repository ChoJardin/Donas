package com.ssafy.donas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Charity;

public interface CharityRepo extends JpaRepository<Charity, Long>{

}
