package com.ssafy.happyhouse.repo;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.RelatedDto;

@Repository
public interface RelatedRepo {
	RelatedDto getRelated(String keyword);
}
