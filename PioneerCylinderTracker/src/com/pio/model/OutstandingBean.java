package com.pio.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "out_standing")
public class OutstandingBean {
	
	@Id
	@Column(name = "outstanding_id", length = 6)
	private Long outId;
	
	@Column(name = "out_date")
	private Timestamp outDate;
	
	@Column(name = "dealer_id", length = 6)
	private String dealerId;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "cylinder_taken")
	private Integer cylinderTaken;
	
	@Column(name = "cylinder_return")
	private Integer cylinderReturn;
	
	@Column(name ="bill_nos")
	private String billNos;

	public Long getOutId() {
		return outId;
	}

	public void setOutId(Long outId) {
		this.outId = outId;
	}

	public String getBillNos() {
		return billNos;
	}

	public void setBillNos(String billNos) {
		this.billNos = billNos;
	}

	

	public Timestamp getOutDate() {
		return outDate;
	}

	public void setOutDate(Timestamp outDate) {
		this.outDate = outDate;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getCylinderTaken() {
		return cylinderTaken;
	}

	public void setCylinderTaken(Integer cylinderTaken) {
		this.cylinderTaken = cylinderTaken;
	}

	public Integer getCylinderReturn() {
		return cylinderReturn;
	}

	public void setCylinderReturn(Integer cylinderReturn) {
		this.cylinderReturn = cylinderReturn;
	}
		
}
