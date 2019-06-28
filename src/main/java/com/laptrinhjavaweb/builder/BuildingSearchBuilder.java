package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
	 private String name;
	 private String ward;
	 private String street;
	 
//	 private Integer numberOfBasement;
	 private String costRentFrom;
	 private String costRentTo;
	 private String areaRentForm;
	 private String areaRentTo;
	 private String[] buildingTypes = new String[] {};
	 private String district;
	public String getName() {
		return name;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	 
	 public String getCostRentFrom() {
		return costRentFrom;
	}
	public String getCostRentTo() {
		return costRentTo;
	}
	public String getAreaRentForm() {
		return areaRentForm;
	}
	public String getAreaRentTo() {
		return areaRentTo;
	}
	
	public String getDistrict() {
		return district;
	}
	//	public Integer getNumberOfBasement() {
//		return numberOfBasement;
//	}
	public BuildingSearchBuilder(Builder builder) {
		 this.name = builder.name;
		 this.ward = builder.ward;
		 this.street = builder.street;
//		 this.numberOfBasement = builder.numberOfBasement;
		 this.costRentFrom = builder.costRentFrom;
		 this. costRentTo = builder.costRentTo;
		 this.areaRentForm = builder.areaRentForm;
		 this.areaRentTo = builder.areaRentTo;
		 this.buildingTypes = builder.buildingTypes;
		 this.district = builder.district;
	 }
	 
	 public String[] getBuildingTypes() {
		return buildingTypes;
	}

	public static class Builder {
		 private String name;
		 private String ward;
		 private String street;
//		 private Integer numberOfBasement;
		 private String costRentFrom;
		 private String costRentTo;
		 private String areaRentForm;
		 private String areaRentTo;
		 private String[] buildingTypes = new String[] {};
		 private String district;
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		

		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}
		//		public Builder setNumberOfBasement(Integer numberOfBasement) {
//			this.numberOfBasement = numberOfBasement;
//			return this;
//		}
		public Builder setCostRentFrom(String costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}
		public Builder setCostRentTo(String costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}
		public Builder setAreaRentForm(String areaRentForm) {
			this.areaRentForm = areaRentForm;
			return this;
		}
		public Builder setAreaRentTo(String areaRentTo) {
			this.areaRentTo = areaRentTo;
			return this;
		}
		public Builder setBuildingTypes(String[] buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	 }
	 
}
