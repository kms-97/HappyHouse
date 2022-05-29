package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.repo.CodeRepo;
import com.ssafy.happyhouse.vo.DongVO;
import com.ssafy.happyhouse.vo.GugunVO;
import com.ssafy.happyhouse.vo.SidoVO;

@Service
public class codeServiceImpl implements codeService {
	private final CodeRepo repo;
	
	public codeServiceImpl(CodeRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public List<SidoVO> getSidoCode() {
		return repo.getSido();
	}
	
	@Override
	public List<GugunVO> getGugunCode(String sidoCode) {
		return repo.getGugun(sidoCode);
	}
	
	@Override
	public List<DongVO> getDongCode(String gugunCode) {
		return repo.getDong(gugunCode);
	}
	
	@Override
	public List<DongVO> getAllDong() {
		return repo.getAllDong();
	}
	
	@Override
	public List<DongVO> getDongByKeyword(String keyword) {
		return repo.getDongByKeyword(keyword);
	}
	
	@Override
	public String getRandomDong() {
		return repo.getRandomDong();
	}
}
