package com.spring.mvc.user.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService service;
	
	//join.jsp 화면처리 요청
	@GetMapping("/join")
	public String register() throws Exception {
		
		return "user/join";
	}
	
	//회원가입 처리 요청
	@PostMapping("/join")
	@ResponseBody
	public String register(@RequestBody UserVO user) throws Exception {
		System.out.println("/user/join 요청 발생: POST");
		System.out.println("Parameter: " + user);
		service.join(user);
		
		return "joinSuccess";
	}
	
	//ID 중복확인 요청 처리
	@PostMapping("/idCheck")
	@ResponseBody
	public String confirmId(@RequestBody String userId) throws Exception {
		
		System.out.println("중복확인할 ID: " + userId);
		String result = null;
		
		int rn = service.isDuplicateId(userId);
		if(rn == 1) {
			System.out.println("아이디 중복!");
			result = "NO";
		} else {
			System.out.println("아이디 사용가능!");
			result = "OK";
		}
		
		return result;
	}
	
	//로그인 페이지 요청
	@GetMapping("/login")
	public String login() throws Exception {
		return "user/login";
	}
	
	//로그인 검증 요청
	@PostMapping("/loginCheck")
	@ResponseBody
	public String login(@RequestBody UserVO inputData
			, /*HttpServletRequest request*/
			HttpSession session, HttpServletResponse response) throws Exception {
		
		//서버에서 세션객체를 얻는 방법
		//1. HttpServletRequest객체 사용
//		HttpSession session = request.getSession();
		//2. 컨트롤러의 메서드의 매개변수로 HttpSession타입을 지정.
		
		
		/*
		 # 클라이언트가 전송한 id값과 pw값을 가지고 DB에서 회원의 정보를
		   조회해서 불러온다음 값 비교를 통해
		   1. 아이디가 없을 경우 클라이언트측으로 문자열 "idFail" 전송
		   2. 비밀번호가 틀렸을 경우 문자열 "pwFail"전송
		   3. 로그인 성공시 문자열 "loginSuccess" 전송
		 */
		
		System.out.println("/user/loginCheck 요청! : POST");
		System.out.println("Parameter: " + inputData);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserVO dbData = service.login(inputData.getUserId());
		System.out.println("DB에서 조회된 회원 정보: " + dbData);
		
		String result = null;
		
		if(dbData != null) { //아이디 있음
			if(encoder.matches(inputData.getUserPw(), dbData.getUserPw())) { //비밀번호 일치
				
				session.setAttribute("login", dbData);
				result = "loginSuccess";
				
				//자동 로그인 쿠키 생성
				if(inputData.isAutoLogin()) {
					System.out.println("자동로그인 체크됨!");
					
					long limitTime = 60 * 60 * 24 * 90;
					
					//자동로그인 유지시간을 날짜객체로 변환
					Date limitDate = new Date(System.currentTimeMillis() + (limitTime * 1000));
					
					Cookie loginCookie = new Cookie("loginCookie", session.getId());
					loginCookie.setPath("/");
					loginCookie.setMaxAge((int)limitTime);
					response.addCookie(loginCookie);
					
					service.keepLogin(dbData.getUserId(),
							session.getId(), limitDate);
				}
				
			} else {//비밀번호 불일치
				result = "pwFail";
			}
		} else { //아이디 없음
			result = "idFail";
		}
		
		return result;
	}
	
	
	//로그아웃 요청
	@GetMapping("/logout")
	public String logout(HttpSession session
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		UserVO user = (UserVO)session.getAttribute("login");
		
		if(user != null) {
			session.removeAttribute("login");
			session.invalidate();
			
			//로그아웃시 자동로그인 쿠키 삭제
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(user.getUserId(), 
						"none", new Date());
			}
		}
		
		return "redirect:/";
	}
	
}













