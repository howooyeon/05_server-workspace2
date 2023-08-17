package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		// 일반방식이 아닌 multipart/form-data로 전송하는 경우 request로부터 값 뽑기 불가!!
		// String boardTitle = request.getAttribute("title");
		
		// enctype이 multpart/form-data로 잘 전송되었을 경우 전반적인 내용들이 수행되도록
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 파일 업로드를 위한 라이브러리 : cos.jar(com.oreilly.servlet의 약자)
			// http://www.servlets.com
			
			// 1. 전달되는 파일 처리할 작업 내용 (전달되는 파일의 용량제한, 전달된 파일을 저장시킬 폴더경로)
			// 1_1) 전달되는 파일의 용량 제한(int maxSize => byte 단위) => 10Mbyte로 제한
			
			/*
			 * byte => kbyte => mbyte => gbyte => tbyte ....
			 * 
			 * 1kbyte == 1024byte
			 * 1mbyte == 1024kbyte == 1024*1024byte
			 * 10mbyte == 10 * 1024 * 1024 byte
			 * 
			 */
			
			int maxSize = 10*1024*1024;
			
			// 1_2) 전달된 파일을 저장시킬 폴더의 경로 알아내기(String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
			// 2. 전달된 파일의 파일명 수정 및 서버에 업로드 작업
			/*
			 *		>> HttpServletRequest request => MultiPartRequest multiRequest 변환
			 *		위 구문 한 줄 실행만으로 넘어온 첨부파일이 해당 폴더에 무조건 업로드 됨!!
			 *
			 *		단, 업로드시 파일명은 수정해주는게 일반적임! 그래서 파일명 수정시켜주는 객체 제시해야됨
			 *		=> 같은 파일명이 존재할 경우 덮어씌워질 수 있고, 파일에 한글/특문/띄어쓰기가 포함될 경우 서버에 따라 문제 발생
			 *		
			 *		기본적으로 파일명이 안 겹치도록 수정작업해주는 객체 있음
			 *		=> FileRenamePolicy 객체 (cos.jar에서 제공하는 객체)
			 *		=> 내부적으로 해당 클래스에 rename() 메소드 실행되면서 파일명 수정된 후 업로드
			 *		   rename(원본파일) {
			 *		    	기존에 동일한 파일명이 존재할 경우
			 *				파일명 뒤에 카운팅된 숫자를 붙여줌
			 *				ex) aaa.jpg, aaa1.jpg, aaa2.jpg
			 *					꽃.png, 꽃1.png
			 *				return 수정파일
			 *		    }
			 *
			 *		나만의 방식대로 절대 안 겹치도록 rename 할 수 있게 나만의 FileRenamePolicy 클래스 만들기(rename 메소드 만들기)!
			 *		com.kh.common.MyFilePolicy 클래스 만들기
			 *
			 *		
			 */
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			// 3. DB에 기록할 데이터를 뽑아서 VO에 주섬주섬 담기
		
			// 4. 서비스 요청(요청 처리)
			
			// 5. 응답뷰 지정
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
