package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.service.BoardService;
import com.ssafy.happyhouse.vo.BoardVO;

@RestController
@CrossOrigin("*")
public class BoardController {
	private final BoardService service;
	
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	@GetMapping("/board")
	public ResponseEntity<?> getAllBoard() {
		return new ResponseEntity<List<BoardDto>>(service.getAllBoard(), HttpStatus.OK);
	}
	
	@PostMapping("/board")
	public ResponseEntity<?> registBoard(@RequestBody BoardVO board) {
		int result = service.registBoard(board);
		
		if (result == 1) {
			// 작성 성공 시 게시글 목록으로 리다이렉트
			//HttpHeaders httpHeaders = new HttpHeaders();
	        //httpHeaders.setLocation(new URI("/"));
	        
	        return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/board/{no}")
	public ResponseEntity<?> deleteBoard(@PathVariable int no) {
		int result = service.deleteBoard(no);
		
		if (result == 1) {
	        return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/board/{no}")
	public ResponseEntity<?> modifyBoard(@PathVariable int no, @RequestBody BoardVO board) {
		int result = service.modifyBoard(board);
		
		if (result == 1) {
			// 수정 성공 시 게시글 목록으로 리다이렉트
			HttpHeaders httpHeaders = new HttpHeaders();
	        //httpHeaders.setLocation(new URI("/"));
	        
	        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
