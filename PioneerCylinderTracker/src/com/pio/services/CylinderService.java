package com.pio.services;

import java.util.ArrayList;
import java.util.TreeMap;

import com.pio.model.CylinderTransactions;
import com.pio.model.DealerCylinderTxn;
import com.pio.vo.PreviewInvoiceVO;

public interface CylinderService {

	public String purchase(CylinderTransactions buy);
	public long countCylinders(String cylinderType);
	public String returnCylinder(CylinderTransactions cylinder);
	public TreeMap<Integer, String> listCylinders(String dealerId);
	public String usageStatus(Long cylinderId);
	public String typeOfCylinders(Long cylinderId);
	public String getDealerFromCylinder(Long cylinderId);
	public TreeMap<Long, String> capacityOfCylinders(String[] cNo);
	public String dealerCylinderBuy(DealerCylinderTxn dct);
	public PreviewInvoiceVO previewInvoice(String dealerId);
	public String previewDealerInvoice(String dealerId);
	public DealerCylinderTxn dealerTxnCylinder(Long l);
	public String batchPurchase(CylinderTransactions[] cylinder);
	public String returnCylinderBatch(ArrayList<Long> cylNumbers, String dealerId);
}
