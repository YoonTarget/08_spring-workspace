package com.kh.ajax.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

	/*
	 * 1. HttpServletResponse  객체로 응답데이터 응답하기 (기존의 jsp, servlet 때 했던 Stream 이용한 방식)
	@RequestMapping("ajax1.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		
		System.out.println(name);
		System.out.println(age);
		
		// 요청처리를 위해 서비스 호출
		
		// 요청처리가 다 됐다는 가정하에 요청한 페이지에 응답할 데이터가 있을 경우 뿌려주는 데이터
		String responseData = name + "은(는) " + age + "살 입니다.";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(responseData);
		
	}
	*/
	
	/*
	 * 2. 응답할 데이터로 문자열로 리턴
	 * 		=> HttpServletResponse 안 써도 됨
	 * 		단, 문자열 리턴하면 원래는 포워딩 방식이었음 => 응답뷰로 인식해서 해당 뷰 페이지를 찾고 있음
	 * 		따라서 내가 리턴하는 문자열이 응답뷰가 아니라 응답데이터라는 걸 선언해야 함
	 * 		어노테이션 @ResponseBody를 붙여야됨
	 */
	@ResponseBody
	@RequestMapping(value="ajax1.do", produces="text/html; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		
		String responseData = name + "은(는) " + age + "살 입니다.";
		
		return responseData;
		
	}
	
}
