package com.ssafy.happyhouse.repo;

import java.util.List;

import com.ssafy.happyhouse.vo.UserVO;

public interface UserRepo {
	UserVO readUser(String username);
	
	List<String> readAuthority(String username);
	
	// 회원 가입
	int signUp(UserVO userVO);
	
	// 초기 권한 등록
	int initAuth(String username);
	
	// 업데이트
	int update(UserVO user);
	
	// 탈퇴
	int delete(String username);
}
