package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		int result = new MemberService().deleteMember(userId, userPwd);
		
		HttpSession session = request.getSession();
		
		if(result > 0) { // 성공
		session.setAttribute("alertMsg", "성공적으로 회원탈퇴 되었습니다."); // request는 포워딩 방식일 때만 가
		session.removeAttribute("loginMember");
		// System.out.println(result + "컨트롤러 result 성공");
		
		// 성공 => index 페이지 => url 재요청 !! => /jsp
		response.sendRedirect(request.getContextPath());
			
		}else { // 실패
			// request. 2
			session.setAttribute("alertMsg", "회원탈퇴를 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/myPage.me");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
