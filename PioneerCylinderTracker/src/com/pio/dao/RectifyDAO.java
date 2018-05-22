package com.pio.dao;

import com.pio.model.GenerateInvoiceBean;

public interface RectifyDAO {

	public String rectifyCylinder(String wrongCid, String rightCid, String dealerId);

	public String rectifyLorry(String billNo, String lorryNo);

	public GenerateInvoiceBean reprintBill(String billNo);

}
