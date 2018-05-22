package com.pio.dao;

import java.util.ArrayList;
import java.util.TreeMap;

import com.pio.model.CylinderTransactions;

public interface CylinderTransactionDAO {
	
	public String transact(CylinderTransactions buy);

	public Long findSequenceID();
	
	public String listCylinder(String dealerId);

	public TreeMap<Integer, String> listCylinders(String dealerId);

	public String returnCylinders(CylinderTransactions cylinders);

	public String usageStatus(Long cylinderId);

	public Long findBillID();

	public String batchTransact(CylinderTransactions[] cylinder);

	public String returnCylinderBatch(ArrayList<Long> cylNumbers, String dealerId);

}
