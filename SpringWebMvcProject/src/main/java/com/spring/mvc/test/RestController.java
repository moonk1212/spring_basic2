package com.spring.mvc.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mvc.user.model.UserVO;

@Controller
@RequestMapping("/rest")
public class RestController {
	
	/*
	 * @ResponseBody : 
	 * ViewResolver에게 데이터를 
	 * 리턴하지 않고 요청한 클라이언트측에
	 *  리턴데이터를 바로응답합니다.
	 */
	
	@RequestMapping("/hello")
	@ResponseBody 
	public String sayHello() {
		
		return "hello world!!";
	}
	
	@RequestMapping("/hobbys")
	@ResponseBody
	public List<String> hobbys() {
		
		List<String> list 
			= Arrays.asList("축구", "수영", "음악감상");
		
		return list;
	}
	
	@RequestMapping("/person")
	@ResponseBody
	public UserVO person() {
		
		UserVO user = new UserVO();
		user.setUserId("abc1234");
		user.setUserPw("aaa1111");
		user.setUserName("김빠빠");
		
		return user;
	}
	
	@RequestMapping("/people")
	@ResponseBody
	public List<UserVO> people() {
		
		UserVO user1 = new UserVO();
		user1.setUserId("abc1234");
		user1.setUserPw("aaa1111");
		user1.setUserName("김빠빠");
		UserVO user2 = new UserVO();
		user2.setUserId("def1234");
		user2.setUserPw("ddd1111");
		user2.setUserName("박까까");
		
		List<UserVO> list = new ArrayList<>();
		list.add(user1); list.add(user2);
		
		return list;
	}
	
	

}









