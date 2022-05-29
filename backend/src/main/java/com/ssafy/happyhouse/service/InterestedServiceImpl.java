package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.repo.InterestedRepo;
import com.ssafy.happyhouse.vo.AptVO;

@Service
public class InterestedServiceImpl implements InterestedService {
	private final InterestedRepo repo;
	
	public InterestedServiceImpl(InterestedRepo repo) {
		this.repo = repo;
	}

	@Override
	public void addInterested(String username, String aptCode) {
		this.repo.addInterested(username, aptCode);
	};

	@Override
	public void deleteInterested(String username, String aptCode) {
		this.repo.deleteInterested(username, aptCode);
	};
	
	@Override
	public List<AptVO> getInterested(String username) {
		return this.repo.getInterested(username);
	};
}
