package com.pio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="ECRTransaction")
public class ECRTransactionBean {

	@Id
	@Column(name ="TX_ID")
	private String txId;
	
	@Column(name ="cylinder_id")
	private Long cylinderId;
	
	@Column(name ="lorry_no")
	private String lorryNo;
	
	@Column(name ="dealer_id")
	private String dealerId;
	
	@Column(name ="ecr_status")
	private String ecrStatus;

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public Long getCylinderId() {
		return cylinderId;
	}

	public void setCylinderId(Long cylinderId) {
		this.cylinderId = cylinderId;
	}

	public String getLorryNo() {
		return lorryNo;
	}

	public void setLorryNo(String lorryNo) {
		this.lorryNo = lorryNo;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getEcrStatus() {
		return ecrStatus;
	}

	public void setEcrStatus(String ecrStatus) {
		this.ecrStatus = ecrStatus;
	}
	
	
	
}
