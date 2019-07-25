package com.spring.mvc.user.repository;

import java.util.Map;

import com.spring.mvc.user.model.UserVO;

public interface IUserDAO {
	
	//회원가입 기능
	void join(UserVO user) throws Exception;
	
	//아이디 중복확인 처리기능
	int isDuplicateId(String userId) throws Exception;

	//로그인 처리기능
	UserVO login(String userId) throws Exception;
	
	//자동로그인 처리
	void keepLogin(Map<String, Object> datas) throws Exception;
	
	//세션아이디로 회원정보 불러오는 기능
	UserVO getUserWithSessionId(String sessionId) throws Exception;
	
}









