package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.member.model.vo.Member;
import static com.kh.common.JDBCTemplate.*;

public class MemberDao {
	// 항상 변수구나 뒤에 있는 건 이름~ 
	private Properties prop = new Properties();
	
	public MemberDao() { // 메소드 아니라 memberdao의 기본 생성자 생성자는 반환형 x 괄호안에 변수 없으니까 기본 생성자다 ~ 실행할 내용이 있어서 쓴거임
		// 언제 실행됨? service.java 가보면 dao호출할 때마다 읽을 거 서버 구동없이 쿼리를 읽을 수가 있음
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath(); // 멤버dao 클래스 이 파일패스 경로에가서 있으면 찾아와달라
		
		try {
			prop.loadFromXML(new FileInputStream(filePath)); // 파일패스에 있는 파일을 읽어들여 xml파일을 읽어들이면서 키는 벨류
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
		// select문 => ResultSet 객체(한행) => Member 객체
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql에 select ~ 들어있는거
		String sql = prop.getProperty("loginMember"); // xml 키값과 일치해야됨
		
		try {
			pstmt = conn.prepareStatement(sql); // 미완성된 쿼리 고냥 외우삼
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd); // 마지막 숫자가 물음표의 갯수랑 같다
			
			rset = pstmt.executeQuery(); // 조회된 결과가 있다면 한행 | 조회결과 없다고 하면 아무것도 안담김
			
			if(rset.next()) { // 커서를 한행한행 옮기느누거
				m = new Member(rset.getInt("USER_NO"), // new Member 잖어 생성자 호출!! 매개변수 생성자 호출
								rset.getString("USER_ID"), // rset과 관련된 부분은 db의 컬럼명으로
								rset.getString("USER_PWD"),
								rset.getString("USER_NAME"),
								rset.getString("PHONE"),
								rset.getString("EMAIL"),
								rset.getString("ADDRESS"),
								rset.getString("INTEREST"),
								rset.getDate("ENROLL_DATE"),
								rset.getDate("MODIFY_DATE"),
								rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn);
			close(pstmt);
		}
		
		return m; // 던지면 서비스가 받음
		
	}
	
	public int insertMember(Connection conn, Member m) {
		// insert문 => 처리된 행 수 => 트렌젝션 처리
		int result = 0; // insert문이기 때문
		
		PreparedStatement pstmt = null;
		
		// rset은 select에만 필요
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			result = pstmt.executeUpdate(); // 반환형이 int 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		// update문 => 처리된 행수 => 트랜젝션 처리
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql); // 미완성된 쿼리
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate(); // 꼭 담아줘야됨
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection conn, String userId) {
		// select문 => 한행 => ResultSet 객체 => Member 객체
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USER_NO"), // new Member 잖어 생성자 호출!! 매개변수 생성자 호출
						rset.getString("USER_ID"), // rset과 관련된 부분은 db의 컬럼명으로
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("PHONE"),
						rset.getString("EMAIL"),
						rset.getString("ADDRESS"),
						rset.getString("INTEREST"),
						rset.getDate("ENROLL_DATE"),
						rset.getDate("MODIFY_DATE"),
						rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}

	public int updatePwd(Connection conn, String userId, String userPwd, String updatePwd) {
		// update 문 => 처리된 행수 => 트랜젝션 처리
		
		int result =0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	
}
