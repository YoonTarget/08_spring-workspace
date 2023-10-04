package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardServiceImpl;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {
	
	@Autowired
	private BoardServiceImpl bService;
	
	// 메뉴바 클릭시 	/list.bo					(기본적으로 1번 페이지 요청)
	// 페이징바 클릭시 	/list.bo?cpage=요청하는 페이지수
	
	/*
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="cpage", defaultValue = "1") int currentPage, Model model) {
		
		// System.out.println(currentPage);
		int listCount = bService.selectListCount();
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);
		
		ArrayList<Board> list = bService.selectList(pi);
		
		model.addAttribute("pi", pi);

		model.addAttribute("list", list);
		
		// 포워딩할 뷰 (/WEB-INF/views/		board/boardListView		.jsp)
		return "board/boardListView";
		
	}
	*/
	
	@RequestMapping("list.bo")
	public ModelAndView selectList(@RequestParam(value="cpage", defaultValue = "1") int currentPage, ModelAndView mv) {
		
		// System.out.println(currentPage);
		int listCount = bService.selectListCount();
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);
		ArrayList<Board> list = bService.selectList(pi);
		
		/*
		mv.addObject("pi", pi);
		mv.addObject("list", list);
		mv.setViewName("board/boardListView");
		*/
		
		mv.addObject("pi", pi).addObject("list", list).setViewName("board/boardListView");
		
		// 포워딩할 뷰 (/WEB-INF/views/		board/boardListView		.jsp)
		return mv;
		
	}
	
	@RequestMapping("enrollForm.bo")
	public String enrollForm() {
		// /WEB-INF/views/board/boardEnrollForm.jsp
		return "board/boardEnrollForm";
	}
	
	@RequestMapping("insert.bo")
	public String insertBoard(Board b, MultipartFile upfile, HttpSession session, Model model) {
		
		// System.out.println(b);
		// System.out.println(upfile); // 첨부파일을 선택했든 안했든 생성되는 객체임 (다만 filename에 원본명이 있냐, ""이냐)
		
		// 전달된 파일이 있을 경우 => 파일명 수정 작업 후 서버 업로드 => 원본명, 서버 업로드 된 경로를 b에 이어서 담기
		if(!upfile.getOriginalFilename().equals("")) {
			
			// 파일명 수정 작업 후 서버에 업로드 시키기("flower.png" => "2023100412153012345.png")
			/*
			String originName = upfile.getOriginalFilename(); // "flower.png"
			
			// "20231004154607" (년월일시분초)
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // "20231004154708"
			int ranNum = (int)(Math.random() * 90000 + 10000); // 21318 (5자리 랜덤값)
			String ext = originName.substring(originName.lastIndexOf("."));
			
			String changeName = currentTime + ranNum + ext; // "2023100415470821318.png"
			
			// 업로드 시키고자 하는 폴더의 물리적인 경로를 알아내기
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
			
			try {
				
				upfile.transferTo(new File(savePath + changeName));
			
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			*/
			
			String changeName = saveFile(upfile, session);
			
			// 원본명, 서버 업로드 된 경로를 Board b에 이어서 담기
			b.setOriginName(upfile.getOriginalFilename());
			b.setChangeName("resources/uploadFiles/" + changeName);
			
		}
		
		// 넘어온 첨부파일 있을 경우 b : 제목, 작성자, 내용, 파일 원본명, 파일 저장경로
		// 넘어온 첨부파일 없을 경우 b : 제목, 작성자, 내용
		
		int result = bService.insertBoard(b);
		
		if(result > 0) { // 성공 => 게시글 리스트페이지(list.bo url 재요청)
			session.setAttribute("alertMsg", "게시글 등록 성공!!");
			
			return "redirect:list.bo";
		}
		else { // 실패 => 에러페이지 포워딩
			model.addAttribute("errorMsg", "게시글 등록 실패ㅠㅠ");
			
			return "common/errorPage";
		}
		
	}

	// 현재 넘어온 첨부파일 그 자체를 서버의 폴더에 저장시키는 역할
	public String saveFile(MultipartFile upfile, HttpSession session) {
		
		String originName = upfile.getOriginalFilename(); // "flower.png"
		
		// "20231004154607" (년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // "20231004154708"
		int ranNum = (int)(Math.random() * 90000 + 10000); // 21318 (5자리 랜덤값)
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String changeName = currentTime + ranNum + ext; // "2023100415470821318.png"
		
		// 업로드 시키고자 하는 폴더의 물리적인 경로를 알아내기
		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
		
		try {
			
			upfile.transferTo(new File(savePath + changeName));
		
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return changeName;
		
	}
	
	@RequestMapping("detail.bo")
	public String selectBoard(int bno, HttpSession session, Model model) {
		
		// bno에는 상세조회 하고자 하는 해당 게시물의 번호 담겨있음
		
		// 해당 게시글 조회수 증가 서비스 호출 결과 받기 (update 하고 옴)
		int result = bService.increaseCount(bno);
		
		if(result > 0) {
			// >> 성공적으로 조회수 증가
			// 		>> boardDetailView.jsp 상에 필요한 데이터 조회 (게시글 상세정보 조회 서비스 호출)
			//		>> 조회된 데이터 담아서
			//		>> board/boardDetailView.jsp로 포워딩
			
			Board b = bService.selectBoard(bno);
			
			model.addAttribute("b", b);
			
			return "board/boardDetailView";
			
		}
		else {
			// >> 조회수 증가 실패
			// 		>> 에러문구 담아서 에러페이지 포워딩
			
			model.addAttribute("errorMsg", "조회수 증가 실패ㅠㅠ");
			
			return "common/errorPage";
			
		}
		
	}
	
}
