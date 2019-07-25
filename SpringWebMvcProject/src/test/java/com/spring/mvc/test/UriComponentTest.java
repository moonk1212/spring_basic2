package com.spring.mvc.test;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentTest {

	@Test
	public void testUriComp() throws Exception {
		
		UriComponents ucp = UriComponentsBuilder.newInstance()
							.path("/board/content")
							.queryParam("boardNo", 33)
							.queryParam("page", 7)
							.queryParam("keyword", "야옹이")
							.build();
		
		System.out.println(ucp.toUriString());
		
	}
	
}






