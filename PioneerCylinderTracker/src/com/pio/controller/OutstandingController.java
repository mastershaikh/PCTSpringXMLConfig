package com.pio.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pio.model.OutstandingBean;
import com.pio.services.OutstandingService;
import com.pio.util.DealerUtil;
import com.pio.vo.PrintCylOutVO;
import com.pio.vo.PrintDealerOutVO;

@Controller
public class OutstandingController {
	
	@Autowired
	private OutstandingService outS;
	
	@Autowired
	private DealerUtil dealerU;
	
	@RequestMapping(value="/GenerateOutstanding", method = RequestMethod.GET)
	public String getOutstanding (final Model model) {
		model.addAttribute("gout", new OutstandingBean());
		return "DealerInvoiceOutstanding";
	}
	
	@RequestMapping(value = "/GenerateOutstanding", method = RequestMethod.POST)
	public ModelAndView processOutstanding (
			@ModelAttribute("gout") final OutstandingBean ob) {
		ModelAndView result = new ModelAndView("DealerInvoiceOutstanding", "inv",
				"Invalid Dealer Id");
		String dealerId = ob.getDealerId();
		String dealerStatus = dealerU.login(dealerId);
		if(!dealerStatus.equals("FAIL")) {
		String generate = outS.genOutstanding(dealerId);
		if(!generate.equals("FAIL")) {
		String[] g = generate.split("#");
		String[] billNo = g[0].split(";");
		ArrayList<Long> bilNo = new ArrayList<>();
		for(String bn:billNo) {
			Long c = Long.valueOf(bn);
			if(c instanceof Long) {
				bilNo.add(c);
			}
		}
		
		String[] billValue = g[1].split(";");
		Double totalBill = 0.0;
		ArrayList<Double> tb = new ArrayList<>();
		for(String bv:billValue) {
			Double d = Double.valueOf(bv);
			if(d instanceof Double) {
				totalBill +=d;
				tb.add(d);
			}
		}
		
				
		String[] totalCylinders = g[2].split(";");
		int totalCyl =0;
		ArrayList<Integer> tc = new ArrayList<>();
		for(String a:totalCylinders) {
			Integer d = Integer.valueOf(a);
			if(d instanceof Integer) {
				totalCyl +=d;
				tc.add(d);
					}
				}
		
		String[] billDate = g[3].split(";");
		ArrayList<Timestamp> bd = new ArrayList<>();
		for(String c:billDate) {
			Timestamp d = Timestamp.valueOf(c);
			if(d instanceof Timestamp) {
				bd.add(d);
					}
				}
		String[] CGST = g[4].split(";");
		ArrayList<Double> cg = new ArrayList<>();
		for(String c:CGST) {
			Double d = Double.valueOf(c);
			if(d instanceof Double) {
				cg.add(d);
					}
				}
		
		String[] SGST = g[5].split(";");
		ArrayList<Double> sg = new ArrayList<>();
		for(String c:SGST) {
			Double d = Double.valueOf(c);
			if(d instanceof Double) {
				sg.add(d);
					}
			}
		
		String[] r = g[6].split(";");
		ArrayList<Double> rt = new ArrayList<>();
		for(String c:r) {
			Double d = Double.valueOf(c);
			if(d instanceof Double) {
				rt.add(d);
					}
			}
		
		String[] q = g[7].split(";");
		ArrayList<Double> qt = new ArrayList<>();
		for(String c:q) {
			Double d = Double.valueOf(c);
			if(d instanceof Double) {
				qt.add(d);
					}
			}
		
		OutstandingBean  outb = new OutstandingBean();
		outb.setBillNos(g[0]);
		outb.setCylinderTaken(totalCyl);
		outb.setOutDate(Timestamp.valueOf(LocalDateTime.now()));
		outb.setTotalAmount(totalBill);
		outb.setDealerId(dealerId);
		System.out.println("Before outS.updateOutTable total bill"+totalBill);
		String outstatus = outS.updateOutTable(outb);
		System.out.println("OutstandingController 92 "+outstatus);
		if(outstatus.equals("SUCCESS")) {
			String outs = outS.updateInvoiceTable(g[0]);
			System.out.println("OutstandingController "+outs);
			if(outs.equals("SUCCESS")) {
				String dealer = dealerU.dealerDetails(dealerId);
				String[] d = dealer.split(";");
				String dealerName = d[1];
				String address = d[5];
				String contactNo = d[6];
				PrintDealerOutVO pdo = new PrintDealerOutVO();
				pdo.setDealerId(dealerId);
				pdo.setAddress(address);
				pdo.setContactNo(contactNo);
				pdo.setDealerName(dealerName);
				pdo.setBillDate(bd);
				pdo.setBillNos(bilNo);
				pdo.setCGST(cg);
				pdo.setSGST(sg);
				pdo.setBillValues(tb);
				pdo.setUnitRate(rt);
				pdo.setCylinderNos(tc);
				pdo.setQuantities(qt);
				pdo.setTotalQuantity(qt.stream().mapToDouble(f->f.doubleValue()).sum());
				pdo.setTotalSGST(sg.stream().mapToDouble(f->f.doubleValue()).sum());
				pdo.setTotalCGST(cg.stream().mapToDouble(f->f.doubleValue()).sum());
				pdo.setTotalBillValue(tb.stream().mapToDouble(f -> f.doubleValue()).sum());
				pdo.setTotalCylinders(tc.stream().mapToInt(f->f.intValue()).sum());
				outS.printDealerOut(pdo);
		result = new ModelAndView("DealerInvoiceOutstanding","suc","Outstanding Generated");
			}
		}
		}else {
			result = new ModelAndView("DealerInvoiceOutstanding","fail","Outstanding Already Generated ");
		}
		}
		return result;
	}
	
	@RequestMapping(value="/GenerateCylinderOutstanding", method = RequestMethod.GET)
	public String getCylstanding () {
		return "DealerCylinderOutstanding";
	}
	
	@RequestMapping(value = "/GenerateCylinderOutstanding", method = RequestMethod.POST)
	public ModelAndView processCylstanding (
			@RequestParam(value="dealerId") String dealerId) {
		ModelAndView result = new ModelAndView("DealerCylinderOutstanding", "inv",
				"Invalid Dealer Id");
		String dealerStatus = dealerU.login(dealerId);		
		
		if(!dealerStatus.equals("FAIL")) {
			
			String dealer = dealerU.dealerDetails(dealerId);
			String[] d = dealer.split(";");
			String dealerName = d[1];
			String address = d[5];
			String contactNo = d[6];
			
		String generate = outS.genCylstanding(dealerId);
		if(!generate.equals("FAIL")) {
		String[] c = generate.split("#");
		String[] cno = c[0].split(";");
		String[] issDate = c[1].split(";");
		ArrayList<Long> cylno = new ArrayList<Long>();
		ArrayList<Timestamp> issueDate = new ArrayList<Timestamp>();
		for(String b:cno) {
			Long l = Long.valueOf(b);
			if(l instanceof Long) {
				cylno.add(l);
			}
		}
		
		for(String b:issDate) {
			Timestamp t = Timestamp.valueOf(b);
			if(t instanceof Timestamp) {
				issueDate.add(t);
			}
		}

		PrintCylOutVO pco = new PrintCylOutVO();
		pco.setCylno(cylno);
		pco.setIssueDate(issueDate);
		pco.setDealerName(dealerName);
		pco.setAddress(address);
		pco.setContactNo(contactNo);
		Timestamp date = Timestamp.valueOf(LocalDateTime.now());
		pco.setStatementDate(date);
		pco.setDealerId(dealerId);
		String txt = outS.printCylOut(pco);
		System.out.println("BillGenerated status"+txt);
		result = new ModelAndView("DealerCylinderOutstanding","out","Cylinder Outstanding Generated for "
				+ "Dealer "+dealerId);
		}else {
			result = new ModelAndView("DealerCylinderOutstanding","dhrac","Dealer has returned all Cylinders");
		}
		}
		return result;
	}
	
	@RequestMapping(value="/CurrentMonthSale", method = RequestMethod.GET)
	public String getCurrentSale () {
		return "CurrentMonthSale";
	}
	
}
