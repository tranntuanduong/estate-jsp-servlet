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

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;


@WebServlet(urlPatterns ="/admin-customer")
public class CustomerController extends HttpServlet {

	private static final long serialVersionUID = -3771625784348905485L;
	
	@Inject
	private ICustomerService customerService;
	@Inject
	private IUserService userService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDTO model = FormUtil.toModel(CustomerDTO.class, request);
		String url ="";
		if(model.getAction().equals("LIST")) {

			CustomerSearchBuilder builder = initCustomerSearchBuilder(model);
			String findAllStr =  "http://localhost:8087/api/customer";
			StringBuilder findAllAPI = initCustomerParams(findAllStr, builder, model);
			//String getTotalItemStr = "http://localhost:8087/api/customer/total";
			//StringBuilder getTotalItemAPI = initCustomerParams(findAllStr, builder, model);
			//model.setTotalItems(customerService.getTotalItems(getTotalItemAPI.toString().replaceAll("\\s+", "%20")));
			//model.setTotalPage((int)Math.ceil((double)model.getTotalItems() / model.getMaxPageItem()));
			
			//load staff list
			String loadStaffList = "http://localhost:8087/api/user/assignment?role=STAFF";
			List<UserDTO> staffList = userService.findAll(loadStaffList);
			model.setStaffList(staffList);
			
			model.setListResult(customerService.findAll(findAllAPI.toString().replaceAll("\\s+", "%20")));
			url = "/views/admin/customer/customer.jsp";
		} else if (model.getAction().equals("EDIT")) {
			url = "/views/admin/customer/edit.jsp";
		}
		//tam thoi de the nay da !!!!!
		model.setTotalItems(5);
		model.setTotalPage(2);
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private StringBuilder initCustomerParams(String sql, CustomerSearchBuilder builder, CustomerDTO model) {
		StringBuilder findAllAPI = new StringBuilder(sql);
		findAllAPI.append("?page="+model.getPage()+"&maxPageItem="+model.getMaxPageItem()+"");
		Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if(StringUtils.isNotBlank((String) field.get(builder))) {
					findAllAPI.append("&"+field.getName()+"="+field.get(builder)+"");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotBlank(model.getSortName())) {
			findAllAPI.append("&sortBy="+model.getSortBy()+"&sortName="+model.getSortName());
		}
		return findAllAPI;
	}

	private CustomerSearchBuilder initCustomerSearchBuilder(CustomerDTO model) {
		CustomerSearchBuilder builder = new CustomerSearchBuilder.Builder()
					.setName(model.getName()).setEmail(model.getEmail())
					.setPhoneNumber(model.getPhoneNumber()).setUserId(model.getUserId())
					.build();
		return builder;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
