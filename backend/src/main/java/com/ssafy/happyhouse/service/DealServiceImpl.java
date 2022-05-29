package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.repo.DealRepo;
import com.ssafy.happyhouse.vo.DealVO;

@Service
public class DealServiceImpl implements DealService {
	private final DealRepo repo;
	
	public DealServiceImpl(DealRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public List<DealVO> getAptDeal(String aptCode) {
		return this.repo.getDeal(aptCode);
	}
}
