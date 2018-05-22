package com.pio.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pio.model.CylinderTransactions;
import com.pio.model.ECRTransactionBean;
import com.pio.services.CylinderService;
import com.pio.services.ECRService;
import com.pio.util.CylinderUtil;
import com.pio.util.DealerUtil;

@Controller
public class CylinderTransactionController {

	@Autowired
	private CylinderService cylinderS;
	
	@Autowired
	private DealerUtil dealerU;	
		
	@Autowired
	private ECRService ecrS;
	
	@Autowired
	private CylinderUtil cylinderU;
	
	@RequestMapping(value="/CylinderBuy", method = RequestMethod.GET)
	public String buyCylinder (final Model model) {
		model.addAttribute("buy", new CylinderTransactions());
		return "CylinderBuy";
	}
	
	@RequestMapping(value = "/CylinderBuy", method = RequestMethod.POST)
	public ModelAndView processPurchase (@RequestParam(value="cylinderId") String cnos,@RequestParam(value="lorryNo") String lorryNo,
			HttpSession session) {
			ModelAndView result = new ModelAndView("CylinderBuy", "MSG",
					"Registration Failed");
			String dealerName = (String)session.getAttribute("dealerName");
			String dealerId = (String) session.getAttribute("dealerId");
			System.out.println(dealerId);
			if(dealerName instanceof String) {
			session.setAttribute("dealerName", dealerName);
			session.setAttribute("dealerId", dealerId);
			session.setAttribute("lorryNo", lorryNo);
			session.setAttribute("CorD", "Y");
			
			String c = cnos.trim().replaceAll("\\s{2,}", " ");
			String[] cno = c.split(" ");
			ArrayList<Long> cylinderNumbers = new ArrayList<>();
			for(String s:cno){
				Long l = Long.valueOf(s);
				if(l instanceof Long){
					cylinderNumbers.add(l);
				}
			}
			
			TreeSet<String> successCylinder = new TreeSet<>();
			TreeSet<String> noRegisterno = new TreeSet<>();
			TreeSet<String> differentDealer = new TreeSet<>();
			ArrayList<Long> cylNumbers = new ArrayList<>();
			ArrayList<String> cylType= new ArrayList<>();
			for(Long cylId:cylinderNumbers) {
				String usageAndType = cylinderU.getUsageAndType(cylId);
					String[] us = usageAndType.split(";");
					String usageStatus = us[1];
						if(usageStatus.equals("0")) {
							cylType.add(us[0]);
							cylNumbers.add(cylId);
							String gasType ="";
							if(us[0].contains("OXYGEN")) {
								gasType="Oxygen";
							}else if(us[0].contains("NITROGEN")) {
								gasType="Nitrogen";
							}else {
								gasType="Air";
							}	
							successCylinder.add(cylId+" = "+ gasType+"\n");
						}
						else if(usageStatus.equals("NEW")) {
							noRegisterno.add(String.valueOf(cylId));
						}else {
							differentDealer.add(cylId+" = "+usageStatus + "\n");
						}
						
			}
			
			CylinderTransactions cylinder[] = new CylinderTransactions[cylNumbers.size()];
			for(int i=0;i<cylNumbers.size();i++) {
				cylinder[i] = new CylinderTransactions();
			}
			for(int i =0;i<cylNumbers.size();i++) {
				cylinder[i].setCylinderId(cylNumbers.get(i));
				cylinder[i].setCylinderType(cylType.get(i));
				cylinder[i].setDealerId(dealerId);
				}
			cylinderS.batchPurchase(cylinder);
			dealerU.updateCylinderCount(dealerId);
			
			List<String> lines = Arrays.asList("Successfully Added Cylinders "+successCylinder,"Not Registered or Damaged "+noRegisterno,
					"Different Dealer"+differentDealer);
			Path file = Paths.get("E:\\PIONEER\\Purchase\\"+dealerId+".txt");
			
			try {
				Files.write(file, lines, Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = new ModelAndView("PreviewBill", "success","Cylinders Added successfully! Check Purchase Folder for details");
			}
			else {
				result = new ModelAndView("CylinderBuy", "nodeal","Visit Dealer Details and enter Dealer ID to buy or return cylinder");
			}
			return result;
		}
	@RequestMapping(value = "/CylinderCount", method = RequestMethod.GET)
	@ResponseBody
	public long countCylinder (@RequestParam(value = "cylinderType") String cylinderType) {
			long result = cylinderS.countCylinders(cylinderType);
			return result;
		}
	@RequestMapping(value = "/CylinderType", method = RequestMethod.GET)
	@ResponseBody
	public String typeOfCylinder (@RequestParam(value = "cylinderId") Long cylinderId) {
		String result = cylinderS.typeOfCylinders(cylinderId);
			return result;
		}
		
	
	@RequestMapping(value = "/CylinderReturn", method = RequestMethod.GET)
	public String returnCylinder (final Model model) {
		return "CylinderReturn";
	}
	
	@RequestMapping(value = "/CylinderReturn", method = RequestMethod.POST)
	public ModelAndView processReturn (@RequestParam(value="cid")
	String cylId,@RequestParam(value="lorryNo")
	String lorryNo,@RequestParam(value="dealerId") String dealerId, HttpSession session) {
			ModelAndView result = new ModelAndView("CylinderReturn", "MSG",
					"Registration Failed");
			//String dealerId = cylinderS.getDealerFromCylinder(cylId);
			String c = cylId.trim().replaceAll("\\s{2,}", " ");
			String[] cno = c.split(" ");
			ArrayList<Long> cylinderNumbers = new ArrayList<>();
			for(String s:cno){
				Long l = Long.valueOf(s);
				if(l instanceof Long){
					cylinderNumbers.add(l);
				}
			}
			
			String validDealer = dealerU.login(dealerId);
			TreeSet<String> differentDealer = new TreeSet<>();
			TreeSet<Long> noBillCno = new TreeSet<>();
			TreeSet<String> noRegisterno = new TreeSet<>();
			TreeSet<String> oldCylinder = new TreeSet<>();
			if(validDealer.equals(dealerId)){
				ArrayList<Long> cylNumbers = new ArrayList<>();
				for(Long a:cylinderNumbers) {					
						String[] us = cylinderU.getUsageAndType(a).split(";");
							if(us[1].equals(dealerId)) {
								cylNumbers.add(a);
							}
							else if(us[1].equals("NEW")) {
								noRegisterno.add(String.valueOf(cylId));
							}else {
								differentDealer.add(cylId+" = "+us[1] + "\n");
							}
							
				}	
					String status = cylinderS.returnCylinderBatch(cylNumbers,dealerId);
					
					if(status.equals("SUCCESS")){
					ECRTransactionBean[] ecr = new ECRTransactionBean[cylNumbers.size()];
					for(int i=0;i<cylNumbers.size();i++) {
					ecr[i] = new ECRTransactionBean();
					}
					
					for(int i =0;i<cylNumbers.size();i++) {
						ecr[i].setCylinderId(cylNumbers.get(i));
						ecr[i].setDealerId(dealerId);
						ecr[i].setLorryNo(lorryNo);
						ecr[i].setEcrStatus("N");
						}
					ecrS.updateECRTxBatch(ecr);//updates ECRTx table after returning cylinder
					}
				dealerU.updateCylinderCount(dealerId);//work done
				
				List<String> lines = Arrays.asList("Different Dealers "+differentDealer,"NoBill "+noBillCno,"No Register "+noRegisterno,
						"Old Cylinder "+oldCylinder);
				Path file = Paths.get("E:\\PIONEER\\Return\\"+dealerId+".txt");
				try {
					Files.write(file, lines, Charset.forName("UTF-8"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				session.setAttribute("dealerId", dealerId);
				session.setAttribute("lorryNo", lorryNo);
				result = new ModelAndView("GenerateECR","success","Cylinder Returned successfully! Generate ECR now");
			}
				else{
						result = new ModelAndView("CylinderReturn", "bad","Dealer Id is incorrect! Check again");
					}
			
			return result;
	}	
}