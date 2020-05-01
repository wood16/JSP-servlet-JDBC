package com.example.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
	public static void showMessage(HttpServletRequest request) {
		if(request.getParameter("message")!= null) {
			String message = request.getParameter("message");
			String alert = "";
			String messageResponse = "";
			if(message.equals("insert_success")) {
				messageResponse = "Insert success";
				alert = "success";
			}
			else if(message.equals("update_success")) {
				messageResponse = "Update success";
				alert = "success";
			}
			else if(message.equals("delete_success")) {
				messageResponse = "Delete success";
				alert = "success";
			}
			else if(message.equals("error_system")) {
				messageResponse = "Error System";
				alert = "danger";
			}
			request.setAttribute("messageResponse", messageResponse);
			request.setAttribute("alert", alert);
		}
	}
}
