package com.kh.member.model.service;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import static com.kh.common.JDBCTemplate.*;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		// 1) 커넥션 객체 생성
		// 2) Dao 호출
		Connection conn = /*JDBCTemplate.*/getConnection();
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		/*JDBCTemplate.*/close(conn);
		return m; // 또 던져
	}
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn, m);
		
		// 트렌젝션 처리
		if(result > 0) { // 성공했다는 의미
			commit(conn);
		}else { // 실패
			rollback(conn); // dao에서 insert문을 할 때 conn close안하는 이유가 여깄음
		}
		
		close(conn);
		
		return result;
		
	}
}
