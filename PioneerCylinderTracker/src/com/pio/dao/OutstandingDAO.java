package com.pio.dao;

import com.pio.model.OutstandingBean;

public interface OutstandingDAO {

	public String genOutstanding(String dealerId);

	public String updateOutTable(OutstandingBean outb);

	public Long generateOutSequence();

	public String updateInvoiceTable(String billNos);

	public String genCylstanding(String dealerId);

}
