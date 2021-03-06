package com.laptrinhjavaweb.dto;

import java.util.List;

public class UserDTO extends AbstractDTO<UserDTO> {
	private String buildingId;
	private String customerId;
	private String username;
	private String fullName;
	private String password;
	private String role;
	private List<BuildingDTO> buildings;
	private List<CustomerDTO> customers;
	private String buildingChecked;
	private String customerChecked;
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerChecked() {
		return customerChecked;
	}
	public void setCustomerChecked(String customerChecked) {
		this.customerChecked = customerChecked;
	}
	public List<CustomerDTO> getCustomers() {
		return customers;
	}
	public void setCustomers(List<CustomerDTO> customers) {
		this.customers = customers;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	
	public String getBuildingChecked() {
		return buildingChecked;
	}
	public void setBuildingChecked(String buildingChecked) {
		this.buildingChecked = buildingChecked;
	}
	public List<BuildingDTO> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<BuildingDTO> buildings) {
		this.buildings = buildings;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
