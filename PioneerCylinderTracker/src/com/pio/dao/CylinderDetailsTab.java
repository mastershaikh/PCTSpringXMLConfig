package com.pio.dao;

import java.util.TreeMap;

import com.pio.model.DealerCylinderTxn;
import com.pio.vo.PreviewInvoiceVO;

public interface CylinderDetailsTab {

	public long countCylinder (final String cylinderType);
	public String typeOfCylinders(Long cylinderId);
	public String findCylinder(Long cylinderId);
	public TreeMap<Long, String> capacityOfCylinders(String[] cno);
	public PreviewInvoiceVO previewInvoice(String dealerId);
	public String previewDealerInvoice(String dealerId);
	public Double getCylinderCapacity(Long cylinderId);
	public DealerCylinderTxn dealerTxnCylinder(Long l);
	public String getUsageAndType(Long cylinderId);
}
