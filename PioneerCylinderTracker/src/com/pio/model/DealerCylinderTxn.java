package com.pio.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "DEALERCYLINDERTXN")
public class DealerCylinderTxn {

	@Id
	@Column(name="transactionId")
	private Long transactionId;
	
	@Column(name="dealerId")
	private String dealerId;
	
	@Column(name="cylinder_count")
	private Integer cylinderCount;
	
	@Column(name="cylinder_type")
	private String cylinderType;
	
	@Column(name="capacity")
	private Double capacity;
	
	@Column(name="quantity")
	private Double quantity;
	
	@Column(name ="bill_value")
	private Double billValue;
	
	@Column(name="dOTx")
	private Timestamp dOTx;
	
	@Column(name = "bill_generated",length = 1)
	private String billGenerated;

	public Timestamp getdOTx() {
		return dOTx;
	}

	public void setdOTx(Timestamp dOTx) {
		this.dOTx = dOTx;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getCylinderType() {
		return cylinderType;
	}

	public void setCylinderType(String cylinderType) {
		this.cylinderType = cylinderType;
	}

	public String getBillGenerated() {
		return billGenerated;
	}

	public void setBillGenerated(String billGenerated) {
		this.billGenerated = billGenerated;
	}

	public Integer getCylinderCount() {
		return cylinderCount;
	}

	public void setCylinderCount(Integer cylinderCount) {
		this.cylinderCount = cylinderCount;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public Double getBillValue() {
		return billValue;
	}

	public void setBillValue(Double billValue) {
		this.billValue = billValue;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	
}
