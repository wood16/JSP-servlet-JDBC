package com.example.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.constant.SystemConstant;
import com.example.model.NewsModel;
import com.example.paging.PageRequest;
import com.example.paging.Pageble;
import com.example.service.ICategoryService;
import com.example.service.INewService;
import com.example.sort.Sorter;
import com.example.utils.FormUtil;
import com.example.utils.MessageUtil;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	
	ResourceBundle resourceBundle =ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsModel model = FormUtil.toModel(NewsModel.class, request);
		String view ="";
		if(model.getType().equals(SystemConstant.LIST)) {
			
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), 
					new Sorter(model.getSortName(), model.getSortBy()));
			
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/new/list.jsp";
			
		} else if(model.getType().equals(SystemConstant.EDIT)) {
//			id != null -> cập nhật
			if(model.getId() != null) {
				
				model = newService.findOne(model.getId());
			} 
//			id == null -> thêm mới
			else {
		
			}
			request.setAttribute("categories",categoryService.findAll());
			view = "/views/admin/new/edit.jsp";
		}
		
//		String message = request.getParameter("message");
//		String alert = request.getParameter("alert");
//		if(message != null && alert != null) {
//			request.setAttribute("messageResponse", resourceBundle.getString(message));
//			request.setAttribute("alert", alert);
//		}
		
		MessageUtil.showMessage(request);
		
//		setAttribute( key, value ) đẻ cho các file  .jsp viết  ${key}  để sử dụng 
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
		requestDispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
