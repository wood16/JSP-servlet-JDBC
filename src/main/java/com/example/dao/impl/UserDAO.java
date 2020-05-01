package com.example.dao.impl;

import java.util.List;

import com.example.dao.IUserDAO;
import com.example.mapper.UserMapper;
import com.example.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
//		INNER JOIN = SELECT 2 bang
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
		sql.append("INNER JOIN role AS r ON r.id = u.roleid ");
		sql.append("WHERE username = ? AND password = ? AND status = ?");
		List<UserModel> users =  query(sql.toString(), new UserMapper(), username, password, status);
		if(users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

}
