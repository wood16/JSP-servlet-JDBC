package com.example.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.example.dao.ICategoryDAO;
import com.example.model.CategoryModel;
import com.example.service.ICategoryService;

public class CategoryService implements ICategoryService{

//	private ICategoryDAO categoryDAO;
//	
//	public CategoryService() {
//		categoryDAO = new CategoryDAO();
//	}
//	
//	tuong duong
	
	@Inject
	private ICategoryDAO categoryDAO;
	
	

	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

}
