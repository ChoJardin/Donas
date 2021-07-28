package com.ssafy.donas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.donas.domain.Search;

public interface SearchRepo extends JpaRepository<Search, Long>{

}
