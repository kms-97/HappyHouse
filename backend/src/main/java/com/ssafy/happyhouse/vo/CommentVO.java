package com.ssafy.happyhouse.vo;

public class CommentVO {
	private int no;
	private int boardNo;
	private String writerId;
	private String title;
	private String content;
	private String regTime;
	
	public CommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentVO(int no, int boardNo, String writerId, String title, String content, String regTime) {
		super();
		this.no = no;
		this.boardNo = boardNo;
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

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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
