package com.pio.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pio.model.DealerCylinderTxn;
import com.pio.model.GenerateInvoiceBean;
import com.pio.numtoword.EnglishNumberToWords;
import com.pio.services.CylinderService;
import com.pio.services.GenerateInvoice;
import com.pio.services.RectifyService;
import com.pio.util.CylinderUtil;
import com.pio.util.DealerUtil;
import com.pio.vo.PreviewInvoiceVO;
import com.pio.vo.PrintInvoiceVO;

@Controller
public class InvoiceController {

	@Autowired
	GenerateInvoice generateInvoice;
	
	@Autowired
	private RectifyService recS;
	
	@Autowired
	private DealerUtil dealerU;
	
	@Autowired
	private CylinderUtil cylinderU;
	
	@Autowired
	private CylinderService cylinderS;
	
	@RequestMapping(value = "/GenerateInvoice", method = RequestMethod.POST)
	public ModelAndView generateInvoice ( HttpSession session,@RequestParam(value="dealerId") String dealerId,
			@RequestParam(value="company") String company,@RequestParam(value="vehicleNo") String vehicleNo) {
		ModelAndView result = new ModelAndView("GenerateBill", "inv",
				"Invalid Dealer Id");
		GenerateInvoiceBean gi = new GenerateInvoiceBean();
		gi.setDealerId(dealerId);
		gi.setVehicleNo(vehicleNo);
		PrintInvoiceVO piv = new PrintInvoiceVO();
		dealerId = dealerU.login(gi.getDealerId());
		if(dealerId instanceof String) {
			if(company.equals("Y")){
				gi.setCorD("C");
		String dealer = dealerU.dealerDetails(dealerId);
		String rateOfON = dealerU.dealerRate(dealerId);
		String[] rate = rateOfON.split("-");
		String[] d = dealer.split(";");
		gi.setDealerId(dealerId);
		String dealerName = d[1];
		String tin = d[2];
		String address = d[5];
		String contactNo = d[6];
		
		String billGenStatus = generateInvoice.checkInvoiceGenStatus(gi);
		if(billGenStatus.equals("SUCCESS")) {
		String cylinders = generateInvoice.generateInvoice(gi);		
		Long billNo = gi.getBillNo();
		LocalDate billDate = gi.getBillDate().toLocalDateTime().toLocalDate();
		
		String[] cNo =cylinders.split("#");
		int[] o2=new int[6],n2=new int[6],a1=new int[6];
		TreeMap<Long,String> gasType = new TreeMap<Long,String>();
		ArrayList<Long> caps1 = new ArrayList<Long>();
		ArrayList<Long> caps1_5 = new ArrayList<Long>();
		ArrayList<Long> caps3 = new ArrayList<Long>();
		ArrayList<Long> caps5 = new ArrayList<Long>();
		ArrayList<Long> caps6 = new ArrayList<Long>();
		ArrayList<Long> caps7 = new ArrayList<Long>();
		gasType = cylinderS.capacityOfCylinders(cNo);//Working here DONE
		for(Map.Entry<Long,String> g:gasType.entrySet()) {
			String[] a = g.getValue().split("-");
			if(a[0].contains("OXYGEN")) {
				Double cap = Double.valueOf(a[1]);
				gi.setCylinderType("INDUSTRIAL OXYGEN [HSN:28044090]");
				if(cap==7.00) {
					caps7.add(g.getKey());
					o2[5]++;
				}else if(cap==6.00) {
					caps6.add(g.getKey());
					o2[4]++;
				}else if(cap==5.00) {
					caps5.add(g.getKey());
					o2[3]++;
				}
				else if(cap==3.00) {
					caps3.add(g.getKey());
					o2[2]++;
				}
				else if(cap==1.50) {
					caps1_5.add(g.getKey());
					o2[1]++;
				}
				else if(cap==1.00) {
					caps1.add(g.getKey());
					o2[0]++;
				}
			}else if(a[0].contains("NITROGEN")) {
				gi.setCylinderType("INDUSTRIAL NITROGEN [HSN:28043000]");
				Double cap = Double.valueOf(a[1]);
				if(cap==7.00) {
					caps7.add(g.getKey());
					n2[5]++;
				}else if(cap==6.00) {
					caps6.add(g.getKey());
					n2[4]++;
				}else if(cap==5.00) {
					caps5.add(g.getKey());
					n2[3]++;
				}
				else if(cap==3.00) {
					caps3.add(g.getKey());
					n2[2]++;
				}
				else if(cap==1.50) {
					caps1_5.add(g.getKey());
					n2[1]++;
				}
				else if(cap==1.00) {
					caps1.add(g.getKey());
					n2[0]++;
				}
			}else if(a[0].contains("Air")){
				Double cap = Double.valueOf(a[1]);
				if(cap==7.00) {
					caps7.add(g.getKey());
					a1[5]++;
				}else if(cap==6.00) {
					caps6.add(g.getKey());
					a1[4]++;
				}else if(cap==5.00) {
					caps5.add(g.getKey());
					a1[3]++;
				}
				else if(cap==3.00) {
					caps3.add(g.getKey());
					a1[2]++;
				}
				else if(cap==1.50) {
					caps1_5.add(g.getKey());
					a1[1]++;
				}
				else if(cap==1.00) {
					caps1.add(g.getKey());
					a1[0]++;
				}
			}
		}
			
		int oo2 = IntStream.of(o2).sum();
		int nn2 = IntStream.of(n2).sum();
		int air = IntStream.of(a1).sum();
		Double bill = 0.0;	
		Double totalQuantity = 0.0;
		if(oo2>0) {
			gi.setTotalCylinders(oo2);
			totalQuantity += o2[0]*1.00+o2[1]*1.50+o2[2]*3.00+o2[3]*5.00+o2[4]*6.00+o2[5]*7.00;
			try {
				bill = Double.parseDouble(rate[0])*totalQuantity;
				gi.setRate(Double.valueOf(rate[0]));
				}catch(NumberFormatException n) {
				}
			}
		else if(nn2>0) {
			gi.setTotalCylinders(nn2);
			totalQuantity += n2[0]*1.00+n2[1]*1.50+n2[2]*3.00+n2[3]*5.00+n2[4]*6.00+n2[5]*7.00;
			try {
				bill = Double.parseDouble(rate[1])*totalQuantity;
				gi.setRate(Double.valueOf(rate[1]));
				}catch(NumberFormatException n) {
				}
			}
		else if(air>0) {
			gi.setTotalCylinders(air);
			totalQuantity = a1[0]*1.00+a1[1]*1.50+a1[2]*3.00+a1[3]*5.00+a1[4]*6.00+a1[5]*7.00;
			try {
				bill = Double.parseDouble(rate[2])*totalQuantity;
				gi.setRate(Double.valueOf(rate[2]));
				}catch(NumberFormatException n) {
				}
			}
		gi.setQuantity(totalQuantity);
		
		//Billing Section starts
		Double SGST = bill*0.09;
		SGST = Math.round(SGST*100D)/100D;
		gi.setSGST(SGST);
		Double CGST = bill*0.09;
		CGST = Math.round(CGST*100D)/100D;
		gi.setCGST(CGST);
		Double billValue = bill+SGST+CGST;
		gi.setSaleValue(bill);
		gi.setBillNo(billNo);
		billValue = Math.round(billValue*100D)/100D;
		gi.setBillValue(billValue);//Billing Section ends
		long rupee = billValue.longValue();
		Double temp = (billValue - rupee);
		temp = Math.round((temp * 100.0)*100D)/100D;
		long paisa = temp.longValue();
		String numtoword = "RUPEES " + EnglishNumberToWords.convert( rupee ).toUpperCase() + " AND PAISA "
		           + EnglishNumberToWords.convert(paisa).toUpperCase() ;
		//Cylinder List as per capacity starts
		if(o2[0]>0 || n2[0]>0) {
			piv.setCylList1(caps1);
		}
		if(o2[1]>0 || n2[1]>0) {
			piv.setCylList1_5(caps1_5);
		}if(o2[2]>0 || n2[2]>0) {
			piv.setCylList3(caps3);
		}if(o2[3]>0 || n2[3]>0) {
			piv.setCylList5(caps5);
		}if(o2[4]>0 || n2[4]>0) {
			piv.setCylList6(caps6);
		}if(o2[5]>0 || n2[5]>0) {
			piv.setCylList7(caps7);
		}//Cylinder List as per capacity ends
		
		String status = generateInvoice.finalUpdateInvoice(gi);
		if(status.equals("SUCCESS")) {
			
			piv.setAddress(address);
			piv.setBillNo(billNo);
			piv.setBillValue(billValue);
			piv.setCGST(CGST);
			piv.setSGST(SGST);
			piv.setContactNo(contactNo);
			piv.setCustomerName(dealerName);
			piv.setVehicleNo(gi.getVehicleNo());
			piv.setSaleValue(gi.getSaleValue());
			piv.setNoCylinder(gi.getTotalCylinders());
			piv.setNumtoword(numtoword);
			piv.setDate(billDate);
			piv.setQuantity(totalQuantity);
			piv.setRate(gi.getRate());
			piv.setTin(tin);
			piv.setGasType(gi.getCylinderType());
			String txt = generateInvoice.printInvoice(piv);
			System.out.println("BillGenerated status"+txt);
			result = new ModelAndView("GenerateBill","bil","Bill Generated for "
					+ "Dealer "+dealerName+"\n Bill No is "+billNo
					+"Bill Amount = Rs."+billValue);
		}
		}
		else {
			result = new ModelAndView("GenerateBill","exist","Bill Already Generated! Check among these bills "+billGenStatus);
		}
		}else {
			result = generateDealerBill(dealerId,gi.getVehicleNo());
		}
	}
		return result;
	}
	
	public ModelAndView generateDealerBill(String dealerId, String vehicleNo) {
		
		ModelAndView result = new ModelAndView("GenerateBill","dealerBill","Bill Generated for");
		PrintInvoiceVO piv = new PrintInvoiceVO();
		GenerateInvoiceBean gi = new GenerateInvoiceBean();
		String status = cylinderS.previewDealerInvoice(dealerId);
		if(status instanceof String)
		{
			gi.setCorD("D");
		String[] p = status.split("#");
		String[] cylinderCount = p[0].split(";");int totalCCount =0;
		ArrayList<Integer> cylCount = new ArrayList<Integer>();
		for(String cc:cylinderCount) {
			Integer i = Integer.valueOf(cc);
			if(i instanceof Integer) {
				totalCCount +=i;
				cylCount.add(i);
			}
		}
		gi.setTotalCylinders(totalCCount);//Total cylinders Taken
		String[] capacity =p[1].split(";");
		ArrayList<Double> cap = new ArrayList<Double>();
		for(String cp:capacity) {
			Double d = Double.valueOf(cp);
			if(d instanceof Double) {
				cap.add(d);
			}
		}
		
		String[] cylinderType = p[2].split(";");
		gi.setCylinderType(cylinderType[0]);//type of cylinder
		
		String[] quantity = p[3].split(";");
		ArrayList<Double> q = new ArrayList<Double>();
		Double tquant =0.0;
		for(String c:quantity) {
			Double d = Double.valueOf(c);
			if(d instanceof Double) {
				q.add(d);
				tquant += d;
			}
		}
		gi.setQuantity(tquant);//Quantity in Cu.m
		String[] billValue = p[4].split(";");
		Double bill =0.0;
		for(String c:billValue) {
			Double d = Double.valueOf(c);
			if(d instanceof Double) {
				bill +=d;
			}
		}
		piv.setSaleValue(bill);
		gi.setSaleValue(bill);
		String[] TxId = p[5].split("-");//Txn id of dealer cylinders 
		String dttu = dealerU.updateTxIds(TxId);//update DealerCylinderTxn model
		if(dttu.equals("SUCCESS")) {
			String dealer = dealerU.dealerDetails(dealerId);
			Double rate = dealerU.dealerCylRate(cylinderType[0],dealerId);
			gi.setRate(rate);
			gi.setCylinderNo(p[5]);
			String[] d = dealer.split(";");
			gi.setDealerId(dealerId);
			String dealerName = d[1];
			String tin = d[2];
			String address = d[5];
			String contactNo = d[6];
			Double SGST = bill*0.09;
			SGST = Math.round(SGST*100D)/100D;
			gi.setSGST(SGST);//SGST
			Double CGST = bill*0.09;
			CGST = Math.round(CGST*100D)/100D;
			gi.setCGST(CGST);//CGST
			bill = ((bill+CGST+SGST)*100D)/100D;
			gi.setBillValue(bill);//InvoiceAmount
			
			gi.setVehicleNo(vehicleNo);//Vehicle No
		String genstatus = generateInvoice.generateDealerInvoice(gi);		
		
		long rupee = bill.longValue();
		Double temp = (bill - rupee);
		temp = Math.round((temp * 100.0)*100D)/100D;
		long paisa = temp.longValue();
		String numtoword = "RUPEES " + EnglishNumberToWords.convert( rupee ).toUpperCase() + " AND PAISA "
		           + EnglishNumberToWords.convert(paisa).toUpperCase() ;
		if(genstatus.equals("SUCCESS")) {
			
			piv.setAddress(address);
			piv.setBillNo(gi.getBillNo());
			piv.setBillValue(bill);
			piv.setCGST(CGST);
			piv.setSGST(SGST);
			piv.setContactNo(contactNo);
			piv.setGasType(gi.getCylinderType());
			piv.setRate(gi.getRate());
			piv.setCustomerName(dealerName);
			piv.setTin(tin);
			piv.setVehicleNo(vehicleNo);
			piv.setNoCylinder(totalCCount);//Total number of cylinders
			piv.setNumtoword(numtoword);
			piv.setDate(gi.getBillDate().toLocalDateTime().toLocalDate());
			piv.setQuantity(tquant);//total Quantity
			piv.setCylQuant(q);//individual cylinder quantity
			piv.setCylCap(cap);//Cylinder capacity in 1,1.5,3,5,6,7 Cu.m
			piv.setCylCount(cylCount);
			generateInvoice.printDealerInvoice(piv);//returns "SUCCESS" when .txt is generated
			
			
			result = new ModelAndView("GenerateBill","bil","Bill Generated for "
					+ "Dealer "+dealerName+"\n Bill No is "+gi.getBillNo()
					+"Bill Amount = Rs."+bill);
		}
		}
		
	}
		return result;
		
	}
	
	@RequestMapping(value = "/PreviewInvoice", method = RequestMethod.POST)
	public ModelAndView processPreviewInvoice (@RequestParam(value="dealerId") String dealerId,
			@RequestParam(value="vehicleNo") String vehicleNo,
			@RequestParam(value="company") String company,HttpSession session) {
		ModelAndView result = new ModelAndView("PreviewBillDetails", "inv",
				"Invalid Dealer Id");
		GenerateInvoiceBean gi = new GenerateInvoiceBean();
		gi.setDealerId(dealerId);
		gi.setVehicleNo(vehicleNo);
		String dealerStatus = dealerU.login(dealerId);
		if(dealerStatus instanceof String) {
						if(company.equals("Y")) {
							session.setAttribute("CorD", company);
							PreviewInvoiceVO piv = new PreviewInvoiceVO();
							piv = cylinderS.previewInvoice(dealerId);
			    
							session.setAttribute("dealerId",dealerId);
							session.setAttribute("lorryNo", vehicleNo);
				if(piv.getGasN2()instanceof String) {
							session.setAttribute("GasType", piv.getGasN2());
							session.setAttribute("NoCyl", piv.getNoN2());
				}else if(piv.getGasO2() instanceof String) {
							session.setAttribute("GasType", piv.getGasO2());
							session.setAttribute("NoCyl", piv.getNoO2());
				}else if(piv.getGasAir() instanceof String){
							session.setAttribute("GasType", piv.getGasAir());
							session.setAttribute("NoCyl", piv.getNoAir());
				}
				if(piv.getCylList().size()>0) {
							session.setAttribute("cylinderList", piv.getCylList());
										
				}
							session.setAttribute("totalQuantity", piv.getQuantity());
							session.setAttribute("CGST", piv.getCGST());
							session.setAttribute("SGST", piv.getSGST());
							session.setAttribute("bill", piv.getBillValue());
								
				result = new ModelAndView("PreviewBillDetails", "success",
						"");
			
			
		}else if(company.equals("N")){
			session.setAttribute("CorD", "N");
			String result1 = cylinderS.previewDealerInvoice(dealerId);
			if(!result1.equals("FAIL")) {
				String[] p = result1.split("#");
				String[] cylinderCount = p[0].split(";");int totalCCount =0;
				ArrayList<Integer> cylCount = new ArrayList<Integer>();
				for(String cc:cylinderCount) {
					Integer i = Integer.valueOf(cc);
					if(i instanceof Integer) {
						totalCCount +=i;
						cylCount.add(i);
					}
				}
				String[] capacity =p[1].split(";");
				ArrayList<Double> cap = new ArrayList<Double>();
				for(String cp:capacity) {
					Double d = Double.valueOf(cp);
					if(d instanceof Double) {
						cap.add(d);
					}
				}
				String[] cylinderType = p[2].split(";");
				ArrayList<String> ctype = new ArrayList<String>();
				for(String c:cylinderType) {
					if(c instanceof String) {
						ctype.add(c);
					}
				}
				String[] quantity = p[3].split(";");
				ArrayList<Double> q = new ArrayList<Double>();
				Double tquant =0.0;
				for(String c:quantity) {
					Double d = Double.valueOf(c);
					if(d instanceof Double) {
						q.add(d);
						tquant += d;
					}
				}
				String[] billValue = p[4].split(";");
				Double bill =0.0;
				for(String c:billValue) {
					Double d = Double.valueOf(c);
					if(d instanceof Double) {
						bill +=d;
					}
				}
				session.setAttribute("billwithoutTax", bill);
				Double SGST = bill*0.09;
				SGST = Math.round(SGST*100D)/100D;
				session.setAttribute("SGST", SGST);
				Double CGST = bill*0.09;
				CGST = Math.round(CGST*100D)/100D;
				bill = Math.round((bill+CGST+SGST)*100D)/100D;
				session.setAttribute("dealerId", dealerId);
				session.setAttribute("cylinderType", ctype);
				session.setAttribute("cylCount", cylCount);
				session.setAttribute("quantity", q);
				session.setAttribute("CGST", CGST);
				session.setAttribute("SGST", SGST);
				session.setAttribute("bill", bill);				
				session.setAttribute("totalQuantity", tquant);
				session.setAttribute("totalCCount", totalCCount);
				session.setAttribute("lorryNo", vehicleNo);
				result = new ModelAndView("PreviewDealerBillDetails","","");
			}
			else {
				result = new ModelAndView("PreviewDealerBill","invalid","Dealer invoice not generated!");
			}
		}
	}else {
			result = new ModelAndView("PreviewBillDetails", "inv",
					"Invalid Dealer Id");
		}
		
		return result;
	}
	
	@RequestMapping(value="/ReprintInvoice", method = RequestMethod.GET)
	public String getPrint () {
		return "ReprintInvoice";
	}
	
	@RequestMapping(value="/ReprintInvoice", method = RequestMethod.POST)
	public ModelAndView processPrint (@RequestParam(value="billNo") String billNo) {
		ModelAndView result = new ModelAndView("ReprintInvoice","done","Try again");
		GenerateInvoiceBean gib = recS.reprintBill(billNo);
		PrintInvoiceVO piv = new PrintInvoiceVO();
		String dealer = dealerU.dealerDetails(gib.getDealerId());
		String[] d = dealer.split(";");
		String dealerName = d[1];
		String tin = d[2];
		String address = d[5];
		String contactNo = d[6];
		if(gib.getCorD().equals("C")) {
			//Company print
			
			piv.setAddress(address);//Address
			piv.setBillNo(gib.getBillNo());//Bill Number
			piv.setBillValue(gib.getBillValue());// Bill Value
			piv.setCGST(gib.getCGST());//CGST
			piv.setSGST(gib.getSGST());//SGST
			piv.setContactNo(contactNo);//Contact Number
			piv.setCustomerName(dealerName);// Dealer Id
			piv.setGasType(gib.getCylinderType());//cylinder Type
			piv.setDate(gib.getBillDate().toLocalDateTime().toLocalDate());//Bill Date
			//piv.setNoCylinder(gib.getCylinderNo());
			long rupee = gib.getBillValue().longValue();
			Double temp = piv.getBillValue() - rupee;
			temp = Math.round((temp * 100.0)*100D)/100D;
			long paisa = temp.longValue();
			String numtoword = "RUPEES " + EnglishNumberToWords.convert( rupee ).toUpperCase() + " AND PAISA "
			           + EnglishNumberToWords.convert(paisa).toUpperCase() ;
			piv.setNumtoword(numtoword);//Number to words
			piv.setRate(gib.getRate());//rate
			piv.setTin(tin);
			piv.setSaleValue(gib.getSaleValue());//sale value
			piv.setVehicleNo(gib.getVehicleNo());//vehicle Number
			String[] cap = gib.getCylinderNo().split("#");
			ArrayList<Long> capacity6 = new ArrayList<>();
			ArrayList<Long> capacity7 = new ArrayList<>();
			
			
			
			for(String c:cap) {
				Long l = Long.valueOf(c);
				if(l instanceof Long) {
					Double e = cylinderU.getCylinderCapacity(l);
					if(e==7.00) {
						capacity7.add(l);
					}else if(e==6.00) {
						capacity6.add(l);
					}
				}
			}
			piv.setNoCylinder(gib.getTotalCylinders());
			piv.setCylList6(capacity6);
			piv.setCylList7(capacity7);
			piv.setQuantity(gib.getQuantity());
			generateInvoice.printInvoice(piv);
			result = new ModelAndView("ReprintInvoice","bil","Invoice re-printed for "
					+ "Dealer "+dealerName+"\n Bill No is "+billNo
					+" Bill Amount = Rs."+gib.getBillValue());
		}
		else if(gib.getCorD().equals("D")) {

//Dealer print
			piv.setAddress(address);//Address
			piv.setBillNo(gib.getBillNo());//Bill Number
			Double billValue = (gib.getBillValue()*100D)/100D;
			piv.setBillValue(billValue);// Bill Value
			piv.setCGST(gib.getCGST());//CGST
			piv.setSGST(gib.getSGST());//SGST
			piv.setContactNo(contactNo);//Contact Number
			piv.setCustomerName(dealerName);// Dealer Id
			piv.setGasType(gib.getCylinderType());//cylinder Type
			piv.setDate(gib.getBillDate().toLocalDateTime().toLocalDate());//Bill Date
			//piv.setNoCylinder(gib.getCylinderNo());
			long rupee = gib.getBillValue().longValue();
			Double temp = (gib.getBillValue() - rupee);
			temp = Math.round((temp * 100.0)*100D)/100D;
			long paisa = temp.longValue();
			String numtoword = "RUPEES " + EnglishNumberToWords.convert(rupee).toUpperCase() + " AND PAISA "
			           + EnglishNumberToWords.convert(paisa).toUpperCase() ;
			piv.setNumtoword(numtoword);//Number to words
			piv.setRate(gib.getRate());//rate
			piv.setTin(tin);
			piv.setSaleValue(gib.getSaleValue());//sale value
			piv.setVehicleNo(gib.getVehicleNo());//vehicle Number
			
			
			String txId = gib.getCylinderNo();
			
			ArrayList<Double> q = new ArrayList<>();
			ArrayList<Double> cap = new ArrayList<>();
			ArrayList<Integer> cylCount = new ArrayList<>();
			int ccount =0;
			String[] TxId = txId.split(";");
			for(String c:TxId) {
				Long l = Long.valueOf(c);
				if(l instanceof Long) {
					DealerCylinderTxn temp1 = cylinderS.dealerTxnCylinder(l);
					int count = temp1.getCylinderCount();
					cylCount.add(count);
					ccount +=count;
					cap.add(temp1.getCapacity());
					q.add(temp1.getQuantity());
				}
			}
			piv.setNoCylinder(ccount);
			piv.setQuantity(gib.getQuantity());//total Quantity
			piv.setCylQuant(q);//individual cylinder quantity
			piv.setCylCap(cap);//Cylinder capacity in 1,1.5,3,5,6,7 Cu.m
			piv.setCylCount(cylCount);
			Double saleValue = (gib.getSaleValue()*100D)/100D;
			piv.setSaleValue(saleValue);
			generateInvoice.printDealerInvoice(piv);//returns "SUCCESS" when .txt is generated
			
			
			result = new ModelAndView("ReprintInvoice","bil","Invoice re-printed for "
					+ "Dealer "+dealerName+"\n Bill No is "+gib.getBillNo()
					+" Invoice Amount = Rs."+gib.getBillValue());
		}
		return result;
	}
	
}
