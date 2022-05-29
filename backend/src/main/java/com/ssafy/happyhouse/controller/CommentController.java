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
import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.service.CommentService;
import com.ssafy.happyhouse.vo.CommentVO;

@RestController
@CrossOrigin("*")
public class CommentController {
	private final CommentService service;
	
	public CommentController(CommentService service) {
		this.service = service;
	}
	
	@GetMapping("/comment/{no}")
	public ResponseEntity<?> getComments(@PathVariable String no) {
		return new ResponseEntity<List<CommentDto>>(service.getComments(no), HttpStatus.OK);
	}
	
	@PostMapping("/comment")
	public ResponseEntity<?> registComment(@RequestBody CommentVO comment) {
		int result = service.registComment(comment);
		
		if (result == 1) {
			// 작성 성공 시 게시글로 리다이렉트
			//HttpHeaders httpHeaders = new HttpHeaders();
	        //httpHeaders.setLocation(new URI("/board/${comment.board_no}"));
	        
	        return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/comment")
	public ResponseEntity<?> modifyComment(@RequestBody CommentVO comment) {
		int result = service.modifyComment(comment);
		
		if (result == 1) {
	        return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/comment/{no}")
	public ResponseEntity<?> DeleteComment(@PathVariable int no) {
		int result = service.deleteComment(no);
		
		if (result == 1) {
	        return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
