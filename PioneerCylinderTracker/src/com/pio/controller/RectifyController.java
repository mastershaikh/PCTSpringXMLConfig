package com.pio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pio.services.RectifyService;

@Controller
public class RectifyController {

	@Autowired
	private RectifyService recS;
	
	@RequestMapping(value="/RectifyCylinder", method = RequestMethod.GET)
	public String recCylinder () {
		return "CylinderCorrection";
	}
	
	@RequestMapping(value="/RectifyCylinder", method = RequestMethod.POST)
	public ModelAndView processrecCylinder (@RequestParam(value="wrongCid") String wrongCid,
			@RequestParam(value="rightCid") String rightCid,@RequestParam(value="dealerId") String dealerId) {
		ModelAndView result = new ModelAndView("CylinderCorrection","done","Try again");
		
		String status = recS.rectifyCylinder(wrongCid,rightCid,dealerId);
		if(status.equals("SUCCESS")) {
			result = new ModelAndView("CylinderCorrection","done","Correction Done! check preview Invoice");
		}
		return result;
	}
	
	@RequestMapping(value="/RectifyLorry", method = RequestMethod.GET)
	public String recLorry () {
		return "LorryCorrection";
	}
	
	@RequestMapping(value="/RectifyLorry", method = RequestMethod.POST)
	public ModelAndView processLorry (@RequestParam(value="billNo") String billNo, @RequestParam(value="lorryNo")
	String lorryNo) {
		ModelAndView result = new ModelAndView("LorryCorrection","done","Try again");
		String status = recS.rectifyLorry(billNo,lorryNo);
		if(status.equals("SUCCESS")) {
			result = new ModelAndView("LorryCorrection","done","Lorry Correction done!");
		}else if(status.equals("FAIL")) {
			result = new ModelAndView("LorryCorrection","fail","Invalid Invoice Number or Invoice not generated");
		}
		return result;
	}
	
	
}
