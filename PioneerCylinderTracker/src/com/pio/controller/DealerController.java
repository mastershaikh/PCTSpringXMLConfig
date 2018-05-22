package com.pio.controller;


import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pio.model.DealerCylinderTxn;
import com.pio.model.DealerDetails;
import com.pio.services.CylinderService;
import com.pio.services.GenerateInvoice;
import com.pio.util.DealerUtil;

@Controller
public class DealerController {

	@Autowired
	private DealerUtil dealerU;
	
	@Autowired
	private GenerateInvoice gi;
	
	@Autowired
	private CylinderService cylinderS;
	
	@RequestMapping(value = "/dealerLists", method = RequestMethod.GET)
	public ModelAndView listDealer (final Model model) {
		TreeMap<String,String> result = new TreeMap<String,String>();
		result = dealerU.allDealers();
		ModelAndView mod = new ModelAndView("DealerList","dealerList",result);
		return mod;
	}

	@RequestMapping(value = "/dealerRegistration", method = RequestMethod.GET)
	public String dealerRegister (final Model model) {
		model.addAttribute("dealer", new DealerDetails());
		return "DealerRegister";
	}
	
	@RequestMapping(value = "/dealerRegistration", method = RequestMethod.POST)
	public ModelAndView processRegister (
	@ModelAttribute("dealer") final DealerDetails dealer){
			ModelAndView result = new ModelAndView("DealerRegister", "MSG",
					"Registration Failed");
					dealer.setAirNo((long)0);
					dealer.setN2No((long)0);
					dealer.setO2No((long)0);
					String status = dealerU.register(dealer);
					System.out.println("status = "+status);
					if(!status.isEmpty()){
										result = new ModelAndView("DealerRegister", "MSG",
										"<center>Registration Successful!</center><br><center> DealerID is:"
												+ dealer.getDealerId() + " </center>");
				}
			return result;
		}
		
		@RequestMapping(value = "/dealerDetails", method = RequestMethod.GET)
		public ModelAndView viewDealer (final Model model) {
			model.addAttribute("dealer", new DealerDetails());
			TreeMap<String,String> result = new TreeMap<String,String>();
			result = dealerU.allDealers();
			ModelAndView mod = new ModelAndView("GetDealer","dealerList",result);
			return mod;
		}
		
		@RequestMapping(value = "/dealerDetails", method = RequestMethod.POST)
		public ModelAndView processDealer (
				@RequestParam(value="dealerId") String dealerId, HttpSession session){
				ModelAndView result = new ModelAndView("GetDealer", "fail",
						"Dealer Details not found! \n Check in Dealer List");
				String valid = dealerU.login(dealerId);
				if(!valid.equals("FAIL")) {
				String dealer = dealerU.dealerDetails(dealerId);
				dealerU.updateCylinderCount(dealerId);
				String[] d = dealer.split(";");
				session.setAttribute("dealerId",d[0]);
				session.setAttribute("dealerName", d[1]);
				session.setAttribute("gstin", d[2]);
				session.setAttribute("rateo2", d[3]);
				session.setAttribute("raten2", d[4]);
				session.setAttribute("address", d[5]);
				session.setAttribute("contactNo", d[6]);
				session.setAttribute("o2", d[7]);
				session.setAttribute("n2", d[8]);
				session.setAttribute("dateOfRegistration",d[9].substring(0, 10));
				
				long total = Long.parseLong(d[7])+Long.parseLong(d[8]);
				
				Double bill = gi.billTillDate(dealerId);
				bill = Math.round(bill*100D)/100D;
				session.setAttribute("totalCylindersTaken", "OXYGEN ="+d[7]+"\nNITROGEN="+d[8]+"\ntotal="+total);

				session.setAttribute("bill", bill);
						if(!dealer.equals("FAIL"))
		result = new ModelAndView("DealerDetails", "MSG",
											dealer);
				}
		return result;
			}
		
		@RequestMapping(value = "/DealerCylinderBuy", method = RequestMethod.GET)//work to be done
		public String buyDealer () {
			return "DealerCylinderBuy";
		}
		
		@RequestMapping(value = "/DealerCylinderBuy", method = RequestMethod.POST)
		public ModelAndView processPurchase (@RequestParam(value="lorryNo") String lorryNo,
				@RequestParam(value="dealerName") String dealerName,@RequestParam(value="dealerId") String dealerId,
				@RequestParam(value="cylinderType") String cylinderType,@RequestParam(value="capacity") Double capacity, 
				@RequestParam(value="cylinderCount") Integer cylinderCount,
		/*@ModelAttribute("buy") final DealerCylinderTxn dct,*/ HttpSession session) {
				ModelAndView result = new ModelAndView("DealerCylinderTxn", "MSG",
						"Transaction Failed");
				DealerCylinderTxn dct = new DealerCylinderTxn();
				dct.setCapacity(capacity);
				dct.setCylinderType(cylinderType);
				dct.setDealerId(dealerId);
				dct.setCylinderCount(cylinderCount);
				session.setAttribute("lorryNo", lorryNo);
				session.setAttribute("dealerId", dealerId);
				dct.setDealerId(dealerId);
				if(dealerName instanceof String) {
				String status = cylinderS.dealerCylinderBuy(dct);
				if(status.equals("SUCCESS")){
					result = new ModelAndView("DealerCylinderBuy", "success",
							"Purchase Successful! TransactionID is:"
									+ dct.getTransactionId() + "Bill Value without TAX= "+dct.getBillValue());
					}
				else if(status.equals("FAIL")) {
					result = new ModelAndView("DealerCylinderBuy","fail",
							"<center>Purchase Successful!</center><br><center> TransactionID is:"
									+ dct.getTransactionId() + " </center>");
				}
				}
				else {
					result = new ModelAndView("DealerCylinderBuy", "stats","Dealer Invalid");
				}
				return result;
			}
		
		
}
