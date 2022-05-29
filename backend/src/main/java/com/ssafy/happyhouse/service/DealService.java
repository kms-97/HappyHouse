package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.vo.DealVO;

public interface DealService {
	List<DealVO> getAptDeal(String aptCode);
}
