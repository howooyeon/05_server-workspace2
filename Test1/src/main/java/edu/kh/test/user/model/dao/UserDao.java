package edu.kh.test.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.kh.test.user.common.JDBCTemplate;
import edu.kh.test.user.model.vo.User;

public class UserDao {
	public User selectUser(Connection conn, int userNo) {
		// select문 => ResultSet 객체 => 한행조회 => User객체
		
		// 1) jdbc 객체 생성 + 필요한 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		User user = null;
		
		// 2) sql 쿼리 작성
		String sql = "SELECT * FROM TB_USER WHERE USER_NO = ?";
		
		// 3) pstmt, rset 채워넣기
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery(); // 뭐라도 있더나 | 비어있거나
			
			if(rset.next()) {
				user = new User(rset.getInt("user_no"),
								rset.getString("user_id"),
								rset.getString("user_name"),
								rset.getInt("user_age")); // dao는 db랑 굉장히 밀접한 관계다 그래서 이것도 컬럼명이랑 동일시해줌
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		return user;
	}

	public User selectUserId(Connection conn, String userId, int userAge) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		User user = null;
		
		// 2) sql 쿼리 작성
			String sql = "SELECT * FROM TB_USER WHERE USER_ID = ? AND USER_AGE = ?";
			

			try {
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, userId);
				pstmt.setInt(2, userAge);
				
				rset = pstmt.executeQuery(); // 뭐라도 있더나 | 비어있거나
				
				if(rset.next()) {
					user = new User(rset.getInt("user_no"),
									rset.getString("user_id"),
									rset.getString("user_name"),
									rset.getInt("user_age")); // dao는 db랑 굉장히 밀접한 관계다 그래서 이것도 컬럼명이랑 동일시해줌
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
	
		return user;
	}
}
