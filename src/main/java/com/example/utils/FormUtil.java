package com.example.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
//	BeanUtils.populate()
//	chuyen cach parameter thanh dang key value giong nhu kieu Json
//	sau do mapping vao cac truong trong object
	
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
		T object = null;
		try {
			object = tClass.newInstance();
			BeanUtils.populate(object, request.getParameterMap());
		} catch(IllegalAccessException | InvocationTargetException | InstantiationException e) {
			System.out.println(e);
		}
		return object;
	}
}
