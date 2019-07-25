package com.spring.mvc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MvcUserDAOTest {
	
	@Autowired
	private IUserDAO dao;
	
	/*@Test
	public void insertTest() throws Exception {
		
		UserVO user = new UserVO();
		user.setUserId("abc1234");
		user.setUserPw("aaa1111!");
		user.setUserName("홍길동");
		dao.join(user);
		System.out.println("회원가입 성공");
		
	}*/
	
	/*
	@Test 
	public void duplicateTest() throws Exception {
		
		int result = dao.isDuplicateId("abc123");
		if(result == 1) {
			System.out.println("ID가 중복됨!");
		} else {
			System.out.println("ID 사용 가능!");
		}
		
	}*/
	
	//로그인 테스트
	@Test 
	public void loginTest() throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String inputId = "melon";
		String inputPw = "mmm1234!";
		
		UserVO user = dao.login(inputId);
		
		if(user != null) {
			System.out.println("ID검증 통과!");
			String dbPw = user.getUserPw();
			
			if(encoder.matches(inputPw, dbPw)) { //비밀번호 검증
				System.out.println("로그인 성공!");
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
		} else {
			System.out.println("존재하지 않는 회원입니다.");
		}
		
	}

}







