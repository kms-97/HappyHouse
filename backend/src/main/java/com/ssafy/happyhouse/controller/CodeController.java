package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.service.codeService;
import com.ssafy.happyhouse.vo.DongVO;
import com.ssafy.happyhouse.vo.GugunVO;
import com.ssafy.happyhouse.vo.SidoVO;

@RestController
@CrossOrigin("*")
public class CodeController {
	private final codeService service;
	
	public CodeController(codeService service) {
		this.service = service;
	}
	
	@GetMapping("/sido")
	@Deprecated
	public ResponseEntity<?> getSidoCode() {
		return new ResponseEntity<List<SidoVO>> (service.getSidoCode(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun/{sidoCode}")
	@Deprecated
	public ResponseEntity<?> getGugunCode(@PathVariable String sidoCode) {
		return new ResponseEntity<List<GugunVO>> (service.getGugunCode(sidoCode), HttpStatus.OK);
	}
	
	@GetMapping("/dong/{gugunCode}")
	public ResponseEntity<?> getDongCode(@PathVariable String gugunCode) {
		return new ResponseEntity<List<DongVO>> (service.getDongCode(gugunCode), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<?> getAllDong() {
		return new ResponseEntity<List<DongVO>> (service.getAllDong(), HttpStatus.OK);
	}
	
	@GetMapping("/dongName/{name}")
	public ResponseEntity<?> getDongByName(@PathVariable String name) {
		return new ResponseEntity<List<DongVO>> (service.getDongByKeyword(name), HttpStatus.OK);
	}
}
