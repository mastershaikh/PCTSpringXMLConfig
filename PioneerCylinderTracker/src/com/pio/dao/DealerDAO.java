package com.pio.dao;


import java.util.TreeMap;

import com.pio.model.DealerCylinderTxn;


public interface DealerDAO {

	public String login(String dealer);
	
	public TreeMap<String,String> dealerList();

	public String getDealer(String dealerId);
	
	public String getDealerFCylinder(Long cylinderId);

	public void updateCylinderCount(String dealerId);

	public String getRate(String dealerId);

	public Double dealerCylinderRate(String cylinderType, String dealerId);

	public Long getDealerCylinderSequence();

	public String updateDealerCylinderTable(DealerCylinderTxn dct);

	public String updateTxIds(String[] txId);

}
