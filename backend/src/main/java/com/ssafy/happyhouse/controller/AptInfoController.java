package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.DealHistoryDto;
import com.ssafy.happyhouse.dto.RandomAptDto;
import com.ssafy.happyhouse.service.AptService;
import com.ssafy.happyhouse.service.codeService;
import com.ssafy.happyhouse.vo.AptVO;

@RestController
@CrossOrigin("*")
public class AptInfoController extends HttpServlet {
	private final AptService service;
	private final codeService cService;
	
	public AptInfoController(AptService service, codeService cService) {
		this.service = service;
		this.cService = cService;
	}
	
	@GetMapping("/aptList/{dongCode}")
	public ResponseEntity<?> getAptList(@PathVariable String dongCode) {
		return new ResponseEntity<List<AptVO>>(service.getAptList(dongCode), HttpStatus.OK);
	}
	
	@GetMapping("/aptName/{name}")
	public ResponseEntity<?> getAptListbyAptName(@PathVariable String name) {
		return new ResponseEntity<List<AptVO>>(service.getAptByAptName(name), HttpStatus.OK);
	}
	
	@GetMapping("/aptCode/{code}")
	public ResponseEntity<?> getAptbyAptCode(@PathVariable String code) {
		return new ResponseEntity<List<AptVO>>(service.getAptByAptCode(code), HttpStatus.OK);
	}
	
	@GetMapping("/aptDong/{name}")
	public ResponseEntity<?> getAptListbyDongName(@PathVariable String name) {
		return new ResponseEntity<List<AptVO>>(service.getAptByDongName(name), HttpStatus.OK);
	}
	
	@GetMapping("/aptList")
	public ResponseEntity<?> getAllAptList() {
		return new ResponseEntity<List<AptVO>>(service.getAllAptList(), HttpStatus.OK);
	}

	@GetMapping("/dealHistory/{code}")
	public ResponseEntity<?> getDealHistory(@PathVariable String code) {
		return new ResponseEntity<DealHistoryDto>(service.getDealHistory(code), HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping("/getRandomApt/{num}")
	public ResponseEntity<?> getRandomApt(@PathVariable int num) {
		String dongCode = cService.getRandomDong();
		return new ResponseEntity<RandomAptDto>(service.getRandomApt(dongCode, num), HttpStatus.OK);
	}
}
