package com.example.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.example.dao.ICategoryDAO;
import com.example.dao.INewDAO;
import com.example.dao.impl.CategoryDAO;
import com.example.model.CategoryModel;
import com.example.model.NewsModel;
import com.example.paging.Pageble;
import com.example.service.INewService;

public class NewService implements INewService{
	
	@Inject
	private INewDAO newDAO;
	
	@Inject
	private ICategoryDAO categoryDAO;

	@Override
	public List<NewsModel> findByCategoryId(Long categoryid) {	
		return newDAO.findByCategoryId(categoryid);
	}

	@Override
	public NewsModel save(NewsModel newmodel) {
		newmodel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//		newmodel.setCreatedBy(""); da xet ben API
		

		CategoryModel categoryModel = categoryDAO.findOneByCode(newmodel.getCategoryCode());
		newmodel.setCategoryId(categoryModel.getId());
		
//		khi save xuong database se tra ve id
		Long newId = newDAO.save(newmodel);
//		tra lai model vua luu vao database bang cach tim theo id
		return newDAO.findOne(newId);
		
	}

	@Override
	public NewsModel update(NewsModel updateNew) {
		NewsModel oldNew = newDAO.findOne(updateNew.getId());
		updateNew.setCreatedBy(oldNew.getCreatedBy());
		updateNew.setCreatedDate(oldNew.getCreatedDate());
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//		updateNew.setModifiedBy(""); da xet ben API
		
//		chuyen tu code(category) sang id(category) de luu xuong database news		
		CategoryModel categoryModel = categoryDAO.findOneByCode(updateNew.getCategoryCode());
		updateNew.setCategoryId(categoryModel.getId());
		
		newDAO.update(updateNew);
		return newDAO.findOne(updateNew.getId());
	}

	@Override
	public void delete(long[] ids) {
//		Để xóa được New thì phải xóa comment trc, do News-Comment là 1-n
//		1 - n -> Khóa của News sang Comment làm khóa ngoại 
		for(long id:ids) {
			newDAO.delete(id);
		}
		
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

	@Override
	public NewsModel findOne(long id) {
		NewsModel newsModel = newDAO.findOne(id);
//		tim category co id = id ben newmodel
		CategoryModel categoryModel = categoryDAO.findOne(newsModel.getCategoryId());
		
		newsModel.setCategoryCode(categoryModel.getCode());
		return newsModel;
	}
	
	

}
