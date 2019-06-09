package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	String value;
	public HttpUtil (String value) {
		this.value = value;
	}
	
	public <T> T toModel (Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpUtil of (BufferedReader reader) {
		String line;
		StringBuilder sb = new StringBuilder();
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return new HttpUtil(sb.toString());
	}
}
