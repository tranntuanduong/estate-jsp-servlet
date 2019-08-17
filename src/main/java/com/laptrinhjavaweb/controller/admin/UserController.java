package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;


@WebServlet(urlPatterns = "/employee-list")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = -7148271982384551120L;
	
	
	@Inject
	private IUserService userService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		//load user
		UserDTO users = new UserDTO();
		String findAll = "http://localhost:8087/api/user/findAll";
		users.setListResult(userService.findAll(findAll));

		String url = "/views/admin/employeeList.jsp";			
		request.setAttribute("users", users);
		request.setAttribute("buildingId", idStr);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
