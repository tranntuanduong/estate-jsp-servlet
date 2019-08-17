package com.laptrinhjavaweb.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.controller.admin.BuildingController;
import com.laptrinhjavaweb.converter.IBuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentArea;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.HttpClientUtils;

public class BuildingService implements IBuildingService {
	
	@Inject
	private  IBuildingRepository buildingRepository;
	@Inject
	private  IBuildingConverter buildingConverter;
	
	@Inject
	private IRentAreaRepository rentAreaRespository;
	final static Logger logger = Logger.getLogger(BuildingController.class);
	
	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {

		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreatedBy("duong d`ep trai ahihi");
		buildingEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));	
		if(buildingDTO.getBuildingTypes().length > 0) {
			buildingEntity.setType(StringUtils.join(buildingDTO.getBuildingTypes(), ","));
		}
		Long id = buildingRepository.insert(buildingEntity);
		//save rentarea
		if(StringUtils.isNotBlank(buildingDTO.getRentArea())) {
			for(String item : buildingDTO.getRentArea().split(",")) {
				RentArea rentArea = new RentArea();
				rentArea.setValue(item);
				rentArea.setBuildingId(id);
				rentAreaRespository.insert(rentArea);
			}	
		}
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
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

	
	
	@Override
	public void update(BuildingDTO updateBuilding, Long id) {
		BuildingEntity oldBuilding = buildingRepository.findById(id);
		BuildingEntity newBuilding = buildingConverter.convertToEntity(updateBuilding);
		newBuilding.setCreatedBy(oldBuilding.getCreatedBy());
		newBuilding.setCreatedDate(oldBuilding.getCreatedDate()); 
		updateRentArea(updateBuilding.getRentArea() ,id);
		newBuilding.setType(StringUtils.join(updateBuilding.getBuildingTypes(),","));
		buildingRepository.update(newBuilding);
	}

	private void updateRentArea(String rentAreaStr, Long id) {
		//delete
		rentAreaRespository.deleteByBuildingId(id);		
		//insert
		for(String item : rentAreaStr.split(",")) {
			RentArea rentArea = new RentArea();
			rentArea.setValue(item);
			rentArea.setBuildingId(id);
			rentAreaRespository.insert(rentArea);
		}
	}

	@Override
	public BuildingDTO findById(long id) {
 		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}

	@Override
	public void delete(Long[] ids) {
		for(Long item : ids) {
			rentAreaRespository.deleteByBuildingId(item);
			buildingRepository.delete(item);
		}		
	}
	
	@Override
	public int getTotalItems(BuildingSearchBuilder builder) {
		return buildingRepository.countByProperty(builder);
	}
	
	@Override
	public List<BuildingDTO> findAll(String url) {
		String result = HttpClientUtils.httpGet(url);
		//json -> Array
		//Array -> list
		try {
			return Arrays.asList(new ObjectMapper().readValue(result, BuildingDTO[].class));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}

	@Override
	public int getTotalItems(String url) {
		String result = HttpClientUtils.httpGet(url);
		try {
			BuildingDTO buildingDTO = new ObjectMapper().readValue(result, BuildingDTO.class);
			return buildingDTO.getTotalItems();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public BuildingDTO findById(String url) {
		String result = HttpClientUtils.httpGet(url);
		try {
			BuildingDTO buildingDTO = new ObjectMapper().readValue(result, BuildingDTO.class);
			return buildingDTO;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	
}
