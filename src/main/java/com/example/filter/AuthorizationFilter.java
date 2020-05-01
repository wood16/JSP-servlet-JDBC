package com.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.constant.SystemConstant;
import com.example.model.UserModel;
import com.example.utils.SessionUtil;

public class AuthorizationFilter implements Filter{

	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		if(url.startsWith("/admin")) {
			UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			
			if(model != null) {
				if(model.getRole().getCode().equals(SystemConstant.ADMIN)) {
//					phai la Admin ms dc qua 
					filterChain.doFilter(servletRequest, servletResponse);
					
				}
				else if(model.getRole().getCode().equals(SystemConstant.USER)){
//					da dang nhap nhung khong phai ADMIN ma la USER
					response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not-permission&alert=danger");
				}
			} 
			else {
//				chua dang nhap
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not-login&alert=danger");
			}
		} 
		else {
//			URL ko can filter thi dc qua 
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
