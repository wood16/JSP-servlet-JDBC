package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.RoleModel;
import com.example.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		try {
			
//			" " la ten cot trong mysql
			UserModel user = new UserModel();
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFullName(resultSet.getString("fullname"));
			user.setStatus(resultSet.getInt("status"));
			try {
//				Trong TH cau SQL ko co INNER JOIN vs bang role thi resultSet ko co 'code' va 'name' nen co the bi loi
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				user.setRole(role);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return user;
		} catch (SQLException e) {
			return null;
		}
	}

}
