package com.pio.vo;

import java.time.LocalDate;
import java.util.ArrayList;

public class PrintECRVO {

	private String ecrNo;
	private String dealerName;
	private String address;
	private String contactNo;
	private ArrayList<Long> cylinderList;
	private String lorryNo;
	private LocalDate ecrDate;
	public String getEcrNo() {
		return ecrNo;
	}
	public void setEcrNo(String ecrNo) {
		this.ecrNo = ecrNo;
	}
	public ArrayList<Long> getCylinderList() {
		return cylinderList;
	}
	public void setCylinderList(ArrayList<Long> cylinderList) {
		this.cylinderList = cylinderList;
	}
	public String getLorryNo() {
		return lorryNo;
	}
	public void setLorryNo(String lorryNo) {
		this.lorryNo = lorryNo;
	}
	public LocalDate getEcrDate() {
		return ecrDate;
	}
	public void setEcrDate(LocalDate ecrDate) {
		this.ecrDate = ecrDate;
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
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	
}
