package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberServiceImpl;
import com.kh.spring.member.model.vo.Member;

@Controller // Controller 타입의 어노테이션을 붙여주면 빈 스캐닝을 통해 자동으로 빈 등록이 됨
public class MemberController {
	
	@Autowired // DI(Dependency Injection) 특징
	private MemberServiceImpl mService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	/*
	 * [스프링을 배우기 전]
	 * 1. 화면에서 요소들을 controller로 보낸다.
	 * 2. controller에서는 그 요소들에 해당 하는 각각의 Servlet으로 가공 후 Service로 보낸다.
	 * 3. DB와 연동 후 Dao로 보낸다.
	 * 4. DB에 직접적으로 붙어 화면에서 요청한 값을 가져온다.
	 * 
	 * [스프링을 배운 후]
	 * 1. 화면에서 요소들을 Dispatcher Servlet으로 보낸다.
	 * 2. Dispatcher Servlet에서는 해당 요소들이 어떤 Controller에서 쓰여야 되는지 알아내어 해당 Controller로 보낸다.
	 */
	
	/*
	@RequestMapping(value="login.me") // RequestMapping 타입의 어노테이션을 붙여줌으로써 HandlerMapping을 등록
	public void loginMember() {
		
		
		
	}
	
	@RequestMapping(value="insert.me")
	public void insertMember() {
		
		
		
	}
	
	@RequestMapping(value="update.me")
	public void updateMember() {
		
		
		
	}
	*/
	
	/*
	 * * 파라미터(요청시 전달값)를 받는 방법 (요청시 전달되는 값들 처리방법)
	 * 
	 * 1. HttpServletRequest를 이용해서 전달받기 (기존의 jsp/servlet 방식)
	 * 		해당 메소드의 매개변수로 HttpServletRequest를 작성해두면
	 * 		스프링 컨테이너가 해당 메소드 호출시(실행시) 자동으로 해당 객체를 생성해서 인자로 주입해줌
	 */
	/*
	@RequestMapping("login.me")
	public String loginMember(HttpServletRequest request) {
		
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		
		System.out.println("ID : " + userId);
		System.out.println("PWD : " + userPwd);
		
		return "main";
		
	}
	*/
	
	/*
	 * 2. @RequestParam 어노테이션을 이용하는 방법
	 * 		request.getParameter("키") : 벨류의 역할을 대신 해주는 어노테이션
	 */
	/*
	@RequestMapping("login.me")
	public String loginMember( @RequestParam(value="id", defaultValue="aaa") String userId,
							   @RequestParam(value="pwd") String userPwd ) {
		
		System.out.println("ID : " + userId);
		System.out.println("PWD : " + userPwd);
		
		return "main";
		
	}
	*/
	
	/*
	 * 3. @RequestParam 어노테이션을 생략하는 방법
	 * 		** 단, 매개변수명 name값 (요청시 전달값의 키값)과 동일하게 세팅해둬야 자동으로 같이 주입됨
	 */
	/*
	@RequestMapping("login.me")
	public String loginMember(String id, String pwd) {
		
		System.out.println("ID : " + id);
		System.out.println("PWD : " + pwd);
		
		Member m = new Member();
		m.setUserId(id);
		m.setUserPwd(pwd);
		
		// Service 쪽 메소드에 m을 전달하며 조회
		
		return "main";
		
	}
	*/
	
	/*
	 * 4. 커맨드 객체 방식
	 * 		해당 메소드 매개변수로
	 * 		요청시 전달값을 담고자 하는 vo클래스 타입을 세팅 후
	 * 		요청시 전달값의 키값(name값)을 vo클래스에 담고자 하는 필드명으로 작성
	 * 
	 * 		스프링 컨테이너가 해당 객체를 기본생성자로 생성 후 setter 메소드 찾아서
	 * 		요청시 전달값을 해당 필드에 담아주는 내부적인 원리
	 * 
	 * 		** 반드시 name 속성값(키값)과 담고자 하는 필드명이 동일해야됨!!
	 */
	/*
	 * * 요청처리 후 응답페이지로 포워딩 또는 url 재요청, 응답데이터 담는 방법
	 * 
	 * 1. 스프링에서 제공하는 Model 객체를 사용하는 방법
	 * 		포워딩할 뷰로 전달하고자 하는 데이터를 맵 형식(key-value)으로 담을 수 있는 영역
	 * 		Model 객체는 requestScope 이다.
	 * 		단, setAttribute가 아닌 addAttribute 메소드 이용
	 */
	/*
	@RequestMapping("login.me")
	public String loginMember(Member m, Model model, HttpSession session) {
		
		Member loginMember = mService.loginMember(m);
		
		if(loginMember == null) { 
			
			// 로그인 실패 => 에러메세지 requestScope에 담아서 에러페이지(/WEB-INF/views/common/errorPage.jsp)로 포워딩
			model.addAttribute("errorMsg", "로그인 실패");
			
			return "common/errorPage";
			
		}
		else { 

			// 로그인 성공 => loginMember를 sessionScope에 담고 메인페이지 url 재요청
			session.setAttribute("loginMember", loginMember);
			
			return "redirect:/";
		
		}
		
	}
	*/
	
	/*
	 * 2. 스프링에서 제공하는 ModelAndView 객체를 이용하는 방법
	 * 
	 * 		Model은 데이터를 key-value 세트로 담을 수 있는 공간이라고 한다면
	 * 		View는 응답 뷰에 대한 정보를 담을 수 있는 공간
	 */

	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
		
		/* 암호화 작업 전에 했던 과정
		Member loginMember = mService.loginMember(m);
		
		if(loginMember == null) { 
			
			// 로그인 실패 => 에러메세지 requestScope에 담아서 에러페이지(/WEB-INF/views/common/errorPage.jsp)로 포워딩
			mv.addObject("errorMsg", "로그인 실패");
			
			mv.setViewName("common/errorPage");
			
		}
		else { 

			// 로그인 성공 => loginMember를 sessionScope에 담고 메인페이지 url 재요청
			session.setAttribute("loginMember", loginMember);
			
			mv.setViewName("redirect:/");
			
		}
		
		return mv;
		*/
		
		// 암호화 작업 후에 해야되는 과정
		// Member m userId 필드 : 사용자가 입력한 아이디
		//			userPwd 필드 : 사용자가 입력한 비번(평문)
		Member loginMember = mService.loginMember(m);
		// loginMember : 오로지 아이디만을 가지고 조회된 회원
		// loginMember userPwd 필드 : db에 기록된 비번(암호문)
		
		if(loginMember != null && bcryptPasswordEncoder.matches(m.getUserPwd(), loginMember.getUserPwd())) {
			// 로그인 성공
			session.setAttribute("loginMember", loginMember);
			
			mv.setViewName("redirect:/");
		}
		else {
			// 로그인 실패
			mv.addObject("errorMsg", "로그인 실패");
			
			mv.setViewName("common/errorPage");
		}
		
		return mv;
		
	}
	
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
		
	}
	
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		
		return "member/memberEnrollForm";
		
	}
	
	@RequestMapping("insert.me")
	public String insertMember(Member m, Model model, HttpSession session) {
		
		// 1. 한글 깨짐 => 스프링에서 제공하는 인코딩 필터 등록
		// 2. 나이를 입력하지 않았을 경우 "" 빈문자열이 넘어오는데 int형 필드에 담을 수 없어서 400 에러 발생
		// 		=> Member 클래스에 age 필드를 int형 --> String형으로 변경
		// 3. 비밀번호가 사용자가 입력한 있는 그대로의 평문
		//		=> Bcrypt 방식의 암호화를 통해서 암호문으로 변경
		//			=> 1) 스프링 시큐리티 모듈에서 제공 (라이브러리 추가 => pom.xml)
		//			=> 2) BcryptPassWordEncoder 라는 클래스를 빈으로 등록 (spring-security.xml)
		//			=> 3) web.xml에 spring-security.xml 파일을 pre-loading 할 수 있도록 root-context.xml 밑에 같이 작성
		
		// System.out.println("평문 : " + m.getUserPwd());
		
		// 암호화 작업 (암호문을 만들어내는 과정)
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		
		// System.out.println("암호문 : " + encPwd);
		
		m.setUserPwd(encPwd); // Member 객체에 userPwd에 평문이 아닌 암호문으로 변경
		
		int result = mService.insertMember(m);
		
		if(result > 0) { // 성공 => 메인페이지 url 재요청
			
			session.setAttribute("alertMsg", "회원가입 성공!!");
			
			return "redirect:/";
			
		}
		else { // 실패 => 에러 문구 담아서 에러페이지 포워딩
			
			model.addAttribute("errorMsg", "회원가입 실패ㅠㅠ");
			
			return "common/errorPage";
			
		}
		
	}
	
	@RequestMapping("myPage.me")
	public String myPage() {
		
		return "member/myPage";
		
	}
	
	@RequestMapping("update.me")
	public String updateMember(Member m, Model model, HttpSession session) {
		
		int result = mService.updateMember(m);
		
		if(result > 0) { // 수정 성공
			// db로부터 수정된 회원 정보를 다시 조회해와서
			// session에 loginMember 키값으로 덮어씌워야 함
			
			// Member updateMem = mService.loginMember(m);
			
			// session.setAttribute("loginMember", updateMem);
			session.setAttribute("loginMember", mService.loginMember(m));

			// alert 띄워줄 문구 담기
			session.setAttribute("alertMsg", "회원정보 변경 성공!!");
			
			// 마이페이지로 url 재요청
			return "redirect:myPage.me";
		}
		else { // 수정 실패 => 에러문구 담아서 에러페이지 포워딩
			model.addAttribute("errorMsg", "회원정보 변경 실패ㅠㅠ");
			
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping("delete.me")
	public String deleteMember(String userPwd, String userId, HttpSession session, Model model) {
		// userPwd : 회원탈퇴 요청시 사용자가 입력한 비번의 평문
		// session에 loginMember Member 객체의 userPwd 필드 : db로부터 조회된 비번(암호문)
		String encPwd = ((Member)(session.getAttribute("loginMember"))).getUserPwd();
		
		if(bcryptPasswordEncoder.matches(userPwd, encPwd)) { // 비번이 맞음 => 탈퇴처리
			
			int result = mService.deleteMember(userId);
			
			if(result > 0) { // 탈퇴 처리 성공 => session에 있는 loginMember 지우고 alert 문구 담기 => 메인페이지 url 재요청
				session.removeAttribute("loginMember");
				
				session.setAttribute("alertMsg", "탈퇴 성공!! 바이바이ㅠㅠ");
				
				return "redirect:/";
			}
			else { // 탈퇴 처리 실패 => 에러문구 담아서 에러페이지로 포워딩
				model.addAttribute("errorMsg", "탈퇴 실패ㅠㅠ");
				
				return "common/errorPage";
			}
			
		}
		else { // 비번이 틀림 => 비번 틀린 것을 알리고 마이페이지 노출
			
			session.setAttribute("alertMsg", "비번 틀림.. 확인 후 다시 하셈!!");
			
			return "redirect:myPage.me";
			
		}
		
		
	}
	
}
