package com.koitt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.koitt.vo.User;

/*
 * UserDao 테스트 목적
 * 1. 데이터베이스에 정보를 저장할 add 메소드 테스트
 * 2. 저장된 정보를 가져오는 get 메소드 테스트
 */
public class UserDao {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void add(User user) throws SQLException {
		Connection c = this.dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"INSERT INTO user(id, name, password) VALUES (?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws SQLException {
		Connection c = this.dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM user WHERE id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		// user 변수를 null 값으로 초기화
		User user = null;
		
		// 사용자가 존재한다면 user 객체를 만들고 값을 넣어준다.
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		c.close();
		
		// 결과가 없으면 예외발생
		if (user == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return user;
	}
	
	// user 테이블 내부 데이터 전체를 삭제하는 메소드
	public void deleteAll() throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("DELETE FROM user");
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	// user에 저장된 행의 수를 리턴하는 메소드
	public int getCount() throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM user");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
}






