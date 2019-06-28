package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageble;

public interface IBuildingService {	
	void update(BuildingDTO buildingDTO);
	void delete(long id);
	BuildingDTO  findById(long id);
	
	BuildingDTO save(BuildingDTO buildingDTO);
	List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble); 
	
}
