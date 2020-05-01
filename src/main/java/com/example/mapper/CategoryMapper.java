package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		
		try {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setCode(resultSet.getString("code"));
			categoryModel.setId(resultSet.getLong("id"));
			categoryModel.setName(resultSet.getString("name"));
			return categoryModel;
		} catch (SQLException e) {
			return null;
		}
	}

}
