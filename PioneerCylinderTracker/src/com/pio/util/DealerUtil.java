package com.pio.util;

import java.util.TreeMap;

import com.pio.model.DealerDetails;

public interface DealerUtil {
	
	public String register(DealerDetails dealer);
	public String login(String dealer);
	public TreeMap<String, String> allDealers();
	public String dealerDetails(String dealerId);
	public void updateCylinderCount(String dealerId);
	public String dealerRate(String dealerId);
	public String updateTxIds(String[] txId);
	public Double dealerCylRate(String cylinderType, String dealerId);
}
