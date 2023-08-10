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
	
	public Member updateMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		// 트랜젝션 처리
		if(result > 0) { // 성공
			commit(conn);
			
			// 갱신된 회원 객체 다시 조회해보기
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
			
		} else { // 실패
			rollback(conn);
		}
		
		close(conn); // 꼭 닫아줘야됨 db락걸림
		return updateMem;
	}
	
	public Member updatePwd(String userId, String userPwd, String updatePwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updatePwd(conn, userId, userPwd, updatePwd);
		Member updateMem = null;
		
		if(result > 0) {
			commit(conn);
			
			// 갱신된 회원 객체 다시 조회해오기
			updateMem = new MemberDao().selectMember(conn, userId);
			
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
	}

	public int deleteMember(String userId, String userPwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
			
		if(result > 0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
		
}
	
	
