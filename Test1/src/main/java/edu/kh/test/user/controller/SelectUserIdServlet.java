package edu.kh.test.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.service.UserService;
import edu.kh.test.user.model.vo.User;

/**
 * Servlet implementation class SelectUserIdServlet
 */
@WebServlet("/search1.do")
public class SelectUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUserIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 필요한 데이터 뽑기
			String userId = request.getParameter("userId");
			int userAge = Integer.parseInt(request.getParameter("userAge"));
		// 2) 서비스 호출 및 결과받기
			User user = new UserService().selectUserId(userId, userAge);
					
		// 3) 응답화면 결정
			if(user == null) {
				request.setAttribute("errorMsg", "조회된 결과가 없습니다.");
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/searchFail.jsp");
				view.forward(request, response);
			} else {
				request.setAttribute("user", user);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/searchSuccess.jsp");
				view.forward(request, response);
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
