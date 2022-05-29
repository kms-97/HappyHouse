package com.ssafy.happyhouse.repo;

import java.util.List;
import com.ssafy.happyhouse.vo.TradeVO;

public interface TradeRepo {
	// 거래 내역 입력
	void insertTrade(TradeVO vo);
	
	// 모든 거래 내역 불러오기
	List<TradeVO> getTradeAll();
	
	// 동 이름으로 검색
	List<TradeVO> getTradeByDongName(String dongName);
	
	// 동 코드로 검색
	List<TradeVO> getTradeByDongCode(String dongCode);
	
	// 아파트 명으로 검색
	List<TradeVO> getTradeByAptName(String aptName);
	
	// 아파트 코드로 검색
	List<TradeVO> getTradeByAptCode(int aptCode);
}
