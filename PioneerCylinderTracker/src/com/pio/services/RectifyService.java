package com.pio.services;

import com.pio.model.GenerateInvoiceBean;

public interface RectifyService {

	public String rectifyCylinder(String wrongCid, String rightCid, String dealerId);

	public String rectifyLorry(String billNo, String lorryNo);

	public GenerateInvoiceBean reprintBill(String billNo);

}
