package com.laptrinhjavaweb.repositoty.impl;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends AbstractJDBC<BuildingEntity> implements IBuildingRepository{

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageble pageble) {
		Map<String, Object> properties = buildMapSearch(builder);
		StringBuilder whereClase = new StringBuilder();
		if(StringUtils.isNotBlank(builder.getCostRentFrom())) {
			whereClase.append(" AND costrent >= "+builder.getCostRentFrom());
		}
		if(StringUtils.isNotBlank(builder.getCostRentTo())) {
			whereClase.append(" AND costrent <= "+builder.getCostRentTo());
		}
		if(StringUtils.isNotBlank(builder.getAreaRentForm()) || StringUtils.isNotBlank(builder.getAreaRentTo())) {
			whereClase.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (ra.buildingid = A.id");
			if(StringUtils.isNotBlank(builder.getAreaRentForm())) {
				whereClase.append(" AND ra.value >='"+builder.getAreaRentForm()+"'");
			}
			if(StringUtils.isNotBlank(builder.getAreaRentTo())) {
				whereClase.append(" AND ra.value <='"+builder.getAreaRentTo()+"'");
			}
			whereClase.append("))");
		}
		if(builder.getBuildingTypes().length > 0) {
			 whereClase.append(" AND (A.type LIKE '%"+builder.getBuildingTypes()[0]+"%'");
			 for(String type : builder.getBuildingTypes()) {	
				 if(!type.equals(builder.getBuildingTypes()[0])) {
					 whereClase.append(" OR A.type LIKE '%"+type+"%'");
				 }
			 }
			 //java8
//			 Arrays.stream(builder.getBuildingTypes()).filter(item -> !item.equals(builder.getBuildingTypes()[0]))
//			 .forEach(item ->  whereClase.append(" OR A.type LIKE '%"+item+"%'"));
			 whereClase.append(" )");
		}
		return findAll(properties, pageble, whereClase.toString());
	}

	private Map<String, Object> buildMapSearch(BuildingSearchBuilder builder) {
		Map<String, Object> result = new HashMap<>();
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
						&& !field.getName().startsWith("areaRent")) {
					field.setAccessible(true);
					if (field.get(builder) != null) {
						
						result.put(field.getName().toLowerCase(), field.get(builder));
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
