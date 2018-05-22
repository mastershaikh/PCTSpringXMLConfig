package com.pio.vo;

import java.time.LocalDate;
import java.util.ArrayList;

public class PrintInvoiceVO {

	private String customerName;
	private String address;
	private String contactNo;
	private String emailId;
	private String tin;
	private long billNo;
	private LocalDate date;
	private String vehicleNo;
	private String gasType;
	private int noCylinder;
	private Double quantity;
	private Double SGST;
	private Double CGST;
	private Double billValue;
	private String numtoword;
	private Double saleValue;
	private double rate;
	private ArrayList<Long> cylList1;
	private ArrayList<Long> cylList1_5;
	private ArrayList<Long> cylList3;
	private ArrayList<Long> cylList5;
	private ArrayList<Long> cylList6;
	private ArrayList<Long> cylList7;
	
	private ArrayList<Double> cylQuant;
	private ArrayList<Double> cylCap;
	private ArrayList<Integer> cylCount;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public long getBillNo() {
		return billNo;
	}
	public void setBillNo(long billNo) {
		this.billNo = billNo;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	
	public Double getSGST() {
		return SGST;
	}
	public void setSGST(Double sGST) {
		SGST = sGST;
	}
	public Double getCGST() {
		return CGST;
	}
	public void setCGST(Double cGST) {
		CGST = cGST;
	}
	public Double getBillValue() {
		return billValue;
	}
	public void setBillValue(Double billValue) {
		this.billValue = billValue;
	}
	public String getNumtoword() {
		return numtoword;
	}
	public void setNumtoword(String numtoword) {
		this.numtoword = numtoword;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public ArrayList<Long> getCylList1() {
		return cylList1;
	}
	public void setCylList1(ArrayList<Long> cylList1) {
		this.cylList1 = cylList1;
	}
	public ArrayList<Long> getCylList1_5() {
		return cylList1_5;
	}
	public void setCylList1_5(ArrayList<Long> cylList1_5) {
		this.cylList1_5 = cylList1_5;
	}
	public ArrayList<Long> getCylList3() {
		return cylList3;
	}
	public void setCylList3(ArrayList<Long> cylList3) {
		this.cylList3 = cylList3;
	}
	public ArrayList<Long> getCylList5() {
		return cylList5;
	}
	public void setCylList5(ArrayList<Long> cylList5) {
		this.cylList5 = cylList5;
	}
	public ArrayList<Long> getCylList6() {
		return cylList6;
	}
	public void setCylList6(ArrayList<Long> cylList6) {
		this.cylList6 = cylList6;
	}
	public ArrayList<Long> getCylList7() {
		return cylList7;
	}
	public void setCylList7(ArrayList<Long> cylList7) {
		this.cylList7 = cylList7;
	}
	public String getGasType() {
		return gasType;
	}
	public void setGasType(String gasType) {
		this.gasType = gasType;
	}
	public int getNoCylinder() {
		return noCylinder;
	}
	public void setNoCylinder(int noCylinder) {
		this.noCylinder = noCylinder;
	}
	public Double getSaleValue() {
		return saleValue;
	}
	public void setSaleValue(Double saleValue) {
		this.saleValue = saleValue;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public ArrayList<Double> getCylQuant() {
		return cylQuant;
	}
	public void setCylQuant(ArrayList<Double> cylQuant) {
		this.cylQuant = cylQuant;
	}
	public ArrayList<Double> getCylCap() {
		return cylCap;
	}
	public void setCylCap(ArrayList<Double> cylCap) {
		this.cylCap = cylCap;
	}
	public ArrayList<Integer> getCylCount() {
		return cylCount;
	}
	public void setCylCount(ArrayList<Integer> cylCount) {
		this.cylCount = cylCount;
	}
	
	
}
