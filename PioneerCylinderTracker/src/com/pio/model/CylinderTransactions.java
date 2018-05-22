package com.pio.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "CylinderTransactions")
public class CylinderTransactions {

	@Id
	@Column(name="transactionId")
	private String transactionId;
	
	@Column(name="dealerId")
	private String dealerId;
	
	@Column(name="cylinderId")
	private Long cylinderId;
	
	@Column(name="cylinderType")
	private String cylinderType;

	@Column(name="live")
	private String liveTx;
	
	@Column(name="dOTx")
	private Timestamp dOTx;
	
	@Column(name="DOR")
	private Timestamp DOR;
	
	@Column(name = "bill_generated",length = 1)
	private String billGenerated;

	public Timestamp getdOTx() {
		return dOTx;
	}

	public void setdOTx(Timestamp dOTx) {
		this.dOTx = dOTx;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
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

	public Long getCylinderId() {
		return cylinderId;
	}

	public void setCylinderId(Long cylinderId) {
		this.cylinderId = cylinderId;
	}

	public String getLiveTx() {
		return liveTx;
	}

	public void setLiveTx(String liveTx) {
		this.liveTx = liveTx;
	}

	public Timestamp getDOR() {
		return DOR;
	}

	public void setDOR(Timestamp dOR) {
		DOR = dOR;
	}

	public String getBillGenerated() {
		return billGenerated;
	}

	public void setBillGenerated(String billGenerated) {
		this.billGenerated = billGenerated;
	}



}
