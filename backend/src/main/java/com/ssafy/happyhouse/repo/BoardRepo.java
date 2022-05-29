package com.ssafy.happyhouse.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.vo.BoardVO;

@Repository
public interface BoardRepo {
	List<BoardDto> getAllBoard(); // type에 맞는 게시판의 모든 글 가져오기
	int registBoard(BoardVO board); // 게시글 작성
	int deleteBoard(int no); // 게시글 삭제
	int modifyBoard(BoardVO board); // 게시글 수정.
}
