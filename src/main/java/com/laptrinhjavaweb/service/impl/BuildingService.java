package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.IBuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentArea;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	
	@Inject
	private  IBuildingRepository buildingRepository;
	@Inject
	private  IBuildingConverter buildingConverter;

	@Inject
	private IRentAreaRepository rentAreaRespository;
/*	public BuildingService() {
		if(buildingRepository == null) {
			buildingRepository = new BuildingRepository();
		}
		if(buildingConverter == null) {
			buildingConverter = new BuildingConverter();
		}
	}
*/
	
	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {

		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreatedBy("duong dep trai ahihi");
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

	
}
