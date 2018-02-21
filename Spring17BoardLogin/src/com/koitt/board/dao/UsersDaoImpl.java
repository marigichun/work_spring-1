package com.koitt.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

public class UsersDaoImpl implements UsersDao {
	
	private static final String MAPPER_NS = Users.class.getName();
	
	@Autowired
	private SqlSession session;

	@Override
	public List<Users> selectAll() throws UsersException {
		List<Users> list = null;
		
		try {
			list = session.selectList(MAPPER_NS + ".select-all"); 
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public Users select(Integer no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Users users) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserTypes(Integer no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Users users) {
		// TODO Auto-generated method stub
		
	}

}
