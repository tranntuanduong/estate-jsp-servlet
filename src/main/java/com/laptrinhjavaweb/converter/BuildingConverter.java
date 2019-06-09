package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public class BuildingConverter {
	public BuildingDTO convertToDTO(BuildingEntity entity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
		return result;
	}

	public BuildingEntity convertToEntity(BuildingDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);
		return result;
	}
}
