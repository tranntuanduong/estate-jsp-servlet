package com.laptrinhjavaweb.enums;

public enum BuildingTypeEnum {
	TANG_TRET("Tang tret"),
	NGUYEN_CAN("Nguyên căn"),
	NOI_THAT("Nội thất");
	
	private String value;
	
	BuildingTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
