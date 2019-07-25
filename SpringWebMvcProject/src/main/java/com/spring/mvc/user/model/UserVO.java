package com.spring.mvc.user.model;

import java.util.Date;

import lombok.Data;


@Data
public class UserVO {
	
	private String userId;
	private String userPw;
	private String userName;
	private Date userRegDate;
	private String sessionId;
	private Date limitTime;
	
	//자동로그인 체크여부
	private boolean autoLogin;
	
}






