package com.laptrinhjavaweb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	
	@Inject
	private  IBuildingRepository buildingRepository;
	
	@Inject
	private  BuildingConverter buildingConverter;

	/*public BuildingService() {
		if(buildingRepository == null) {
			buildingRepository = new BuildingRepository();
		}
		if(buildingConverter == null) {
			buildingConverter = new BuildingConverter();
		}
	}*/
	
	
	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {

		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreatedBy("duong dep trai ahihi");
		Long id = buildingRepository.insert(buildingEntity);
		return null;
	}

	@Override
	public void update(BuildingDTO buildingDTO) {

		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingRepository.update(buildingEntity);
	}

	@Override
	public BuildingDTO findById(long id) {
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
	public List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder, pageble);
		// java 7
//		for(BuildingEntity item : buildingEntities) {
//			BuildingDTO buildingDTO = buildingConverter.convertToDTO(item);
//			results.add(buildingDTO);
//		}
		// java 8
		List<BuildingDTO> results = buildingEntities.stream().map(item -> buildingConverter.convertToDTO(item))
				.collect(Collectors.toList());
		return results;
	}

	

}
