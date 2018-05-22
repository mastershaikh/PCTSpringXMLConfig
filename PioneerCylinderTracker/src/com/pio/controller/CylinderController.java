package com.pio.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pio.model.Cylinder;
import com.pio.util.CylinderUtil;

@Controller
public class CylinderController {
	
	@Autowired
	CylinderUtil cylinderU;
	
	@RequestMapping(value="/FindCylinder", method = RequestMethod.GET)
	public String buyCylinder (final Model model) {
		model.addAttribute("find", new Cylinder());
		return "CylinderFind";
	}
	@RequestMapping(value = "/FindCylinder", method = RequestMethod.POST)
	public ModelAndView processReturn (@RequestParam(value="cid")
	Long cylId,
	@ModelAttribute("find") final Cylinder cylinder, HttpSession session) {
			ModelAndView result = new ModelAndView("CylinderFind", "",
					"");
			String status = cylinderU.findCylinder(cylId);
			System.out.println("status in CylinderController "+status);
					if(status.equals("FAIL")){
				result = new ModelAndView("CylinderFind", "company",
						"This cylinder "+cylId +" is inside company");
				}
					else if(status.equals("INVALID")) {
						result = new ModelAndView("CylinderFind", "invalid",
								"This cylinder number is INVALID");
					}
					else if(status.startsWith("DAMAGE")){
						result = new ModelAndView("CylinderFind", "dam",
								"This cylinder is damaged, remark = "+status.substring(6));
					}
					else {
						result = new ModelAndView("CylinderFind", "dealer",
								"This cylinder is in possession with Dealer "+status);
					}
			return result;
		}
	
}