package com.pio.vo;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PrintDealerOutVO {

	private String dealerId;
	private String dealerName;
	private String contactNo;
	private String address;
	private int totalCylinders;
	private double totalCGST;
	private double totalSGST;
	
	private double totalQuantity;
	private ArrayList<Long> billNos = new ArrayList<>();
	private ArrayList<Timestamp> billDate = new ArrayList<>();
	private ArrayList<Integer> cylinderNos = new ArrayList<>();
	private ArrayList<Double> quantities = new ArrayList<>();
	private ArrayList<Double> unitRate =new ArrayList<>();
	private ArrayList<Double> CGST = new ArrayList<>();
	private ArrayList<Double> SGST = new ArrayList<>();
	private ArrayList<Double> billValues = new ArrayList<>();
	private double totalBillValue ;
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
	
	public void setTotalCylinders(int totalCylinders) {
		this.totalCylinders = totalCylinders;
	}
	public int getTotalCylinders() {
		return totalCylinders;
	}
	
	public void setTotalCGST(double totalCGST) {
		this.totalCGST = totalCGST;
	}
	public double getTotalCGST() {
		return totalCGST;
	}
	public double getTotalSGST() {
		return totalSGST;
	}
	public void setTotalSGST(double totalSGST) {
		this.totalSGST = totalSGST;
	}
	public double getTotalBillValue() {
		return totalBillValue;
	}
	
	public ArrayList<Double> getBillValues() {
		return billValues;
	}
	public void setBillValues(ArrayList<Double> billValues) {
		this.billValues = billValues;
	}
	
	public ArrayList<Long> getBillNos() {
		return billNos;
	}
	public void setBillNos(ArrayList<Long> billNos) {
		this.billNos = billNos;
	}
	public ArrayList<Timestamp> getBillDate() {
		return billDate;
	}
	public void setBillDate(ArrayList<Timestamp> billDate) {
		this.billDate = billDate;
	}
	public ArrayList<Integer> getCylinderNos() {
		return cylinderNos;
	}
	public void setCylinderNos(ArrayList<Integer> cylinderNos) {
		this.cylinderNos = cylinderNos;
	}
	public ArrayList<Double> getQuantities() {
		return quantities;
	}
	public void setQuantities(ArrayList<Double> quantities) {
		this.quantities = quantities;
	}
	public ArrayList<Double> getUnitRate() {
		return unitRate;
	}
	public void setUnitRate(ArrayList<Double> unitRate) {
		this.unitRate = unitRate;
	}
	
	public ArrayList<Double> getCGST() {
		return CGST;
	}
	public void setCGST(ArrayList<Double> cGST) {
		CGST = cGST;
	}
	public ArrayList<Double> getSGST() {
		return SGST;
	}
	public void setSGST(ArrayList<Double> sGST) {
		SGST = sGST;
	}
	
	public double getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public void setTotalBillValue(double totalBillValue) {
		this.totalBillValue = totalBillValue;
	}
	
	
}
