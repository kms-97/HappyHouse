package com.ssafy.happyhouse.dto;

import java.util.List;

public class BoardDto {
	private int no;
	private int type;
	private String writerId;
	private String writerName;
	private String title;
	private String content;
	private String regTime;
	private int commentNo;
	private List<CommentDto> comments;
	
	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int no, int type, String writerId, String writerName, String title, String content, String regTime,
			int commentNo, List<CommentDto> comments) {
		super();
		this.no = no;
		this.type = type;
		this.writerId = writerId;
		this.writerName = writerName;
		this.title = title;
		this.content = content;
		this.regTime = regTime;
		this.commentNo = commentNo;
		this.comments = comments;
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

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
}
