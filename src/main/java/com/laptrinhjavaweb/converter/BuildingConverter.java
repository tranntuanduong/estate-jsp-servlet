package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentArea;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.repository.IRentAreaRepository;

public class BuildingConverter {
	@Inject
	private IRentAreaRepository  rentAreaRepository;
	
	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		Map<String, Object> condition = new HashMap<>();
		condition.put("buildingid", buildingEntity.getId());
		List<RentArea> rentAreas = rentAreaRepository.findAll(condition, new PageRequest(null, null, null));
 		List<String> areas = new ArrayList<>();
 		for(RentArea item : rentAreas) {
 			areas.add(item.getValue());
 		}
 		if (areas.size() > 0) {
 	 		result.setRentArea(StringUtils.join(areas, ","));
 		}
		return result;
	}

	public BuildingEntity convertToEntity(BuildingDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);
		return result;
	}
}
