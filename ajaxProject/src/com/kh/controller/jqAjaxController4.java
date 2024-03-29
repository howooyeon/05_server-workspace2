package com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class jqAjaxController4
 */
@WebServlet("/jqAjax4.do")
public class jqAjaxController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jqAjaxController4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ArrayList<Member> list = new MemberService().selectMemeberList(); 했다고 가정
		
		ArrayList<Member> list = new ArrayList<Member>(); // [] => JSONArray
		list.add(new Member(1, "박철수", 30, "남")); // JSONObject{} [{}] 
		list.add(new Member(2, "차은우", 24, "남")); // JSONObject{}
		list.add(new Member(3, "김태리", 30, "여")); // JSONObject{} 
		
		// JSONArray [{}, {}, {}]
		/*
		JSONArray jArr = new JSONArray(); // []
		for(Member m : list) {
			JSONObject jObj = new JSONObject();
			jObj.put("userNo", m.getUserNo());
			jObj.put("userName", m.getUserName());
			jObj.put("age", m.getAge());
			jObj.put("gender", m.getGender());
			
			jArr.add(jObj);
		}
		*/
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
