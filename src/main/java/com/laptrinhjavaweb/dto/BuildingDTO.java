package com.laptrinhjavaweb.dto;



public class BuildingDTO extends AbstractDTO{
	private String name;
	private String ward;
	private String street;
	private String structure;
	private Integer numberOfBasement;
	private Integer buildingArea;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public Integer getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(Integer buildingAre) {
		this.buildingArea = buildingAre;
	}
	@Override
	public String toString() {
		return "BuildingModel [name=" + name + ", ward=" + ward + ", street=" + street + ", structure=" + structure
				+ ", numberOfBasement=" + numberOfBasement + ", buildingArea=" + buildingArea + "]";
	}
	
	
	
}
