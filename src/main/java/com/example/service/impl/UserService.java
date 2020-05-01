package com.example.service.impl;

import javax.inject.Inject;

import com.example.dao.IUserDAO;
import com.example.model.UserModel;
import com.example.service.iUserService;

public class UserService implements iUserService{

	@Inject
	private IUserDAO userDAO;
	
	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		return userDAO.findByUsernameAndPasswordAndStatus(username, password, status);
	}

}
