package com.ssafy.happyhouse.repo;

import java.util.List;

import com.ssafy.happyhouse.vo.DongVO;
import com.ssafy.happyhouse.vo.GugunVO;
import com.ssafy.happyhouse.vo.SidoVO;

public interface CodeRepo {
	List<SidoVO> getSido();
	List<GugunVO> getGugun(String sidoCode);
	List<DongVO> getDong(String gugunCode);
	List<DongVO> getAllDong();
	List<DongVO> getDongByKeyword(String keyword);
	String getRandomDong();
}
