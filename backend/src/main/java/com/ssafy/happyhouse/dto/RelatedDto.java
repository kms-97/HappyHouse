package com.ssafy.happyhouse.dto;

import java.util.List;

import com.ssafy.happyhouse.vo.AptVO;
import com.ssafy.happyhouse.vo.DongVO;

public class RelatedDto {
	private List<AptVO> apts;
	private List<DongVO> dongs;
	
	public RelatedDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<AptVO> getApts() {
		return apts;
	}

	public void setApts(List<AptVO> apts) {
		this.apts = apts;
	}

	public List<DongVO> getDongs() {
		return dongs;
	}

	public void setDongs(List<DongVO> dongs) {
		this.dongs = dongs;
	}
}
