package com.pio.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pio.dao.DealerDAO;
import com.pio.dao.DealerRegister;
import com.pio.model.DealerDetails;

@Service
public class DealerUtility implements DealerUtil {

	@Autowired
	DealerRegister dealerDAO;
	
	@Autowired
	DealerDAO dealerL;
	
	@Override
	public String register(DealerDetails dealer){
		String result = "FAIL";
		if(dealer instanceof DealerDetails ){
		String dealerID = generateID(dealer.getDealerName());
		dealer.setDealerId(dealerID);
		Timestamp time = Timestamp.valueOf(LocalDateTime.now());
		dealer.setDateOfRegistration(time);	
		result = dealerDAO.dealerRegister(dealer);
	}
		return result;
	}
		public String generateID(final String name){
			String result = "FAIL";
			if(name instanceof String && name.length()>=2){
			String two = name.substring(0, 2).toUpperCase();
			Long sequence = dealerDAO.findSequenceID();
			result=two+sequence;
			}
			return result;
		}	
		
		@Override
		public String login(String dealerId){
			String result="";
			result=dealerL.login(dealerId);
			return result;
		}
		@Override
		public TreeMap<String,String> allDealers() {
			TreeMap<String,String> result = new TreeMap<String,String>();
			result = dealerL.dealerList();
			return result;
		}
		@Override
		public String dealerDetails(String dealerId) {
			String result="";
			result=dealerL.getDealer(dealerId);
			return result;
		}
		@Override
		public void updateCylinderCount(String dealerId) {
			dealerL.updateCylinderCount(dealerId);			
		}
		@Override
		public String dealerRate(String dealerId) {
			String result="";
			result=dealerL.getRate(dealerId);
			return result;
		}
		@Override
		public String updateTxIds(String[] txId) {
			String result="";
			result=dealerL.updateTxIds(txId);
			return result;
		}
		@Override
		public Double dealerCylRate(String cylinderType,String dealerId) {
			Double result =0.0;
			result=dealerL.dealerCylinderRate(cylinderType,dealerId);
			return result;
		}
		
		
}
