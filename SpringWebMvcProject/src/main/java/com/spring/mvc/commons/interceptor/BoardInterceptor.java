package com.spring.mvc.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터 클래스는 항상 HandlerInterceptorAdapter
//클래스를 상속받아야 합니다.
public class BoardInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("게시판 인터셉터 발동!");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			System.out.println("회원 인증 실패!");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('로그인을 해야해요!'); location.href='/mvc/user/login';</script>");
			out.flush();
			out.close();
			
			//response.sendRedirect("/mvc/user/login");
			return false; //인터셉터 통과 실패!
		}
		
		return true; //인터셉터 검사 통과!
	}
	
}











