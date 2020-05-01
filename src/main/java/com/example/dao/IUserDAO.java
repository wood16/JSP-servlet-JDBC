package com.example.dao;

import com.example.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
	UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
