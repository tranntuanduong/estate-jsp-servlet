package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import com.laptrinhjavaweb.dto.BuildingDTO;

public class BuildingMapper implements RowMapper<BuildingDTO>{

	@Override
	public BuildingDTO mappRow(ResultSet resultSet) {
		
		try {
			BuildingDTO buildingModel = new BuildingDTO();
			buildingModel.setName(resultSet.getString("name"));
			buildingModel.setNumberOfBasement(resultSet.getInt("numberofbasement"));
			buildingModel.setBuildingArea(resultSet.getInt("buildingarea"));
			buildingModel.setWard(resultSet.getString("ward"));
			buildingModel.setStreet(resultSet.getString("street"));
			buildingModel.setStructure(resultSet.getString("structure"));
			//nho phai tra ve T
			return buildingModel;
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return null;
	}
	
}
