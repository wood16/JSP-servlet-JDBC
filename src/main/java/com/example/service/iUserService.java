package com.example.service;

import com.example.model.UserModel;

public interface iUserService {
	UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
