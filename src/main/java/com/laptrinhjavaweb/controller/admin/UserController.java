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

import com.laptrinhjavaweb.builder.UserSearchBuilder;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;


@WebServlet(urlPatterns = "/employee-list")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = -7148271982384551120L;
	
	
	@Inject
	private IUserService userService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDTO model = FormUtil.toModel(UserDTO.class, request);
		String url = "";
		//load user
		if(model.getAction().equals("assignment")) {
			String buildingId = request.getParameter("buildingId");
			UserDTO users = new UserDTO();
			UserSearchBuilder builder = initUserBuilder(model);
			String findAllStr = "http://localhost:8087/api/user/assignment";
			StringBuilder findAllAPI = initUserParams(findAllStr, builder, model);
	
			users.setListResult(userService.findAll(findAllAPI.toString()));

			url = "/views/admin/employeeList.jsp";			
			request.setAttribute("action", model.getAction());
			request.setAttribute("users", users);
			request.setAttribute("buildingId", buildingId);
		} else if (model.getAction().equals("staffInCharge")) {
			String customerId = request.getParameter("customerId");
			UserDTO users = new UserDTO();
			UserSearchBuilder builder = initUserBuilder(model);
			String findAllStr = "http://localhost:8087/api/user/assignment";
			StringBuilder findAllAPI = initUserParams(findAllStr, builder, model);
			
			users.setListResult(userService.findAll(findAllAPI.toString()));
			
			url = "/views/admin/employeeList.jsp";		
			request.setAttribute("action", model.getAction());
			request.setAttribute("users", users);
			request.setAttribute("customerId", customerId);
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}


	private StringBuilder initUserParams(String findAllStr, UserSearchBuilder builder, UserDTO model) {
		StringBuilder findAllAPI = new StringBuilder(findAllStr);	
		//check page and maxpageitem
		if(model.getPage() == 0 && model.getMaxPageItem() == 0) {
			findAllAPI.append("?page=&maxPageItem=");	
		} else {
			findAllAPI.append("?page="+model.getPage()+"&maxPageItem="+model.getMaxPageItem()+"");
		}
		Field[] fields = UserSearchBuilder.class.getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			try {
				if(StringUtils.isNotBlank((String) field.get(builder))) {
					findAllAPI.append("&"+field.getName()+"="+field.get(builder));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return findAllAPI;
	}


	private UserSearchBuilder initUserBuilder(UserDTO model) {
		UserSearchBuilder builder = new UserSearchBuilder.Builder()
					.setFullName(model.getFullName()).setRole(model.getRole())
					.setBuildingId(model.getBuildingId()).setCustomerId(model.getCustomerId())
					
					.build();
		return builder;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
