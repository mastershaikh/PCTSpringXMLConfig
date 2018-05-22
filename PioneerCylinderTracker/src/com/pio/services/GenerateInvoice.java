package com.pio.services;

import com.pio.model.GenerateInvoiceBean;
import com.pio.vo.PrintInvoiceVO;

public interface GenerateInvoice {
	public String generateInvoice(GenerateInvoiceBean gi);

	public String printInvoice(PrintInvoiceVO piv);

	public String updateBill(Long billNo);

	public String finalUpdateInvoice(GenerateInvoiceBean gi);

	public String checkInvoiceGenStatus(GenerateInvoiceBean gi);

	public Double billTillDate(String dealerId);

	public String generateDealerInvoice(GenerateInvoiceBean gi);

	public String printDealerInvoice(PrintInvoiceVO piv);

}
