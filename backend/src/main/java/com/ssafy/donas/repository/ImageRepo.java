package com.ssafy.donas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Image;
	
public interface ImageRepo extends JpaRepository<Image, Long>{

}
