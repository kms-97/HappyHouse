package com.ssafy.happyhouse.dto;

import java.util.List;

import com.ssafy.happyhouse.vo.AptVO;

public class InterestedDto {
	List<String> aptCodes;
	List<AptVO> aptInfos;
	
	public InterestedDto() {
		super();
	}
	
	public InterestedDto(List<String> aptCodes, List<AptVO> aptInfos) {
		super();
		this.aptCodes = aptCodes;
		this.aptInfos = aptInfos;
	}

	public List<String> getAptCodes() {
		return aptCodes;
	}
	public void setAptCodes(List<String> aptCodes) {
		this.aptCodes = aptCodes;
	}
	public List<AptVO> getAptInfos() {
		return aptInfos;
	}
	public void setAptInfos(List<AptVO> aptInfos) {
		this.aptInfos = aptInfos;
	}
}
