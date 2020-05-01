package com.example.dao;

import java.util.List;

import com.example.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	public List<CategoryModel> findAll();
	CategoryModel findOne(long id);
	CategoryModel findOneByCode(String code);
}
