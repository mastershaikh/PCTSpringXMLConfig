package com.pio.controller;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pio.util.DealerUtil;
import com.pio.util.SalesUtil;

@Controller
public class SalesController {

	@Autowired
	private SalesUtil saleU;
	
	@Autowired
	private DealerUtil dealerU;
	
	@RequestMapping(value="/dealerSales",method=RequestMethod.GET)
	public String getDealerWise(){
		return "DealerSales";
	}
	
	@RequestMapping(value="/dealerSales", method=RequestMethod.POST)
	public ModelAndView processDealerWise(@RequestParam(value="fromDate") String fromDate,
			@RequestParam(value="toDate") String toDate, @RequestParam(value="dealerId")
	String dealerId, HttpSession session) throws ParseException{
		ModelAndView result = new ModelAndView("DealerSalesDisp","fail","Try again!");
		String dstatus = dealerU.login(dealerId);
		
		if(!dstatus.equals("FAIL")){
			Date frDate = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
			Date tDate = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
			String status = saleU.fetchDealerSales(frDate,tDate,dealerId);
			if(!status.isEmpty()){
				String[] s = status.split("#");
				session.setAttribute("dealerId", dealerId);
				//Bill Nos starts here
				String[] bNos = s[0].split(";");
				ArrayList<Long> billNo = new ArrayList<>();
				for(String c:bNos){
					Long l = Long.valueOf(c);
					if(l instanceof Long){
						billNo.add(l);
					}
				}
				session.setAttribute("billNo", billNo);
				//Bill Nos ends here
				
				//Cylinder type starts here
				String[] cType = s[1].split(";");
				ArrayList<String> cylinderType = new ArrayList<>();
				for(String c:cType){
					cylinderType.add(c);
				}
				session.setAttribute("cylinderType", cylinderType);
				//Cylinder type ends here
				
				String[] tCyl = s[2].split(";");
				ArrayList<Integer> cylinders = new ArrayList<>();
				int totalCylinders =0;
				for(String c:tCyl){
					Integer i = Integer.valueOf(c);
					if(i instanceof Integer){
						cylinders.add(i);
						totalCylinders +=i;
					}
				}
				session.setAttribute("cylinders", cylinders);
				session.setAttribute("totalCylinders", totalCylinders);
				
				String[] q = s[3].split(";");
				ArrayList<Double> quantity = new ArrayList<>();
				Double totalQuantity = 0.0;
				for(String c:q){
					Double d = Double.valueOf(c);
					if(d instanceof Double){
						quantity.add(d);
						totalQuantity +=d;
					}
				}
				session.setAttribute("quantity", quantity);
				session.setAttribute("totalQuantity", totalQuantity);
				
				String[] sv = s[4].split(";");
				ArrayList<Double> saleValue = new ArrayList<>();
				Double totalSaleValue =0.0;
				for(String c:sv){
					Double d = Double.valueOf(c);
					if(d instanceof Double){
						saleValue.add(d);
						totalSaleValue +=d;
					}
				}
				totalSaleValue = Math.round(totalSaleValue*100D)/100D;
				session.setAttribute("saleValue", saleValue);
				session.setAttribute("totalSaleValue", totalSaleValue);
				
				String[] cgst = s[5].split(";");
				ArrayList<Double> CGST = new ArrayList<>();
				Double totalCGST =0.0;
				for(String c:cgst){
					Double d = Double.valueOf(c);
					if(d instanceof Double){
						CGST.add(d);
						totalCGST +=d;
					}
				}
				totalCGST = Math.round(totalCGST*100D)/100D;
				session.setAttribute("CGST", CGST);
				session.setAttribute("totalCGST", totalCGST);
				
				String[] sgst = s[6].split(";");
				ArrayList<Double> SGST = new ArrayList<>();
				Double totalSGST =0.0;
				for(String c:sgst){
					Double d = Double.valueOf(c);
					if(d instanceof Double){
						SGST.add(d);
						totalSGST +=d;
					}
				}
				totalSGST = Math.round(totalSGST*100D)/100D;
				session.setAttribute("SGST", SGST);
				session.setAttribute("totalSGST", totalSGST);
				
				String[] bv = s[7].split(";");
				ArrayList<Double> billValue = new ArrayList<>();
				Double totalBillValue =0.0;
				for(String c:bv){
					Double d = Double.valueOf(c);
					if(d instanceof Double){
						billValue.add(d);
						totalBillValue +=d;
					}
				}
				totalBillValue = Math.round(totalBillValue*100D)/100D;
				session.setAttribute("billValue", billValue);
				session.setAttribute("totalBillValue", totalBillValue);
				
				String[] bd = s[8].split(";");
				ArrayList<LocalDate> billDate = new ArrayList<>();
				for(String c:bd){
					Timestamp d = Timestamp.valueOf(c);
					if(d instanceof Timestamp){
						LocalDate ld = d.toLocalDateTime().toLocalDate();
						billDate.add(ld);
					}
				}
				session.setAttribute("billDate", billDate);
							
				result = new ModelAndView("DealerSalesDisp","success","Total invoice for dealer "+dealerId+ "from "+fromDate+"to "+toDate
						+"is = Rs."+totalBillValue);
			}
			
		}
		
		return result;
	}
	
	@RequestMapping(value="/companySales",method=RequestMethod.GET)
	public String getCompanySales(){
		return "CompanySales";
	}
	
	@RequestMapping(value="/companySales", method=RequestMethod.POST)
	public ModelAndView processCompanySales(@RequestParam(value="fromDate") String fromDate,
			@RequestParam(value="toDate") String toDate){
		ModelAndView result = new ModelAndView("CompanySales","fail","Try again!");
		
		
		return result;
	}
}