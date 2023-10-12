package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Member;

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
	/*
	@ResponseBody
	@RequestMapping(value="ajax1.do", produces="text/html; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		
		String responseData = name + "은(는) " + age + "살 입니다.";
		
		return responseData;
		
	}
	*/
	
	/*
	// 다수의 응답데이터가 있을 경우??
	@RequestMapping("ajax1.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		// 요청처리가 다 됐다는 가정하에 데이터 응답
		
		
		// response.setContentType("text/html; charset=UTF-8");
		// response.getWriter().print(name);
		// response.getWriter().print(age);
		// 연이어서 출력하는 데이터가 하나의 문자열로 연이어져 있음
		
		
		// JSON(JavaScript Object Notation) 형태로 담아서 응답
		// JSONArray => [값, 값, 값, ...]			=> 자바에서의 ArrayList와 유사 (list에 추가할 때는 add)
		// JSONObject => {키:값, 키:값, 키:값, ...}	=> 자바에서의 HashMap과 유사 (map에 추가할 때는 put)
		
		// 첫번째 방법. JSONArray로 담아서 응답
		
		// JSONArray jArr = new JSONArray(); // []
		// jArr.add(name); // ["차은우"]
		// jArr.add(age); // ["차은우", 20]
		
		
		// 두번째 방법. JSONObject로 담아서 응답
		JSONObject jobj = new JSONObject(); // {}
		jobj.put("name", name); // {name:'차은우'}
		jobj.put("age", age); // {name:'차은우', age:20}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jobj);
		
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="ajax1.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		
		JSONObject jobj = new JSONObject(); // {}
		jobj.put("name", name); // {name:'차은우'}
		jobj.put("age", age); // {name:'차은우', age:20}
		
		return jobj.toJSONString(); // "{name:'차은우', age:20}"
		
	}
	/*
	@ResponseBody
	@RequestMapping(value="ajax2.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod2(int num) {
		
		// Member m = mService.selectMember(num);
		Member m = new Member("user01", "pass01", "차은우", 20, "010-1111-2222");
		
		// JSON 형태로 만들어서 응답
		JSONObject jobj = new JSONObject(); // {}
		jobj.put("userId", m.getUserId()); // {userId:'user01'}
		jobj.put("userName", m.getUserName()); // {userId:'user01', name:'차은우'}
		jobj.put("age", m.getAge());
		jobj.put("phone", m.getPhone());
		
		return jobj.toJSONString();
		
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="ajax2.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod2(int num) {
		
		// Member m = mService.selectMember(num);
		Member m = new Member("user01", "pass01", "차은우", 20, "010-1111-2222");
		
		return new Gson().toJson(m); // {userId:'user01', userPwd:'pass01', ...}
		
	}
	
	@ResponseBody
	@RequestMapping(value="ajax3.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod3() {
		
		// ArrayList<Member> list = mService.selectList();
		ArrayList<Member> list = new ArrayList<Member>();
		list.add(new Member("user01", "pass01", "차은우", 20, "010-1111-2222")); // [{은우 객체}]
		list.add(new Member("user02", "pass02", "차응우옌", 30, "010-3333-4444")); // [{은우 객체}, {응우옌 객체}]
		list.add(new Member("user03", "pass03", "어먼상", 40, "010-5555-6666")); // [{은우 객체}, {응우옌 객체}, {어먼상 객체}]

		return new Gson().toJson(list); // [{}, {}, {}]
		
	}
	
	@RequestMapping("test.do")
	  public void excelDownload(HttpServletResponse response) throws IOException {
//      Workbook wb = new HSSFWorkbook();
      Workbook wb = new XSSFWorkbook();
      Sheet sheet = wb.createSheet("첫번째 시트");
      Row row = null;
      Cell cell = null;
      int rowNum = 0;

      // Header
      row = sheet.createRow(rowNum++);
      cell = row.createCell(0);
      cell.setCellValue("번호");
      cell = row.createCell(1);
      cell.setCellValue("이름");
      cell = row.createCell(2);
      cell.setCellValue("제목");

      // Body
      for (int i=0; i<3; i++) {
          row = sheet.createRow(rowNum++);
          cell = row.createCell(0);
          cell.setCellValue(i);
          cell = row.createCell(1);
          cell.setCellValue(i+"_name");
          cell = row.createCell(2);
          cell.setCellValue(i+"_title");
      }

      // 컨텐츠 타입과 파일명 지정
      response.setContentType("ms-vnd/excel");
//      response.setHeader("Content-Disposition", "attachment;filename=example.xls");
      response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

      // Excel File Output
      wb.write(response.getOutputStream());
      wb.close();
  }
	
}
