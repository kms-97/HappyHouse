package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.service.DealService;
import com.ssafy.happyhouse.vo.DealVO;

@RestController
@CrossOrigin("*")
public class DealInfoController {
	private final DealService service;
	
	public DealInfoController(DealService service) {
		this.service = service;
	}

	@GetMapping("/deal/{aptCode}")
	public ResponseEntity<?> getAptDeal(@PathVariable String aptCode) {
		return new ResponseEntity<List<DealVO>> (service.getAptDeal(aptCode), HttpStatus.OK);
	}
}
