package com.ssafy.happyhouse.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.repo.UserRepo;
import com.ssafy.happyhouse.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepo repo;
	
	public UserServiceImpl(UserRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = repo.readUser(username);
		user.setAuthorities(getAuthorities(username));
		
		return user;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<String> string_authorities = repo.readAuthority(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String authority: string_authorities) {
			authorities.add(new SimpleGrantedAuthority(authority));
		}
		return authorities;
	}
	
	@Override
	public UserVO readUser(String username) {
		return repo.readUser(username);
	}
	
	@Override
	public int signUp(UserVO user) {
		return repo.signUp(user);
	}
	
	@Override
	public int Authinit(String username) {
		return repo.initAuth(username);
	}
	
	@Override
	public int update(UserVO user) {
		return repo.update(user);
	}
	
	@Override
	public int delete(String username) {
		return repo.delete(username);
	}
}
