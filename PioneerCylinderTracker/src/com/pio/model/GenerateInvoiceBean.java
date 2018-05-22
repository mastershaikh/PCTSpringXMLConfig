package com.pio.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Invoice")
public class GenerateInvoiceBean {

	@Id
	@Column(name="bill_no")
	private Long billNo;
	
	@Column(name="dealerId")
	private String dealerId;

	@Column(name="vehicle_no",length=15)
	private String vehicleNo;
	
	@Column(name="cylinder_type",length= 50)
	private String cylinderType;
	
	@Column(name="total_cylinders")
	private Integer totalCylinders;
	
	@Column(name ="quantity")
	private Double quantity;
	
	@Column(name ="sale_value")
	private Double saleValue;
	
	@Column(name ="CGST")
	private Double CGST;
	
	@Column(name = "SGST")
	private Double SGST;
	
	@Column(name="bill_value")
	private Double billValue;
	
	@Column(name="rate")
	private Double rate;
	
	@Column(name="bill_date")
	private Timestamp billDate;

	@Column(name="cylinder_no",length=2000)
	private String cylinderNo;
	
	@Column(name="outstanding",length = 1)
	private String outstanding;
	
	@Column(name ="CorD",length = 1)
	private String CorD;
	
	public Long getBillNo() {
		return billNo;
	}

	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	

	public String getCylinderType() {
		return cylinderType;
	}

	public void setCylinderType(String cylinderType) {
		this.cylinderType = cylinderType;
	}


	public Integer getTotalCylinders() {
		return totalCylinders;
	}


	public void setTotalCylinders(Integer totalCylinders) {
		this.totalCylinders = totalCylinders;
	}

	public Double getBillValue() {
		return billValue;
	}

	public void setBillValue(Double billValue) {
		this.billValue = billValue;
	}

	public Timestamp getBillDate() {
		return billDate;
	}

	public void setBillDate(Timestamp billDate) {
		this.billDate = billDate;
	}

	public String getCylinderNo() {
		return cylinderNo;
	}

	public void setCylinderNo(String cylinderNo) {
		this.cylinderNo = cylinderNo;
	}

	public String getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(String outstanding) {
		this.outstanding = outstanding;
	}
	
	public Double getCGST() {
		return CGST;
	}

	public void setCGST(Double cGST) {
		CGST = cGST;
	}

	public Double getSGST() {
		return SGST;
	}

	public void setSGST(Double sGST) {
		SGST = sGST;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(Double saleValue) {
		this.saleValue = saleValue;
	}

	public String getCorD() {
		return CorD;
	}

	public void setCorD(String corD) {
		CorD = corD;
	}

}
