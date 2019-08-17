package com.laptrinhjavaweb.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.controller.admin.BuildingController;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.HttpClientUtils;

public class UserService implements IUserService {
	final static Logger logger = Logger.getLogger(BuildingController.class);
	@Override
	public List<UserDTO> findAll(String url) {
		String result = HttpClientUtils.httpGet(url);
		//json -> Array
		//Array -> list
		try {
			return Arrays.asList(new ObjectMapper().readValue(result, UserDTO[].class));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}
	
}
