package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.service.InterestedService;
import com.ssafy.happyhouse.vo.AptVO;

@RestController
@CrossOrigin("*")
public class InterestedController {
	private final InterestedService service;
	
	public InterestedController(InterestedService service) {
		this.service = service;
	}
	
	@GetMapping("/interested/{username}")
	public ResponseEntity<?> getInterested(@PathVariable String username) {
		return new ResponseEntity<List<AptVO>>(this.service.getInterested(username), HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping("/interested/{username}/{aptCode}")
	public ResponseEntity<?> addInterested(@PathVariable String username, @PathVariable String aptCode) {
		this.service.addInterested(username, aptCode);
		return new ResponseEntity<List<AptVO>>(this.service.getInterested(username), HttpStatus.OK);
	}
	
	@Transactional
	@DeleteMapping("/interested/{username}/{aptCode}")
	public ResponseEntity<?> deleteInterested(@PathVariable String username, @PathVariable String aptCode) {
		this.service.deleteInterested(username, aptCode);
		return new ResponseEntity<List<AptVO>>(this.service.getInterested(username), HttpStatus.OK);
	}
}
