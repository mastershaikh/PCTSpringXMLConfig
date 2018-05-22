package com.pio.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cylinder_test")
public class CylinderTest {
	
	@Id
	@Column(name = "cylinder_id", length = 6)
	private Long cylinderId;
	
	@Column(name = "capacity")
	private String capacity;
	
	@Column(name = "test_date")
	private Timestamp testDate;
	
	@Column(name = "cylinder_type")
	private String cylinderType;
	
	@Column(name = "damage",length = 1)
	private String damage;
	
	@Column(name = "prev_test_dates")
	private String prevTestDates;

	public Long getCylinderId() {
		return cylinderId;
	}
	public void setCylinderId(Long cylinderId) {
		this.cylinderId = cylinderId;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
		
	public String getCylinderType() {
		return cylinderType;
	}
	public void setCylinderType(String cylinderType) {
		this.cylinderType = cylinderType;
	}
	
	public String getDamage() {
		return damage;
	}
	public void setDamage(String damage) {
		this.damage = damage;
	}
	public Timestamp getTestDate() {
		return testDate;
	}
	public void setTestDate(Timestamp testDate) {
		this.testDate = testDate;
	}
	public String getPrevTestDates() {
		return prevTestDates;
	}
	public void setPrevTestDates(String prevTestDates) {
		this.prevTestDates = prevTestDates;
	}
	
	
}
