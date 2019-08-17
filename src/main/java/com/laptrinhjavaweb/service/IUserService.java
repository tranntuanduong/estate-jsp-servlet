package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.UserDTO;

public interface IUserService {
	List<UserDTO> findAll(String url);

}
