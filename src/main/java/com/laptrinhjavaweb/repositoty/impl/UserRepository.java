package com.laptrinhjavaweb.repositoty.impl;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;

public class UserRepository extends AbstractJDBC<UserEntity> implements IUserRepository{

	@Override
	public Long insert(UserEntity userEntity) {
		return null;
	}
	
}
