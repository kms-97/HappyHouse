package com.ssafy.happyhouse.controller;

import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.secutiry.JwtTokenProvider;
import com.ssafy.happyhouse.service.UserService;
import com.ssafy.happyhouse.vo.UserVO;

@RestController
@CrossOrigin("*")
public class UserController {
    private final PasswordEncoder encoder;
    private final JwtTokenProvider provider;
    private final UserService service;
    
    public UserController(PasswordEncoder encoder, JwtTokenProvider provider, UserService service) {
		this.encoder = encoder;
		this.provider = provider;
		this.service = service;
	}
    
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> map) {
		String username = map.get("username");
		String password = map.get("password");
		
    	try {
    		UserVO user = service.readUser(username);
    		
	        if (!encoder.matches(password, user.getPassword())) {
	            throw new IllegalArgumentException();
	        }
	        
    	    return new ResponseEntity<String>(
    	    		provider.createToken(user.getUsername(), user.getEmail(), user.getNickname(), service.getAuthorities(user.getUsername())), 
    	    		HttpStatus.OK
    	    	);
    	} catch(UsernameNotFoundException e) {
    		System.out.println(e);
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    	
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    // 회원가입
    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserVO user) {
    	try {
        	user.setPassword(encoder.encode(user.getPassword()));
        	service.signUp(user);
        	service.Authinit(user.getUsername());
        	
        	return new ResponseEntity<>(HttpStatus.OK);
    	} catch(DuplicateKeyException e) {
    		return new ResponseEntity<>(HttpStatus.CONFLICT);
    	} catch(Exception e) {
    		System.out.println(e);
    	}

    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    // 정보 수정
    @PutMapping("/user/update")
    public ResponseEntity<?> update(@RequestBody UserVO user) {
    	try {
        	user.setPassword(encoder.encode(user.getPassword()));
        	UserVO dbuser = service.readUser(user.getUsername());
        	
        	if (dbuser.getPassword().equals(user.getPassword())) {
        		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        	}
        	
        	service.update(user);
    	    return new ResponseEntity<String>(
    	    		provider.createToken(user.getUsername(), user.getEmail(), user.getNickname(), service.getAuthorities(user.getUsername())), 
    	    		HttpStatus.OK
    	    	);
    	} catch(DuplicateKeyException e) {
    		return new ResponseEntity<>(HttpStatus.CONFLICT);
    	} catch(Exception e) {
    		System.out.println(e);
    	}

    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    // 탈퇴
    @DeleteMapping("/user/delete")
    public ResponseEntity<?> delete(@RequestBody Map<String, String> map) {
		String username = map.get("username");
		String password = map.get("password");
		
		try {
        	String encodedPassword = encoder.encode(password);
        	UserVO dbuser = service.readUser(username);
        	
        	if (dbuser.getPassword().equals(encodedPassword)) {
        		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        	}
        	
        	service.delete(username);
    	    return new ResponseEntity<> (HttpStatus.OK);
    	} catch(Exception e) {
    		System.out.println(e);
    	}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
