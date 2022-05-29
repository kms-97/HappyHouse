package com.ssafy.happyhouse.dto;

public class CommentDto {
	private int no;
	private String writerId;
	private String writerName;
	private String title;
	private String content;
	private String regTime;
	
	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(int no, String writerId, String writerName, String title, String content, String regTime) {
		super();
		this.no = no;
		this.writerId = writerId;
		this.writerName = writerName;
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

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
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
