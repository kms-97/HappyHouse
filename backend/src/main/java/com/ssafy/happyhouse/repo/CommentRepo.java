package com.ssafy.happyhouse.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.vo.CommentVO;

@Repository
public interface CommentRepo {
	List<CommentDto> getComments(String no);
	int registComment(CommentVO comment);
	int deleteComment(int no);
	int modifyComment(CommentVO comment);
}
