package com.laptrinhjavaweb.dto;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.entity.RoleEntity;

public class UserDTO extends AbstractDTO<UserDTO> {
	private String username;
	private String fullName;
	private String password;
	private RoleEntity role;
	private List<BuildingDTO> buildings = new ArrayList<BuildingDTO>();
	
	
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
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
