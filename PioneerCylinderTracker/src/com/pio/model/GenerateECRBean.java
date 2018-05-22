package com.pio.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ECR")
public class GenerateECRBean {

	@Id
	@Column(name="ecr_no")
	private Long ecrNo;
	
	@Column(name="ecr_date")
	private Timestamp ecrDate;
	
	@Column(name="lorry_no",length=15)
	private String lorryNo;
	
	@Column(name="cylinder_no",length=2000)
	private String cylinderNo;
	
	@Column(name ="dealer_id",length=6)
	private String dealerId;
	
	@Column(name ="outstanding",length=1)
	private String outstanding;

	public Long getEcrNo() {
		return ecrNo;
	}

	public void setEcrNo(Long ecrNo) {
		this.ecrNo = ecrNo;
	}

	public Timestamp getEcrDate() {
		return ecrDate;
	}

	public void setEcrDate(Timestamp ecrDate) {
		this.ecrDate = ecrDate;
	}

	public String getLorryNo() {
		return lorryNo;
	}

	public void setLorryNo(String lorryNo) {
		this.lorryNo = lorryNo;
	}

	public String getCylinderNo() {
		return cylinderNo;
	}

	public void setCylinderNo(String cylinderNo) {
		this.cylinderNo = cylinderNo;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(String outstanding) {
		this.outstanding = outstanding;
	}	
	
}
