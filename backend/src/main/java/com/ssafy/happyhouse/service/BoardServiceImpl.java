package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.repo.BoardRepo;
import com.ssafy.happyhouse.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	private final BoardRepo repo;
	
	public BoardServiceImpl(BoardRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public int deleteBoard(int no) {
		return repo.deleteBoard(no);
	}
	
	@Override
	public List<BoardDto> getAllBoard() {
		return repo.getAllBoard();
	}
	
	@Override
	public int modifyBoard(BoardVO board) {
		return repo.modifyBoard(board);
	}
	
	@Override
	public int registBoard(BoardVO board) {
		return repo.registBoard(board);
	}
}
