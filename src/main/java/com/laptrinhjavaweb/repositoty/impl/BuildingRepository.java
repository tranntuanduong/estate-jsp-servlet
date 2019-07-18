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
		StringBuilder whereClause = createWhereClause(builder);
	
		return findAll(properties, pageble, whereClause.toString());
	}
	
	@Override
	public int countByProperty(BuildingSearchBuilder builder) {
		Map<String, Object> properties = buildMapSearch(builder);
		StringBuilder whereClause = createWhereClause(builder);
		return countByProperty(properties, whereClause.toString());
	}

	private Map<String, Object> buildMapSearch(BuildingSearchBuilder builder) {
		Map<String, Object> result = new HashMap<>();
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
						&& !field.getName().startsWith("areaRent")) {
					field.setAccessible(true);
					if (StringUtils.isNotBlank((String) field.get(builder))) {					
						if(field.getName().equals("numberOfBasement") || field.getName().equals("buildingArea")) {
							result.put(field.getName().toLowerCase(), Integer.parseInt((String)field.get(builder)));
						} else {
							result.put(field.getName().toLowerCase(), field.get(builder));
						}
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	private StringBuilder createWhereClause(BuildingSearchBuilder builder) {
		StringBuilder whereClause = new StringBuilder();
		if(StringUtils.isNotBlank(builder.getCostRentFrom())) {
			whereClause.append(" AND costrent >= "+builder.getCostRentFrom());
		}
		if(StringUtils.isNotBlank(builder.getCostRentTo())) {
			whereClause.append(" AND costrent <= "+builder.getCostRentTo());
		}
		if(StringUtils.isNotBlank(builder.getAreaRentForm()) || StringUtils.isNotBlank(builder.getAreaRentTo())) {
			whereClause.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (ra.buildingid = A.id");
			if(StringUtils.isNotBlank(builder.getAreaRentForm())) {
				whereClause.append(" AND ra.value >='"+builder.getAreaRentForm()+"'");
			}
			if(StringUtils.isNotBlank(builder.getAreaRentTo())) {
				whereClause.append(" AND ra.value <='"+builder.getAreaRentTo()+"'");
			}
			whereClause.append("))");
		}
		if(builder.getBuildingTypes().length > 0) {
			whereClause.append(" AND (A.type LIKE '%"+builder.getBuildingTypes()[0]+"%'");
			 for(String type : builder.getBuildingTypes()) {	
				 if(!type.equals(builder.getBuildingTypes()[0])) {
					 whereClause.append(" OR A.type LIKE '%"+type+"%'");
				 }
			 }
			 //java8
//			 Arrays.stream(builder.getBuildingTypes()).filter(item -> !item.equals(builder.getBuildingTypes()[0]))
//			 .forEach(item ->  whereClase.append(" OR A.type LIKE '%"+item+"%'"));
			 whereClause.append(" )");
		}
		return whereClause;
	}
	
	
}
