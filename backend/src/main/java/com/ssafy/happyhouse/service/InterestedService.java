package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.vo.AptVO;

public interface InterestedService {
	// 관심지역 등록
	void addInterested(String username, String aptCode);
	// 관심지역 삭제
	void deleteInterested(String username, String aptCode);
	// 관심지역 조회
	List<AptVO> getInterested(String username);
}
