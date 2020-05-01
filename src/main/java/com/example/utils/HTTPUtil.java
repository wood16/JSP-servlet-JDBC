package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HTTPUtil {
	private String value;
	
	public HTTPUtil(String value) {
		this.value = value;
	}
//	convert tu json sang model
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch(IOException e) {
			
		}
		return null;
	}
	
	public static HTTPUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}catch (IOException e) {
			System.out.println(e);
		}
		return new HTTPUtil(sb.toString());
	}
}
