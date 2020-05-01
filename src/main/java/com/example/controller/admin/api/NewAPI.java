package com.example.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.NewsModel;
import com.example.model.UserModel;
import com.example.service.INewService;
import com.example.utils.HTTPUtil;
import com.example.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet{
	@Inject
	private INewService newService;
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
//		request: tu client len server
		request.setCharacterEncoding("UTF-8");
//		response: tu server len client
		response.setContentType("application/json");
		
//		convert tu json sang newModel 
		NewsModel newmodel = HTTPUtil.of(request.getReader()).toModel(NewsModel.class);
//		lay Usermodel dang trong session
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		
		newmodel.setCreatedBy(user.getUserName());
//		luu xuong database
		NewsModel news = new NewsModel();
		news = newService.save(newmodel);
//		server chuyen tu model sang json gui ve cho client
		mapper.writeValue(response.getOutputStream(), news);
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
//		request: tu client len server
		request.setCharacterEncoding("UTF-8");
//		response: tu server len client
		response.setContentType("application/json");
		
//		convert tu json sang newModel 
		NewsModel updateNew = HTTPUtil.of(request.getReader()).toModel(NewsModel.class);
//		lay Usermodel dang trong session
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		
		updateNew.setModifiedBy(user.getUserName());
//		luu xuong database
		NewsModel news = new NewsModel();
		news = newService.update(updateNew);
//		server chuyen tu model sang json gui ve cho client
		mapper.writeValue(response.getOutputStream(), news);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
//		request: tu client len server
		request.setCharacterEncoding("UTF-8");
//		response: tu server len client
		response.setContentType("application/json");
		
//		convert tu json sang newModel 
		NewsModel newmodel = HTTPUtil.of(request.getReader()).toModel(NewsModel.class);
		
		newService.delete(newmodel.getIds());
//		server chuyen tu model sang json gui ve cho client
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	
}
