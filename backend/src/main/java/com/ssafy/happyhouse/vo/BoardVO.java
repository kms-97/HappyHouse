package com.ssafy.happyhouse.vo;

public class BoardVO {
	private int no;
	private int type;
	private String writerId;
	private String title;
	private String content;
	private String regTime;
	
	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BoardVO(int no, int type, String writerId, String title, String content, String regTime) {
		super();
		this.no = no;
		this.type = type;
		this.writerId = writerId;
		this.title = title;
		this.content = content;
		this.regTime = regTime;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
}
