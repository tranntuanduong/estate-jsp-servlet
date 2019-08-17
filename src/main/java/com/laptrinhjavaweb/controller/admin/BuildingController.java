package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.pattern.LogEvent;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.DataUtils;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = "/admin-building")
public class BuildingController extends HttpServlet {

	private static final long serialVersionUID = 2008854797242140125L;

	@Inject
	private IBuildingService buildingService;
	
	@Inject
	private IUserService userService;

	final static Logger logger = Logger.getLogger(BuildingController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BuildingDTO model = FormUtil.toModel(BuildingDTO.class, request);
		UserDTO users = new UserDTO();
		String url ="";
		if(model.getAction().equals("LIST")) {
			url = "/views/admin/list.jsp";
			
			BuildingSearchBuilder builder = initBuildingBuilder(model);
			String findAllStr = "http://localhost:8087/api/building";
			StringBuilder findAllAPI = initBuildingParams(findAllStr,builder, model);
			String getTotalItemStr = "http://localhost:8087/api/building/total";
			StringBuilder getTotalItemAPI = initBuildingParams(getTotalItemStr,builder, model); 
			/*Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
			model.setTotalItems(buildingService.getTotalItems(builder));
			model.setTotalPage((int)Math.ceil((double)model.getTotalItems() / model.getMaxPageItem()));
			model.setListResult(buildingService.findAll(builder, pageble));*/
	
			model.setTotalItems(buildingService.getTotalItems(getTotalItemAPI.toString()));
			model.setTotalPage((int)Math.ceil((double)model.getTotalItems() / model.getMaxPageItem()));
			model.setListResult(buildingService.findAll(findAllAPI.toString()));
			/*
			//load user
			String getUserList = "http://localhost:8087/api/user";		
			users.setListResult(userService.findAll(getUserList));
			*/
			
		} else if (model.getAction().equals("EDIT")) { 
			if(model.getId() != null) {
				String findById = "http://localhost:8087/api/building/findAll?id="+model.getId();
				model = buildingService.findById(findById);
				
			}
			url = "/views/admin/edit.jsp";			
		} 
		request.setAttribute("districts", DataUtils.getDistricts());
		request.setAttribute("buildingtypes", DataUtils.getBuildingType());
		request.setAttribute("model", model);		
//		request.setAttribute("users", users);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private StringBuilder initBuildingParams(String sql, BuildingSearchBuilder builder, BuildingDTO model) {
		StringBuilder findAllAPI = new StringBuilder(sql);
		findAllAPI.append("?page="+model.getPage()+"&maxPageItem="+model.getMaxPageItem()+"");
		logger.info("begin add parameter to URL API ");
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if(field.get(builder) != null) {					
					if(field.getName().equals("buildingTypes")) {						
						if(((String[]) field.get(builder)).length > 0) {	
							String[] buildingTypes = (String[]) field.get(builder);
							findAllAPI.append("&buildingTypes="+buildingTypes[0]+"");
							 for(String buildingType : buildingTypes) {	
								 if(!buildingType.equals(builder.getBuildingTypes()[0])) {
									 findAllAPI.append(","+buildingType+"");
								 }
							 }
						}
					} else {
						findAllAPI.append("&"+field.getName()+"="+field.get(builder)+"");
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("ERROR add parameter to URL API: "+e.getMessage(), e);
			}
		}
		if(StringUtils.isNotBlank(model.getSortName())) {
			findAllAPI.append("&sortBy="+model.getSortBy()+"&sortName="+model.getSortName());
		}
		logger.info("URL API: "+findAllAPI.toString());
		return findAllAPI;
	}

	private BuildingSearchBuilder initBuildingBuilder(BuildingDTO model) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(model.getName()).setWard(model.getWard()).setDistrict(model.getDistrict())
				.setCostRentFrom(model.getCostRentFrom()).setCostRentTo(model.getCostRentTo())
				.setAreaRentForm(model.getAreaRentForm()).setAreaRentTo(model.getAreaRentTo())
				.setNumberOfBasement(model.getNumberOfBasement()).setRentArea(model.getRentArea())
				.setStreet(model.getStreet()).setBuildingTypes(model.getBuildingTypes())
				.setBuildingArea(model.getBuildingArea())
				.build();
		return builder;
	}

	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

}
