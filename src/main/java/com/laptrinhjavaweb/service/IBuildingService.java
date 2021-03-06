package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageble;

public interface IBuildingService {	
	void update(BuildingDTO newBuilding, Long id);
	void delete(Long[] ids);
	BuildingDTO  findById(long id);
	BuildingDTO save(BuildingDTO buildingDTO);
	
	//
	List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble); 
	List<BuildingDTO> findAll(String url); 
	int getTotalItems(BuildingSearchBuilder builder);
	int getTotalItems(String url);
	
	BuildingDTO findById(String url);
}
