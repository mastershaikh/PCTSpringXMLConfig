package com.pio.services;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pio.dao.OutstandingDAO;
import com.pio.model.OutstandingBean;
import com.pio.vo.PrintCylOutVO;
import com.pio.vo.PrintDealerOutVO;

@Service
public class OutstandingServiceI implements OutstandingService {

	@Autowired
	private OutstandingDAO outDAO;
	
	@Override
	public String genOutstanding(String dealerId) {

		String result ="";
		result = outDAO.genOutstanding(dealerId);
		return result;
	}

	@Override
	public String updateOutTable(OutstandingBean outb) {
		String result ="";
		Long outId = outDAO.generateOutSequence();
		outb.setOutId(outId);
		result = outDAO.updateOutTable(outb);
		return result;
	}

	@Override
	public String updateInvoiceTable(String billNos) {
		String result ="";
		result = outDAO.updateInvoiceTable(billNos);
		return result;
	}

	@Override
	public String genCylstanding(String dealerId) {
		String result ="";
		result = outDAO.genCylstanding(dealerId);
		return result;
	}

	@Override
	public String printCylOut(PrintCylOutVO pco) {
		String result ="FAIL";
		String cno = pco.getCylno().toString();
		List<String> lines = Arrays.asList("Dealer "+pco.getDealerName(),"DealerId "+pco.getDealerId(),
				"Address "+pco.getAddress(),
				"Contact "+pco.getContactNo(),"Cylinder Number "+cno,"Issue Date "+pco.getIssueDate());
		Path file = Paths.get("E:\\CylOut\\"+pco.getDealerId()+".txt");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
			result ="SUCCESS";
		} catch (IOException e) {
			e.printStackTrace();
			result = "FAIL";
		}
		return result;
	}

	@Override
	public String printDealerOut(PrintDealerOutVO pdo) {
		String result ="FAIL";
		LocalDate currDate = LocalDate.now();
		List<String> lines = Arrays.asList("Dealer "+pdo.getDealerName(),"DealerId "+pdo.getDealerId(),
				"Address "+pdo.getAddress(),
				"Contact "+pdo.getContactNo(),"Cylinders "+pdo.getTotalCylinders(),"Issue Date "+pdo.getBillDate(),
				"Rate"+pdo.getUnitRate(),"Quantities"+pdo.getQuantities(),"Total VAT ="+(pdo.getTotalCGST()+pdo.getTotalSGST())
				,"Total Quantities "+pdo.getTotalQuantity(),"Total Bill "+pdo.getTotalBillValue());
		Path file = Paths.get("E:\\DealerOut\\"+pdo.getDealerId()+"_"+currDate+".txt");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
			result ="SUCCESS";
		} catch (IOException e) {
			e.printStackTrace();
			result = "FAIL";
		}
		return result;
	}
}
