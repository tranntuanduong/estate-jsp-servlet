package com.laptrinhjavaweb.dto;

import java.util.List;

public class CustomerDTO extends AbstractDTO<CustomerDTO>  {
	private String name;
	private String phoneNumber;
	private String email;
	private String need;
	private String dataEntry;
	private String status;
	private List<UserDTO> staffList;
	private String userId;
	
	//nhan vien dang quan li customer
	private String staffInCharge;

	public String getStaffInCharge() {
		return staffInCharge;
	}

	public void setStaffInCharge(String staffInCharge) {
		this.staffInCharge = staffInCharge;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userid) {
		this.userId = userid;
	}

	public String getName() {
		return name;
	}
	
	public List<UserDTO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<UserDTO> staffList) {
		this.staffList = staffList;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	public String getDataEntry() {
		return dataEntry;
	}
	public void setDataEntry(String dataEntry) {
		this.dataEntry = dataEntry;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
