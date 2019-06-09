package com.laptrinhjavaweb.dto;

import java.sql.Timestamp;

public class AbstractDTO {
	
	private Long id;
	private Timestamp createdDate; 
	private Timestamp modifiedDate;
	private String createdBy; 
	private String modifiedBy;
	private int maxPageItem = 3;
	private int page = 1;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public int getMaxPageItem() {
		return maxPageItem;
	}
	public void setMaxPageItem(int maxPageItem) {
		this.maxPageItem = maxPageItem;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}	
	
}
