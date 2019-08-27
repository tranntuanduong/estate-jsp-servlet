package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
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
		StringBuilder loadStaffList = new StringBuilder("http://localhost:8087/api/user/assignment?role=STAFF");
		if(model.getAction().equals("LIST")) {
			url = "/views/admin/list.jsp";
			
			BuildingSearchBuilder builder = initBuildingBuilder(model);
			String findAllStr = "http://localhost:8087/api/building";
			StringBuilder findAllAPI = initBuildingParams(findAllStr,builder, model);
			
			String getTotalItemStr = "http://localhost:8087/api/building/total";
			StringBuilder getTotalItemAPI = initBuildingParams(getTotalItemStr,builder, model); 

			model.setTotalItems(buildingService.getTotalItems(getTotalItemAPI.toString().replaceAll("\\s+", "%20")));
			model.setTotalPage((int)Math.ceil((double)model.getTotalItems() / model.getMaxPageItem()));
			model.setListResult(buildingService.findAll(findAllAPI.toString().replaceAll("\\s+", "%20")));	
							
		} else if (model.getAction().equals("EDIT")) { 	
			if(model.getId() != null) {
				String findById = "http://localhost:8087/api/building/findAll?id="+model.getId();
				model = buildingService.findById(findById);				
				loadStaffList.append("&buildingId="+model.getId());
			}	
			url = "/views/admin/edit.jsp";			
		} 
		users.setListResult(userService.findAll(loadStaffList.toString()));
		request.setAttribute("districts", DataUtils.getDistricts());
		request.setAttribute("buildingtypes", DataUtils.getBuildingType());
		request.setAttribute("model", model);		
		request.setAttribute("users", users);
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
				.setBuildingArea(model.getBuildingArea()).setUser_id(model.getUser_id())
				.build();
		return builder;
	}

	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

}
