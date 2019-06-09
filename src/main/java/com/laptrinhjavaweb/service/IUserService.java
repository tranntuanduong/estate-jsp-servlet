package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

public interface IUserService {
	UserEntity save(UserDTO newUser);
}
