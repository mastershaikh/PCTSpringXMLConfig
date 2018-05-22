package com.pio.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cylinder")
public class Cylinder {
	
	@Id
	@Column(name = "cylinder_id", length = 6)
	private Long cylinderId;
	
	@Column(name = "capacity")
	private Double capacity;
	
	@Column(name = "last_modified_date")
	private Timestamp lastModifiedDate;
	
	@Column(name = "cylinder_type")
	private String cylinderType;
	
	@Column(name = "damage",length = 1)
	private String damage;
	
	@Column(name = "usage_status", length = 6)
	private String usageStatus;
	
	@Column(name = "bill_generated")
	private String billGenerated;
	
	@Column(name = "remark")
	private String remark;
		
	public Long getCylinderId() {
		return cylinderId;
	}
	public void setCylinderId(Long cylinderId) {
		this.cylinderId = cylinderId;
	}
	public Double getCapacity() {
		return capacity;
	}
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public String getCylinderType() {
		return cylinderType;
	}
	public void setCylinderType(String cylinderType) {
		this.cylinderType = cylinderType;
	}
	public String getUsageStatus() {
		return usageStatus;
	}
	public void setUsageStatus(String usageStatus) {
		this.usageStatus = usageStatus;
	}
	
	public String getDamage() {
		return damage;
	}
	public void setDamage(String damage) {
		this.damage = damage;
	}
	public String getBillGenerated() {
		return billGenerated;
	}
	public void setBillGenerated(String billGenerated) {
		this.billGenerated = billGenerated;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
