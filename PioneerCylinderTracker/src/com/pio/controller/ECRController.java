package com.pio.controller;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pio.model.GenerateECRBean;
import com.pio.services.ECRService;
import com.pio.util.DealerUtil;
import com.pio.vo.PrintECRVO;

@Controller
public class ECRController {
	
	@Autowired
	private ECRService ecrS;
	
	@Autowired
	private DealerUtil dealerU;
	
	/*@RequestMapping(value="/GenerateECR", method = RequestMethod.GET)
	public String genECR (final Model model) {
		model.addAttribute("ecr", new GenerateECRBean());
		return "GenerateECR";
	}*/
	
	@RequestMapping(value = "/GenerateECR", method = RequestMethod.POST)
	public ModelAndView generateECR (HttpSession session,@RequestParam(value="dealerId")
	String dealerId,@RequestParam(value="lorryNo")String lorryNo) {
		ModelAndView result = new ModelAndView("GenerateECR", "MSG",
				"Transactions Details");
		String ecrGenStatus = ecrS.checkECRGenStatus(dealerId,lorryNo);
		if(ecrGenStatus.equals("NOECR")) {
			GenerateECRBean ecr = new GenerateECRBean();
			ecr.setEcrDate(Timestamp.valueOf(LocalDateTime.now()));
			ecr.setLorryNo(lorryNo);
			ecr.setDealerId(dealerId);
			Long ecrno = ecrS.generateECR(ecr);//Generates ECR no and saves in ECR table
			ecr.setEcrNo(ecrno);
			String status = ecrS.updateECR(ecr);
		if(status.equals("SUCCESS")) {//to print ECR receipt
			String ecrPrint = ecrS.getECR(ecrno);
			PrintECRVO pecr = new PrintECRVO();
			
			String dealer = dealerU.dealerDetails(dealerId);
			String[] d = dealer.split(";");
			String dealerName = d[1];
			String address = d[5];
			String contactNo = d[6];
			pecr.setAddress(address);
			pecr.setContactNo(contactNo);
			pecr.setDealerName(dealerName);
			
			String[] e = ecrPrint.split("#");
			String cnos = e[0];
			lorryNo = e[1];
			pecr.setEcrNo(ecr.getEcrNo().toString());
			pecr.setLorryNo(lorryNo);
			LocalDate ecrDate = Timestamp.valueOf(e[2]).toLocalDateTime().toLocalDate();
			pecr.setEcrDate(ecrDate);
			String[] cno = cnos.split(";");
			ArrayList<Long> cn = new ArrayList<Long>();
			for(String c:cno) {
				Long cyl = Long.valueOf(c);
				if(cyl instanceof Long)
				cn.add(cyl);
			}
			pecr.setCylinderList(cn);
			
			status = ecrS.printECR(pecr);
			if(status.equals("SUCCESS"))
			result = new ModelAndView("GenerateECR", "ecrgenerated",
					"ECR Generated Successfully with ECR no = "+pecr.getEcrNo());
		}
		}
		else if(ecrGenStatus.equals("FAIL")){
			
			result = new ModelAndView("GenerateECR", "fail",
					"DealerId or LorryNo is invalid! Try again");
		}
		else result = new ModelAndView("GenerateECR", "exist",
				"ECR Already Generated. Check ECR Nos"+ecrGenStatus);
		
		return result;
	}
}
