package com.kh.board.model.service;

import java.sql.Connection;

import com.kh.board.model.dao.MemberDao;
import com.kh.board.model.vo.Member;
import com.kh.common.JDBCTemplate;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		JDBCTemplate.close(conn);
		return m;
		
	}
}
