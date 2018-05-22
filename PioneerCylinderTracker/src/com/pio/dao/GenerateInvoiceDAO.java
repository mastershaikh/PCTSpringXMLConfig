package com.pio.dao;

import com.pio.model.GenerateInvoiceBean;

public interface GenerateInvoiceDAO {

	String generateInvoice(GenerateInvoiceBean gi);

	String makeInvoice(Long billNo);

	String checkInvoiceGenStatus(GenerateInvoiceBean gi);

	Double billTillDate(String dealerId);

	String generateDealerInvoice(GenerateInvoiceBean gi);

}
