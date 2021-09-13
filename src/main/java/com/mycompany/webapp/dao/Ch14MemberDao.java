package com.mycompany.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14Member;

@Repository
public class Ch14MemberDao {

	private static final Logger logger = LoggerFactory.getLogger(Ch14MemberDao.class);

	@Resource
	private DataSource dataSource;

	public void insert(Ch14Member member) {
		Connection conn = null;
		try {
			// 커넥션 풀에서 연결 객체 하나를 가져오기
			conn = dataSource.getConnection();

			// 작업 처리
			String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getMname());
			pstmt.setString(3, member.getMpassword());
			pstmt.setInt(4, member.getMenabled());
			pstmt.setString(5, member.getMrole());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 커넥션 풀로 연결 객체를 반납하기 (연결을 끊는 것이 아니라 커넥션 풀로 반납)
			try {conn.close(); } catch(Exception e) {}
		}
	}

	public Ch14Member selectByMid(String mid) {
		return null;
	}
}
