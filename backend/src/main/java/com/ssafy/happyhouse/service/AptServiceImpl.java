package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.DealHistoryDto;
import com.ssafy.happyhouse.dto.RandomAptDto;
import com.ssafy.happyhouse.repo.AptRepo;
import com.ssafy.happyhouse.vo.AptVO;

@Service
public class AptServiceImpl implements AptService {
	private final AptRepo repo;
	
	public AptServiceImpl(AptRepo repo) {
		this.repo = repo;
	}

	@Override
	public List<AptVO> getAllAptList() {
		return repo.getAllAptList();
	}
	
	@Override
	public List<AptVO> getAptList(String dongCode) {
		return repo.getAptList(dongCode);
	}
	
	@Override
	public List<AptVO> getAptByKeyword(String keyword) {
		return repo.getAptByKeyword(keyword);
	}
	
	@Override
	public List<AptVO> getAptByAptName(String name) {
		return repo.getAptByAptName(name);
	}
	
	@Override
	public List<AptVO> getAptByAptCode(String code) {
		return repo.getAptByAptCode(code);
	}
	
	@Override
	public List<AptVO> getAptByDongName(String name) {
		return repo.getAptByDongName(name);
	}
	
	@Override
	public DealHistoryDto getDealHistory(String aptCode) {
		return repo.getDealHistory(aptCode);
	}
	
	@Override
	public RandomAptDto getRandomApt(String dongCode, int num) {
		return repo.getRandomApt(dongCode, num);
	}
}
