package com.ssafy.happyhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.RelatedDto;
import com.ssafy.happyhouse.service.AptService;
import com.ssafy.happyhouse.service.RelatedService;
import com.ssafy.happyhouse.service.codeService;

@RestController
@CrossOrigin("*")
public class RelatedController {
	private final RelatedService relService;
	private final AptService aptService;
	private final codeService codeService;
	
	public RelatedController(RelatedService relService, AptService aptService, codeService codeService) {
		this.relService = relService;
		this.aptService = aptService;
		this.codeService = codeService;
	}

	@GetMapping("/relate/{keyword}")
	public ResponseEntity<?> getRelated(@PathVariable String keyword) {
		RelatedDto dto = new RelatedDto();
		
		dto.setApts(aptService.getAptByKeyword(keyword));
		dto.setDongs(codeService.getDongByKeyword(keyword));
		
		return new ResponseEntity<RelatedDto> (dto, HttpStatus.OK);
	}
}
