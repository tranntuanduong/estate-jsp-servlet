package com.laptrinhjavaweb.repositoty.impl;

import com.laptrinhjavaweb.entity.RentArea;
import com.laptrinhjavaweb.repository.IRentAreaRepository;

public class RentAreaRespository extends AbstractJDBC<RentArea> implements IRentAreaRepository {

	@Override
	public void deleteByBuildingId(Long id) {
		String where = " WHERE buildingId = "+id+"";
		this.deleteByProperty(where);
	}



}
