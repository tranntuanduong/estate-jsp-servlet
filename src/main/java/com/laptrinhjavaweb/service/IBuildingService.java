package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO buildingDTO);
	void update(BuildingDTO buildingDTO);
	void delete(long id);
	BuildingDTO  findById(long id);
	List<BuildingDTO> findAll(Map<String, Object> properties,PageRequest pageRequest,Object...where);
}
