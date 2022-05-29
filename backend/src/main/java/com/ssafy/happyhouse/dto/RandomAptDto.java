package com.ssafy.happyhouse.dto;

import java.util.List;

import com.ssafy.happyhouse.vo.AptVO;

public class RandomAptDto {
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private	List<AptVO> randomApt;

	public RandomAptDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RandomAptDto(String dongCode, String sidoName, String gugunName, String dongName, List<AptVO> randomApt) {
		super();
		this.dongCode = dongCode;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.randomApt = randomApt;
	}

	public String getAptCode() {
		return dongCode;
	}

	public void setAptCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public List<AptVO> getRandomApt() {
		return randomApt;
	}

	public void setRandomApt(List<AptVO> randomApt) {
		this.randomApt = randomApt;
	}
}
