package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.vo.CommentVO;

public interface CommentService {
	List<CommentDto> getComments(String no);
	int registComment(CommentVO comment);
	int deleteComment(int no);
	int modifyComment(CommentVO comment);
}
