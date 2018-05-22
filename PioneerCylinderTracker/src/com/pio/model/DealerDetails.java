package com.pio.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dealer_details")
public class DealerDetails {

	@Id
	@Column(name="dealerId")
	private String dealerId;
	
	@Column(name="dealer_name")
	private String dealerName;
	
	@Column(name="gstin")
	private String gstin;
	
	@Column(name="o2_rate_per_cm")
	private double rateo2;
	
	@Column(name="air_rate_per_cm")
	private double rateair;
	
	@Column(name="o2_drate_per_cm")
	private double drateo2;
	
	@Column(name="n2_rate_per_cm")
	private double raten2;
	
	@Column(name="n2_drate_per_cm")
	private double draten2;
	
	@Column(name="air_drate_per_cm")
	private double drateair;
	
	@Column(name="address")
	private String address;
	
	@Column(name="contact_no")
	private String contactNo;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="date_of_registration")
	private Timestamp dateOfRegistration;
	
	@Column(name="o2_no",nullable=false)
	private Long o2No;
	
	@Column(name="n2_no",nullable=false)
	private Long n2No;
	
	@Column(name="air_no",nullable=false)
	private Long airNo;
	
	@Column(name="total_cylinders_taken")
	private long totalCylindersTaken;
	
	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public Timestamp getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Timestamp dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public double getRateo2() {
		return rateo2;
	}

	public void setRateo2(double rateo2) {
		this.rateo2 = rateo2;
	}

	public double getRaten2() {
		return raten2;
	}

	public void setRaten2(double raten2) {
		this.raten2 = raten2;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getTotalCylindersTaken() {
		return totalCylindersTaken;
	}

	public void setTotalCylindersTaken(long totalCylindersTaken) {
		this.totalCylindersTaken = getO2No()+getN2No()+getAirNo();
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Long getO2No() {
		return o2No;
	}

	public void setO2No(Long o2No) {
		this.o2No = o2No;
	}

	public Long getN2No() {
		return n2No;
	}

	public void setN2No(Long n2No) {
		this.n2No = n2No;
	}

	public double getDrateo2() {
		return drateo2;
	}

	public void setDrateo2(double drateo2) {
		this.drateo2 = drateo2;
	}

	public double getDraten2() {
		return draten2;
	}

	public void setDraten2(double draten2) {
		this.draten2 = draten2;
	}

	public double getRateair() {
		return rateair;
	}

	public void setRateair(double rateair) {
		this.rateair = rateair;
	}

	public double getDrateair() {
		return drateair;
	}

	public void setDrateair(double drateair) {
		this.drateair = drateair;
	}

	public Long getAirNo() {
		return airNo;
	}

	public void setAirNo(Long airNo) {
		this.airNo = airNo;
	}
	
	
}
