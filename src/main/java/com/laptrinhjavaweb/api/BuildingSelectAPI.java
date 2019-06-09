package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = "/api-admin-buildingselect")
public class BuildingSelectAPI extends HttpServlet{
	

	private static final long serialVersionUID = -7386891962729066342L;
	private IBuildingService buildingService;
	public BuildingSelectAPI() {
		buildingService = new BuildingService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		//logic
		Map<String, Object> properties = new HashMap<>();
		properties.put("name", "kangnam");
		properties.put("numberofbasement",10);
		Sorter sorter = new Sorter("id", "desc");
		PageRequest pageRequest = new PageRequest(1, 3, sorter);
		List<BuildingDTO> buildingDTOList = buildingService.findAll(properties, pageRequest);
		mapper.writeValue(response.getOutputStream(), buildingDTOList);
		System.out.println();
	}
}
