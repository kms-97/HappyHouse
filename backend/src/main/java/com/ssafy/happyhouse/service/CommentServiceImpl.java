package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.repo.CommentRepo;
import com.ssafy.happyhouse.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentRepo repo;
	
	public CommentServiceImpl(CommentRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public List<CommentDto> getComments(String no) {
		return this.repo.getComments(no);
	}
	
	@Override
	public int deleteComment(int no) {
		return this.repo.deleteComment(no);
	}
	
	@Override
	public int modifyComment(CommentVO comment) {
		return this.repo.modifyComment(comment);
	}
	
	@Override
	public int registComment(CommentVO comment) {
		return this.repo.registComment(comment);
	}
}
