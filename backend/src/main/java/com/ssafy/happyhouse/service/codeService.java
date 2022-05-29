package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.vo.DongVO;
import com.ssafy.happyhouse.vo.GugunVO;
import com.ssafy.happyhouse.vo.SidoVO;

public interface codeService {
	List<SidoVO> getSidoCode();
	List<GugunVO> getGugunCode(String sidoCode);
	List<DongVO> getDongCode(String gugunCode);
	List<DongVO> getAllDong();
	List<DongVO> getDongByKeyword(String keyword);
	String getRandomDong();
}
