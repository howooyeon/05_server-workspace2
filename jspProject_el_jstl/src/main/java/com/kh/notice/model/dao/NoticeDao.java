package com.kh.notice.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.notice.model.vo.Notice;

public class NoticeDao {
	private Properties prop = new Properties();
	
	public NoticeDao() {
		try {
			prop.loadFromXML(new FileInputStream(NoticeDao.class.getResource("/db/sql/notice-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Notice> selectNoticeList(Connection conn) {
		// select문 => ResultSet 객체 => 여러행 => ArrayList<Notice> 객체
		ArrayList<Notice> list = new ArrayList<Notice>(); // 텅빈리스트
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			// 1) 커서이동
			// 2) while로 array
			while (rset.next()) { // arraylist이기 때문에 계속 돌려줘야돼서 while 써줌
				list.add(new Notice(rset.getInt("notice_no"),
									rset.getString("notice_title"),
									rset.getString("user_id"), // 이거 중요함!!!!!!
									rset.getInt("count"),
									rset.getDate("create_date")
									));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			
		}
		return list;
	}

	public int insertNotice(Connection conn, Notice n) {
	
		// insert문 => 처리된 행수 => 트랜젝션 처리(서비스에서)
		
		PreparedStatement pstmt= null;
		int result = 0;
	
		String sql = prop.getProperty("insertNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, Integer.parseInt(n.getNoticeWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int increase(Connection conn, int noticeNo) {
		// update문 => 처리된 행수 => 트랜젝션처리 (서비스)
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Notice selectNotice(Connection conn, int noticeNo) {
		// select문 => ResultSet객체 => 한행 => Notice 객체
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice(rset.getInt("notice_no"),
								rset.getString("notice_title"),
								rset.getString("notice_content"),
								rset.getString("user_id"),
								rset.getDate("create_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}

	public int updateNotice(Connection conn, Notice n) {
		// update 문 => 처리된 행수 => 트랜젝션 처리
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteNotice(Connection conn, int num) {
		// update문 => 처리된 행수 => 트랜젝션 처리
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
}
