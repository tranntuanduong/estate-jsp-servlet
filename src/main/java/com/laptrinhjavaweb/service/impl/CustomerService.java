package com.laptrinhjavaweb.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.utils.HttpClientUtils;

public class CustomerService implements ICustomerService {

	@Override
	public List<CustomerDTO> findAll(String url) {
		String result = HttpClientUtils.httpGet(url);
		//json -> Array
		//Array -> list
		try {
			return Arrays.asList(new ObjectMapper().readValue(result, CustomerDTO[].class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public int getTotalItems(String url) {
		String result = HttpClientUtils.httpGet(url);
		try {
			CustomerDTO buildingDTO = new ObjectMapper().readValue(result, CustomerDTO.class);
			return buildingDTO.getTotalItems();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
