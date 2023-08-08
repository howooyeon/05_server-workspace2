package edu.kh.test.user.model.service;

import java.sql.Connection;

import edu.kh.test.user.common.JDBCTemplate;
import edu.kh.test.user.model.dao.UserDao;
import edu.kh.test.user.model.vo.User;

public class UserService {
	// 1. 커넥션 객체 생성
	
	public User selectUser(int userNo) {
		// 1. 커넥션 객체 생성
		// 2. dao 호출 및 결과받기
		// 3. 커넥션 객체 반납
		Connection conn = JDBCTemplate.getConnection();
		
		
		// 2. dao 호출 및 결과받기 => conn도 보내야함!!!
		User user = new UserDao().selectUser(conn, userNo);
		
		
		// 3. 커넥션 객체 반납
		JDBCTemplate.close(conn);
		
		return user;
	}
	
	public User selectUserId(String userId, int userAge) {
		Connection conn = JDBCTemplate.getConnection();
		
		User user = new UserDao().selectUserId(conn, userId, userAge);
		
		JDBCTemplate.close(conn);
		
		return user;
		
	}
	
	
}
