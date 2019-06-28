package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.DataUtils;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = "/admin-building")
public class BuildingController extends HttpServlet {

	private static final long serialVersionUID = 5243934502037847728L;
	
	@Inject
	private IBuildingService buildingService;
	
	/*
	public BuildingController() {
		if(buildingService == null) {		
			buildingService = new BuildingService();
		}
	}*/
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BuildingDTO model = FormUtil.toModel(BuildingDTO.class, request);
		
		String url ="";
		if(model.getAction().equals("LIST")) {
			url = "/views/admin/list.jsp";
			BuildingSearchBuilder builder = initBuildingBuilder(model);
			Pageble pageble = new PageRequest(null, null, null);
			model.setListResult(buildingService.findAll(builder, pageble));
			
		} else if (model.getAction().equals("EDIT")) { 
			url = "/views/admin/edit.jsp";
			
		}
		request.setAttribute("districts", DataUtils.getDistricts());
		request.setAttribute("buildingtypes", DataUtils.getBuildingType());
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private BuildingSearchBuilder initBuildingBuilder(BuildingDTO model) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(model.getName()).setWard(model.getWard()).setDistrict(model.getDistrict())
				.setCostRentFrom(model.getCostRentFrom()).setCostRentTo(model.getCostRentTo())
				.setAreaRentForm(model.getAreaRentForm()).setAreaRentTo(model.getAreaRentTo())
//				.setNumberOfBasement(model.getNumberOfBasement())
				.setStreet(model.getStreet()).setBuildingTypes(model.getBuildingTypes())
				.build();
		return builder;
	}

	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

}
