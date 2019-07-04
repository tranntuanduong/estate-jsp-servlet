package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuildingConverter {
	BuildingDTO convertToDTO(BuildingEntity buildingEntity);
	BuildingEntity convertToEntity(BuildingDTO buildingDTO);
}
