package com.laptrinhjavaweb.dto;

import java.util.Date;

public class TransactionDTO extends AbstractDTO<TransactionDTO>{
	private Date createDate;
	private String customerCare;
	private String guide;	
	private String role;	
	private Long customerId;
	
	
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCustomerCare() {
		return customerCare;
	}
	public void setCustomerCare(String customerCare) {
		this.customerCare = customerCare;
	}
	public String getGuide() {
		return guide;
	}
	public void setGuide(String guide) {
		this.guide = guide;
	}
	
	
}
