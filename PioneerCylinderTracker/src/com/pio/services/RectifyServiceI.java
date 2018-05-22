package com.pio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pio.dao.RectifyDAO;
import com.pio.model.GenerateInvoiceBean;

@Service
public class RectifyServiceI implements RectifyService {

	@Autowired
	private RectifyDAO recDAO;

	@Override
	public String rectifyCylinder(String wrongCid, String rightCid, String dealerId) {
		String result ="";
		result = recDAO.rectifyCylinder(wrongCid,rightCid,dealerId);
		return result;
	}

	@Override
	public String rectifyLorry(String billNo, String lorryNo) {
		String result ="";
		result = recDAO.rectifyLorry(billNo,lorryNo);
		return result;
	}

	@Override
	public GenerateInvoiceBean reprintBill(String billNo) {
		GenerateInvoiceBean result =new GenerateInvoiceBean();
		result = recDAO.reprintBill(billNo);
		return result;
	}
}
