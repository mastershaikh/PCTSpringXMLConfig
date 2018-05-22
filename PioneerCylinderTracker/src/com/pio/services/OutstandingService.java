package com.pio.services;

import com.pio.model.OutstandingBean;
import com.pio.vo.PrintCylOutVO;
import com.pio.vo.PrintDealerOutVO;

public interface OutstandingService {

	public String genOutstanding(String dealerId);

	public String updateOutTable(OutstandingBean outb);

	public String updateInvoiceTable(String billNos);

	public String genCylstanding(String dealerId);

	public String printCylOut(PrintCylOutVO pco);
	
	public String printDealerOut(PrintDealerOutVO pdo);

}
