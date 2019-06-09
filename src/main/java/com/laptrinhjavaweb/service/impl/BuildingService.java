package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repositoty.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	private IBuildingRepository buildingRepository;
	 
	public BuildingService() {
		buildingRepository = new BuildingRepository();
	}
	
	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		 BuildingConverter buildingConverter = new BuildingConverter();
		 BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		 buildingEntity.setCreatedBy("duong dep trai ahihi");
		 Long id = buildingRepository.insert(buildingEntity);
		return null;
	}

	@Override
	public void update(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingRepository.update(buildingEntity);
	}

	@Override
	public BuildingDTO findById(long id) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingDTO buildingDTO = new BuildingDTO();
		BuildingEntity buildingEntity = buildingRepository.findById(id);
		buildingDTO = buildingConverter.convertToDTO(buildingEntity);
		return buildingDTO;
	}

	@Override
	public void delete(long id) {
		buildingRepository.delete(id);	
	}

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> properties,PageRequest pageRequest,Object...where) {
		List<BuildingDTO> resultDTO = new ArrayList<BuildingDTO>();
		List<BuildingEntity> resultEntity = null;
		BuildingConverter buildingConverter = new BuildingConverter();

		resultEntity = buildingRepository.findAll(properties, pageRequest);
		if(!resultEntity.isEmpty()) {
			for(BuildingEntity entity : resultEntity) {
				resultDTO.add(buildingConverter.convertToDTO(entity));
			}
			return resultDTO;
		}
		return null;
	}

}
