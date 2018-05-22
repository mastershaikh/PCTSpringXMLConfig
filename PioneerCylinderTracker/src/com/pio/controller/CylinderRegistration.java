package com.pio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pio.model.Cylinder;
import com.pio.util.CylinderUtil;

@Controller
public class CylinderRegistration {
	@Autowired
	CylinderUtil cylUtil;
		
	@RequestMapping(value = "/CylinderRegister", method = RequestMethod.GET)
	public String viewRegister (final Model model) {
		model.addAttribute("cylinder", new Cylinder());
		return "CylinderRegister";
	}
	
	@RequestMapping(value = "/CylinderRegister", method = RequestMethod.POST)
	public ModelAndView processRegister (
	@ModelAttribute("cylinder") final Cylinder cylinder) {
			ModelAndView result = new ModelAndView("CylinderRegister", "fail",
					"Registration Failed");
				
			String exist = cylUtil.checkCylinder(cylinder.getCylinderId());
			if(exist.equals("SUCCESS")) {
				
				cylinder.setBillGenerated("N");
					String status = cylUtil.register(cylinder);
					if(status.equals("SUCCESS")){
				result = new ModelAndView("CylinderRegister", "success",
						"Cylinder Registration Successful! CylinderID is:"
								+ cylinder.getCylinderId());
				}
			}else {
				
				String[] c = exist.split(";");
				Long cid = Long.valueOf(c[0]);
				Double capacity = Double.valueOf(c[1]);
				String cylinderType = c[2];
				String dealer = c[3];
				result = new ModelAndView("CylinderRegister", "fail",
						"Cylinder Already registered with details:"
						+"Cylinder Id = "+cid+" capacity = "+capacity
						+"cylinder Type = "+cylinderType
						+ "In possession with Dealer ID = "+dealer);
			}
			return result;
		}
	@RequestMapping(value = "/CylinderDamage", method = RequestMethod.GET)
	public String viewDamage (final Model model) {
		model.addAttribute("cylinder", new Cylinder());
		return "CylinderDamage";
	}
	
	@RequestMapping(value = "/CylinderDamage", method = RequestMethod.POST)
	public ModelAndView processDamage (
	@ModelAttribute("cylinder") final Cylinder cylinder) {
			ModelAndView result = new ModelAndView("CylinderDamage", "fail",
					"Registration Failed");
			String exist = cylUtil.checkCylinder(cylinder.getCylinderId());
			if(!exist.equals("SUCCESS")) {
			String[] c = exist.split(";");
			Double capacity = Double.valueOf(c[1]);
			cylinder.setCapacity(capacity);
			String cylinderType = c[2];
			cylinder.setCylinderType(cylinderType);
			String remark = c[3]+" "+cylinder.getRemark();
			cylinder.setDamage("Y");
			cylinder.setBillGenerated("N");
			cylinder.setRemark(remark);
					String status = cylUtil.register(cylinder);
					if(status.equals("SUCCESS")){
				result = new ModelAndView("CylinderDamage", "success",
						"Cylinder Damage Entry Done! Remark is:"
								+ cylinder.getRemark());
				}
			}else {
				result = new ModelAndView("CylinderDamage", "fail",
						"Invalid Cylinder! Check again");
			}
			return result;
		}
}
