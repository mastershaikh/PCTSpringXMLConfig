package com.pio.vo;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PrintCylOutVO {

	private ArrayList<Long> cylno;
	private ArrayList<Timestamp> issueDate;
	private String dealerName;
	private String dealerId;
	private String contactNo;
	private String address;
	private Timestamp statementDate;
	public ArrayList<Long> getCylno() {
		return cylno;
	}
	public void setCylno(ArrayList<Long> cylno) {
		this.cylno = cylno;
	}
	public ArrayList<Timestamp> getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(ArrayList<Timestamp> issueDate) {
		this.issueDate = issueDate;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getStatementDate() {
		return statementDate;
	}
	public void setStatementDate(Timestamp statementDate) {
		this.statementDate = statementDate;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	
}
