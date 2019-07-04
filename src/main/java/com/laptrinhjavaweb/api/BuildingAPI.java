package com.laptrinhjavaweb.api;


import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.HttpUtil;


@WebServlet(urlPatterns = "/api-admin-building")
public class BuildingAPI extends HttpServlet{
		@SuppressWarnings("unused")
		
		@Inject
		private IBuildingService buildingService;
		
		public BuildingAPI() {
			buildingService = new BuildingService();
		}
 		
		private static final long serialVersionUID = -7517182978030195404L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			BuildingDTO buildingDTO = new BuildingDTO();
			//logic
			String id = request.getParameter("id");
			buildingDTO = buildingService.findById(Long.parseLong(id));
			mapper.writeValue(response.getOutputStream(), buildingDTO);
			System.out.println();
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			BuildingDTO buildingDTO = new BuildingDTO();
			 buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
			//logic
			buildingDTO = buildingService.save(buildingDTO);
			mapper.writeValue(response.getOutputStream(), buildingDTO);
		}
		
		protected void doPut(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			BuildingDTO updateBuilding = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
			//logic
			buildingService.update(updateBuilding, updateBuilding.getId());
			mapper.writeValue(response.getOutputStream(), "{}");
		}
		
		protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
			//logic
			buildingService.delete(buildingDTO.getIds());
			mapper.writeValue(response.getOutputStream(), "{}");		
		}

	
}
