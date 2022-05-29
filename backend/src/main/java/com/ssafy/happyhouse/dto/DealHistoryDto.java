package com.ssafy.happyhouse.dto;

import java.util.List;

import com.ssafy.happyhouse.vo.DealVO;

public class DealHistoryDto {
	private String aptCode;
	private String aptName;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String jibun;
	private String roadName;
	private int roadNameBonbun;
	private int roadNameBubun;
	private String lat;
	private String lng;
	private List<DealVO> history;
	
	public DealHistoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DealHistoryDto(String aptCode, String aptName, String sidoName, String gugunName, String dongName,
			String jibun, String roadName, int roadNameBonbun, int roadNameBubun, String lat, String lng,
			List<DealVO> history) {
		super();
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.jibun = jibun;
		this.roadName = roadName;
		this.roadNameBonbun = roadNameBonbun;
		this.roadNameBubun = roadNameBubun;
		this.lat = lat;
		this.lng = lng;
		this.history = history;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
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

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public int getRoadNameBonbun() {
		return roadNameBonbun;
	}

	public void setRoadNameBonbun(int roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}

	public int getRoadNameBubun() {
		return roadNameBubun;
	}

	public void setRoadNameBubun(int roadNameBubun) {
		this.roadNameBubun = roadNameBubun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public List<DealVO> getHistory() {
		return history;
	}

	public void setHistory(List<DealVO> history) {
		this.history = history;
	}
}
