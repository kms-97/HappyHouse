package com.ssafy.happyhouse.vo;

import java.math.BigInteger;

public class AptVO {
	private String aptCode;
	private String aptName;
	private String dongCode;
	private String dongName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String roadName;
	private int roadNameBonbun;
	private int roadNameBubun;
	
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
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getlng() {
		return lng;
	}
	public void setlng(String lng) {
		this.lng = lng;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}	
}
