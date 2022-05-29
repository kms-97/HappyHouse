package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.DealHistoryDto;
import com.ssafy.happyhouse.dto.RandomAptDto;
import com.ssafy.happyhouse.vo.AptVO;

public interface AptService {
	List<AptVO> getAllAptList();
	List<AptVO> getAptList(String dongCode);
	List<AptVO> getAptByKeyword(String keyword);
	List<AptVO> getAptByAptName(String name);
	List<AptVO> getAptByAptCode(String code);
	List<AptVO> getAptByDongName(String name);
 	DealHistoryDto getDealHistory(String aptCode);
 	RandomAptDto getRandomApt(String dongCode, int num);
}
