package com.ssafy.happyhouse.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ssafy.happyhouse.vo.UserVO;

public interface UserService extends UserDetailsService {
	Collection<GrantedAuthority> getAuthorities(String username);
	
	UserVO readUser(String username);
	
	int signUp(UserVO user);
	
	int Authinit(String username);
	
	int update(UserVO user);
	
	int delete(String username);
}
