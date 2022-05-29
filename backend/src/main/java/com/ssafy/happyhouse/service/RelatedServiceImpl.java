package com.ssafy.happyhouse.service;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.RelatedDto;
import com.ssafy.happyhouse.repo.RelatedRepo;

@Service
public class RelatedServiceImpl implements RelatedService {
	private final RelatedRepo repo;
	
	public RelatedServiceImpl(RelatedRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public RelatedDto getRelated(String keyword) {
		return repo.getRelated(keyword);
	}
}
