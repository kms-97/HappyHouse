package com.ssafy.happyhouse.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.vo.DealVO;

@Repository
public interface DealRepo {
	List<DealVO> getDeal(String aptCode);
}
