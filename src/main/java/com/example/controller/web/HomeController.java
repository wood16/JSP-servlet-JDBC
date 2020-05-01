package com.example.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.UserModel;
import com.example.service.iUserService;
import com.example.utils.FormUtil;
import com.example.utils.SessionUtil;

@WebServlet(urlPatterns = {"/home-page","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet{
	
	@Inject
	private iUserService userService;

	private static final long serialVersionUID = 1L;
	
	ResourceBundle resourceBundle =ResourceBundle.getBundle("message");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action != null && action.equals("login")) {
//			get cac gia tri cua para tu url 
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if(message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/login.jsp");
			requestDispatcher.forward(request, response);
		}
		else if(action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
//			xoa session ng dung -> tra ve trang home
			response.sendRedirect(request.getContextPath() + "/home-page");
		}
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/web/home.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, request);
			UserModel u = new UserModel();
			u = userService.findByUsernameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			if(u != null) {
				SessionUtil.getInstance().putValue(request, "USERMODEL", u);
				if(u.getRole().getCode().equals("ADMIN")) {
					response.sendRedirect(request.getContextPath() + "/admin-home-page");
				}
				else if(u.getRole().getCode().equals("USER")) {
					response.sendRedirect(request.getContextPath() + "/home-page");
				}
			} else {

				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username-password-invalid&alert=danger");
			
			}
		}
	}

}
