package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	// 1. Connection 객체 생성한 후 해당 Connection 객체를 반환시켜주는 getConnection 메소드
	public static Connection getConnection() {
		
		// 뭘 반환해줘야 된다고???
		Connection conn = null;
		
		Properties prop = new Properties(); // Map 계열 컬렉션 (key-value)
		
		try {
			prop.load(new FileInputStream("driver.properties")); // 읽어들일 땐 로드 써야됨
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		try {
			// jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 접속하고자 하는 db의 url, 계정명, 비밀번호 제시해서 Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SERVER", "SERVER");
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	// 2_1. Connection 객체 전달 받아서 commit 시켜주는 commit 메소드
	// 2_2. 	" rollback 시켜주는 rollback 메소드
	
	// 3_1. Connection 객체 전달 받아서 반납시켜주는 close 메소드
	
	// 3_2. Statement 객체 전달 받아서 반납시켜주는 close 메소드
	
	// 3_3. ResultSet 객체 전달 받아서 반납시켜주는 close 메소드
}
