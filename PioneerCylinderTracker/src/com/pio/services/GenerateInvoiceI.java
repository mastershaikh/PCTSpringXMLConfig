package com.pio.services;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pio.dao.CylinderTransactionDAO;
import com.pio.dao.GenerateInvoiceDAO;
import com.pio.model.GenerateInvoiceBean;
import com.pio.vo.PrintInvoiceVO;

@Service
public class GenerateInvoiceI implements GenerateInvoice {

	@Autowired
	private GenerateInvoiceDAO giDAO;
	
	@Autowired
	private CylinderTransactionDAO txDAO;
	
	public Long generateID(){
		Long sequence = txDAO.findBillID();
		return sequence;
	}
	
	String space3=String.format("%"+3+"s", "");
	String space4=String.format("%"+4+"s", "");
	String space5=String.format("%"+5+"s", "");
	String space6=String.format("%"+6+"s", "");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
	Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
	@Override
	public String generateInvoice(GenerateInvoiceBean gi) {
		Long sequence = generateID();
		String result = "FAIL";
		if(sequence instanceof Long) {
		gi.setBillNo(sequence);
		Timestamp billDate = Timestamp.valueOf(LocalDateTime.now());
		gi.setBillDate(billDate);
		gi.setOutstanding("N");
		result = giDAO.generateInvoice(gi);
		}
		return result;
	}
	
	private static String spaceCheck(String num) {
		int nl = num.length();
		int len = 7-nl;
		String space = String.format("%"+len+"s", "");
		return space;
	}
	
	@Override
	public String printInvoice(PrintInvoiceVO piv) {
		String result ="FAIL";
		String dealerName = piv.getCustomerName().toUpperCase();
		int dnlength = dealerName.length();
		String daFl = "",daSl="";
		int maxAdd = piv.getAddress().length();
		if(maxAdd>50) {
			daFl = piv.getAddress().substring(0,50);
			daSl = piv.getAddress().substring(50);
		}else {
			daFl = piv.getAddress();
		}
			
		int dalength = daFl.length();
		String spaceDN = " ",spaceDA=" ";
		for(int i = 0;i<(66-dnlength);i++) {
		spaceDN += " ";
		}
		for(int i = 0;i<(66-dalength);i++) {
			spaceDA += " ";
			}
		String billNoSpace = String.format("%"+67+"s", "");

		String saleSpace = String.format("%"+41+"s", "");
		
		String nextCyl = String.format("%"+26+"s", "");
		
		String tinSpace ="";
		if(piv.getTin().length()<17) {
			tinSpace = String.format("%"+40+"s", "");
		}else {
			tinSpace = String.format("%"+25+"s", "");
		}
		try {
		Collections.sort(piv.getCylList6());
		Collections.sort(piv.getCylList7());
		}catch(NullPointerException npe){
		}
		StringBuilder cylList7=new StringBuilder();
		StringBuilder cylList6=new StringBuilder();
		int q6=0,q7=0;
		String cyl6 ="",cyl7="";
		if(piv.getCylList6() instanceof ArrayList)
			if(!piv.getCylList6().isEmpty()) {
				q6 = piv.getCylList6().size();
				if(q6>10) {
				int a = (int)Math.floor((double)q6/10.0);
				int b = q6%10;
				int i =0;
				for(int j = 0;j<a;j++) {
					cylList6.append("6.00 ");
				for(int m=0;m<10;m++) {
					cyl6 = String.valueOf(piv.getCylList6().get(i));
					if(m>=4) {
						cylList6.append(" ");
					}
					if(m%2==0) {
						cylList6.append(" ");
					}
					cylList6.append((cyl6)+spaceCheck(cyl6));
					i++;
				}
				cylList6.append(System.getProperty("line.separator"));
				cylList6.append(System.getProperty("line.separator"));
				
				}
				if(b!=0) {
					cylList6.append("6.00 ");
					for(int j=0;j<b;j++,i++) {
						if(j>=4) {
							cylList6.append(" ");
							
						}
						if(j%2==0) {
							cylList6.append(" ");
						}
						cyl6 = String.valueOf(piv.getCylList6().get(i));
						cylList6.append(cyl6+spaceCheck(cyl6));
					}
				}
				}else {
					cylList6.append("6.00 ");
					for(int i =0;i<q6;i++) {
						if(i>=4) {
							cylList6.append(" ");
							
						}
						if(i%2==0) {
							cylList6.append(" ");
						}
					cyl6 = String.valueOf(piv.getCylList6().get(i));
					cylList6.append(cyl6+spaceCheck(cyl6));
					}
				}
				cylList6.append(System.getProperty("line.separator"));
				}
		
		if(piv.getCylList7() instanceof ArrayList)
		if(!piv.getCylList7().isEmpty()) {
			
		q7 = piv.getCylList7().size();
		
		if(q7>10) {
			int a = (int)Math.floor((double)q7/10.0);
			int b = q7%10;
			int i =0;
			for(int j = 0;j<a;j++) {
				cylList7.append("7.00 ");
			for(int m=0;m<10;m++) {
				if(m>=4) {
					cylList7.append(" ");					
				}
				if(m%2==0) {
					cylList7.append(" ");
				}
				cyl7 = String.valueOf(piv.getCylList7().get(i));
				cylList7.append(cyl7+spaceCheck(cyl7));
				i++;
			}
			cylList7.append(System.getProperty("line.separator"));
			cylList7.append(System.getProperty("line.separator"));
		
		}
		if(b!=0) {
			cylList7.append("7.00 ");
			for(int j=0;j<b;j++,i++) {
				if(j>=4) {
					cylList7.append(" ");
					
				}
				if(j%2==0) {
					cylList7.append(" ");
				}
				cyl7 = String.valueOf(piv.getCylList7().get(i));
				cylList7.append(cyl7+spaceCheck(cyl7));
			}
		}
		}else {
			cylList7.append("7.00 ");
			for(int i=0;i<q7;i++) {
				if(i>=4) {
					cylList7.append(" ");
				}
				if(i%2==0) {
					cylList7.append(" ");
				}
				cyl7 = String.valueOf(piv.getCylList7().get(i));
				cylList7.append(cyl7+spaceCheck(cyl7));
			}
		}
		cylList7.append(System.getProperty("line.separator"));
		}
		StringBuilder capline =new StringBuilder();
		if(q7!=0) {		
			capline.append(nextCyl+q7+space6+"7.00 Cu.m \t"+((double)7.0*q7));
			capline.append(System.getProperty("line.separator"));
		}
		
		if(q6!=0) {
			capline.append(nextCyl+q6+space6+"6.00 Cu.m \t"+((double)6.0*q6));
			capline.append(System.getProperty("line.separator"));
		}
		
		StringBuilder cylListline = new StringBuilder();
		
		if(!cylList7.toString().isEmpty()) {
			cylListline.append(cylList7);
			cylListline.append(System.getProperty("line.separator"));
		}
		if(!cylList6.toString().isEmpty()) {
			cylListline.append(cylList6);
			cylListline.append(System.getProperty("line.separator"));
		}
		
		LocalDate dt = piv.getDate();
		String date = dt.format(formatter);
		List<String> lines = Arrays.asList("","","",billNoSpace+piv.getBillNo(),
				dealerName+spaceDN+date,
				daFl+spaceDA+piv.getVehicleNo(),daSl+" Contact No "+piv.getContactNo(),"",
				tinSpace+"TIN# "+piv.getTin(),"","","","",piv.getGasType(),capline.toString(),"",
				nextCyl+(q6+q7)+"\t\t\t"+piv.getQuantity()+"\t"+space5+format.format(piv.getRate()),
				"","","",saleSpace+"Sales Value "+"\t\t"+space3+format.format(piv.getSaleValue()),
				saleSpace+"ADD: CGST @ 9%"+"\t\t"+space5+format.format(piv.getCGST()),saleSpace+"ADD: SGST @ 9%"
				+"\t\t"+space5+format.format(piv.getSGST()),"","",saleSpace+"Bill - Value"
				+"\t\t"+space3+format.format(piv.getBillValue()),"", 
				"",piv.getNumtoword()+" ONLY","","","","","",cylListline.toString());
		Path file = Paths.get("E:\\PIONEER\\Bill\\"+piv.getBillNo()+".txt");
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
	public String updateBill(Long billNo) {
		String result = "FAIL";
		result = giDAO.makeInvoice(billNo);
		return result;
	}


@Override
public String finalUpdateInvoice(GenerateInvoiceBean gi) {
	String result = "";
	result = giDAO.generateDealerInvoice(gi);
	return result;
}
@Override
public String checkInvoiceGenStatus(GenerateInvoiceBean gi) {
	String result ="";
	result = giDAO.checkInvoiceGenStatus(gi);
	return result;
}
@Override
public Double billTillDate(String dealerId) {
	Double result =0.0;
	result = giDAO.billTillDate(dealerId);
	return result;
}
@Override
public String generateDealerInvoice(GenerateInvoiceBean gi) {
	Long sequence = generateID();
	String result = "FAIL";
	if(sequence instanceof Long) {
	gi.setBillNo(sequence);
	Timestamp billDate = Timestamp.valueOf(LocalDateTime.now());
	gi.setBillDate(billDate);
	gi.setOutstanding("N");
	result = giDAO.generateDealerInvoice(gi);
	}
	return result;
}
@Override
public String printDealerInvoice(PrintInvoiceVO piv) {
	String result ="FAIL";

	String dealerName = piv.getCustomerName().toUpperCase();
	int dnlength = dealerName.length();
	String daFl = "",daSl="";
	int maxAdd = piv.getAddress().length();
	if(maxAdd>50) {
	daFl = piv.getAddress().substring(0,50);		
	daSl = piv.getAddress().substring(50);
	}else {
		daFl = piv.getAddress();
	}
	int dalength = daFl.length();
	String spaceDN = " ",spaceDA=" ";
	for(int i = 0;i<(66-dnlength);i++) {
	spaceDN += " ";
	}
	for(int i = 0;i<(66-dalength);i++) {
		spaceDA += " ";
		}
	String billNoSpace = String.format("%"+67+"s", "");

	String saleSpace =String.format("%"+40+"s", "");
	
	String nextCyl = String.format("%"+26+"s", "");
	
	String tinSpace ="";
	if(piv.getTin().length()<17) {
		tinSpace = String.format("%"+40+"s", "");
	}else {
		tinSpace = String.format("%"+25+"s", "");
	}
	
	StringBuilder cylList7=new StringBuilder();
	StringBuilder cylList6=new StringBuilder();
	StringBuilder cylList5=new StringBuilder();
	StringBuilder cylList3=new StringBuilder();
	StringBuilder cylList1_5=new StringBuilder();
	StringBuilder cylList1=new StringBuilder();
	double q1=0.0,q1_5=0.0,q3=0.0,q5=0.0,q6=0.0,q7=0.0;
	int c1=0,c1_5=0,c3=0,c5=0,c6=0,c7=0;

	for(int i=0;i<piv.getCylCap().size();i++) {
		
	if(piv.getCylCap().get(i)==7.00) {
		q7 = piv.getCylQuant().get(i);
		c7 = piv.getCylCount().get(i);
		int z = 1;
		if(c7>10) {
			int a = (int)Math.floor((double)c7/10.0);
			int b = c7%10;
			
			for(int k = 0;k<a;k++) {
				cylList7.append("7.00 ");
			for(int m=0;m<10;m++,z++) {
				if(m>=4) {
					cylList7.append(" ");
				}
				if(m%2==0) {
					cylList7.append(" ");
				}
				String num = String.valueOf(z);
				cylList7.append(num+spaceCheck(num));
			}
			cylList7.append(System.getProperty("line.separator"));
			cylList7.append(System.getProperty("line.separator"));
		}
		if(b!=0) {
			cylList7.append("7.00 ");
			for(int k=0;k<b;k++,z++) {
				if(k>=4) {
					cylList7.append(" ");
				}
				if(k%2==0) {
					cylList7.append(" ");
				}
				String num = String.valueOf(z);
				cylList7.append(num+spaceCheck(num));
			}
		}
		}else {
			cylList7.append("7.00 ");
			for(int k=0;k<c7;k++,z++) {
				if(k>=4) {
					cylList7.append(" ");
				}
				if(k%2==0) {
					cylList7.append(" ");
				}
				String num = String.valueOf(z);
				cylList7.append(num+spaceCheck(num));
			}
		}
		cylList7.append(System.getProperty("line.separator"));
		}
			
	
	if(piv.getCylCap().get(i)==6.00) {
		q6 = piv.getCylQuant().get(i);
		c6 = piv.getCylCount().get(i);
		int z = 1;
		if(c6>10) {
			int a = (int)Math.floor((double)c6/10.0);
			int b = c6%10;
			
			for(int k = 0;k<a;k++) {
				cylList6.append("6.00 ");
			for(int m=0;m<10;m++,z++) {
				if(m>=4) {
					cylList6.append(" ");
				}
				if(m%2==0) {
					cylList6.append(" ");
				}
				String num = String.valueOf(z);
				cylList6.append(num+spaceCheck(num));
			}
			cylList6.append(System.getProperty("line.separator"));
			cylList6.append(System.getProperty("line.separator"));
					
		}
		if(b!=0) {
			cylList6.append("6.00 ");
			for(int k=0;k<b;k++,z++) {
				if(k>=4) {
					cylList6.append(" ");
				}
				if(k%2==0) {
					cylList6.append(" ");
				}
				String num = String.valueOf(z);
				cylList6.append(num+spaceCheck(num));
			}
			cylList6.append(System.getProperty("line.separator"));
			cylList6.append(System.getProperty("line.separator"));
		}
		}else {
			cylList6.append("6.00 ");
			for(int k=0;k<c6;k++,z++) {
				if(k>=4) {
					cylList6.append(" ");
				}
				if(k%2==0) {
					cylList6.append(" ");
				}
				String num = String.valueOf(z);
				cylList6.append(num+spaceCheck(num));
			}
		}
		cylList6.append(System.getProperty("line.separator"));
	}
	if(piv.getCylCap().get(i)==5.00) {
		q5 = piv.getCylQuant().get(i);
		c5 = piv.getCylCount().get(i);
		int z = 1;
		if(c5>10) {			
			int a = (int)Math.floor((double)c5/10.0);
			int b = c5%10;
			
			for(int k = 0;k<a;k++) {
				cylList5.append("5.00 ");
			for(int m=0;m<10;m++,z++) {
				if(m>=4) {
					cylList5.append(" ");
				}
				if(m%2==0) {
					cylList5.append(" ");
				}
				String num = String.valueOf(z);
				cylList5.append(num+spaceCheck(num));
			}
			cylList5.append(System.getProperty("line.separator"));
			cylList5.append(System.getProperty("line.separator"));
		}
		if(b!=0) {
			cylList5.append("5.00 ");
			for(int k=0;k<b;k++,z++) {
				if(k>=4) {
					cylList5.append(" ");
				}
				if(k%2==0) {
					cylList5.append(" ");
				}
				String num = String.valueOf(z);
				cylList5.append(num+spaceCheck(num));
			}
			cylList5.append(System.getProperty("line.separator"));
			cylList5.append(System.getProperty("line.separator"));
		}
		}else {
			cylList5.append("5.00 ");
			for(int k=0;k<c5;k++,z++) {
				if(k>=4) {
					cylList5.append(" ");
				}
				if(k%2==0) {
					cylList5.append(" ");
				}
				String num = String.valueOf(z);
				cylList5.append(num+spaceCheck(num));
				}
			cylList5.append(System.getProperty("line.separator"));
			cylList5.append(System.getProperty("line.separator"));
		}
		cylList5.append(System.getProperty("line.separator"));
	}
	if(piv.getCylCap().get(i)==3.00) {
		q3 = piv.getCylQuant().get(i);
		c3 = piv.getCylCount().get(i);
		int z = 1;
		if(c3>10) {
			
			int a = (int)Math.floor((double)c3/10.0);
			int b = c3%10;
			
			for(int k = 0;k<a;k++) {
				cylList3.append("3.00 ");
			for(int m=0;m<10;m++,z++) {
				if(m>=4) {
					cylList3.append(" ");
				}
				if(m%2==0) {
					cylList3.append(" ");
				}
				String num = String.valueOf(z);
				cylList3.append(num+spaceCheck(num));
			}
			cylList3.append(System.getProperty("line.separator"));
			cylList3.append(System.getProperty("line.separator"));
					
		}
		if(b!=0) {
			cylList3.append("3.00 ");
			for(int k=0;k<b;k++,z++) {
				if(k>=4) {
					cylList3.append(" ");
				}
				if(k%2==0) {
					cylList3.append(" ");
				}
				String num = String.valueOf(z);
				cylList3.append(num+spaceCheck(num));
			}
			cylList3.append(System.getProperty("line.separator"));
			cylList3.append(System.getProperty("line.separator"));
		}
		}else {
			cylList3.append("3.00 ");
			for(int k=0;k<c3;k++,z++) {
				if(k>=4) {
					cylList3.append(" ");
				}
				if(k%2==0) {
					cylList3.append(" ");
				}
				String num = String.valueOf(z);
				cylList3.append(num+spaceCheck(num));
			}
			cylList3.append(System.getProperty("line.separator"));
			cylList3.append(System.getProperty("line.separator"));
		}
		cylList3.append(System.getProperty("line.separator"));
	}
	if(piv.getCylCap().get(i)==1.50) {
		q1_5 = piv.getCylQuant().get(i);
		c1_5 = piv.getCylCount().get(i);
		int z = 1;
		if(c1_5>10) {
			
			int a = (int)Math.floor((double)c1_5/10.0);
			int b = c1_5%10;
			
			for(int k = 0;k<a;k++) {
				cylList1_5.append("1.50 ");
			for(int m=0;m<10;m++,z++) {
				if(m>=4) {
					cylList1_5.append(" ");
				}
				if(m%2==0) {
					cylList1_5.append(" ");
				}
				String num = String.valueOf(z);
				cylList1_5.append(num+spaceCheck(num));
			}
			cylList1_5.append(System.getProperty("line.separator"));
			cylList1_5.append(System.getProperty("line.separator"));
					
		}
		if(b!=0) {
			cylList1_5.append("1.50 ");
			for(int k=0;k<b;k++,z++) {
				if(k>=4) {
					cylList1_5.append(" ");
				}
				if(k%2==0) {
					cylList1_5.append(" ");
				}
				String num = String.valueOf(z);
				cylList1_5.append(num+spaceCheck(num));
			}
			
		}
		}else {
			cylList1_5.append("1.50 ");
			for(int k=0;k<c1_5;k++,z++) {
				if(k>=4) {
					cylList1_5.append(" ");
				}
				if(k%2==0) {
					cylList1_5.append(" ");
				}
				String num = String.valueOf(z);
				cylList1_5.append(num+spaceCheck(num));
			}
			cylList1_5.append(System.getProperty("line.separator"));
			cylList1_5.append(System.getProperty("line.separator"));
		}
		cylList1_5.append(System.getProperty("line.separator"));
	}
	if(piv.getCylCap().get(i)==1.00) {
		q1 = piv.getCylQuant().get(i);
		c1 = piv.getCylCount().get(i);
		int z = 1;
		if(c1>10) {
			
			int a = (int)Math.floor((double)c1/10.0);
			int b = c1%10;
			
			for(int k = 0;k<a;k++) {
				cylList1.append("1.00 ");
			for(int m=0;m<10;m++,z++) {
				if(m>=4) {
					cylList1.append(" ");
				}
				if(m%2==0) {
					cylList1.append(" ");
				}
				String num = String.valueOf(z);
				cylList1.append(num+spaceCheck(num));
			}
			cylList1.append(System.getProperty("line.separator"));
			cylList1.append(System.getProperty("line.separator"));
					
		}
		if(b!=0) {
			cylList1.append("1.00 ");
			for(int k=0;k<b;k++,z++) {
				if(k>=4) {
					cylList1.append(" ");
				}
				if(k%2==0) {
					cylList1.append(" ");
				}
				String num = String.valueOf(z);
				cylList1.append(num+spaceCheck(num));
			}
			cylList1.append(System.getProperty("line.separator"));
			cylList1.append(System.getProperty("line.separator"));
		}
		}else {
			cylList1.append("1.00 ");
			for(int k=0;k<c1;k++,z++) {
				if(k>=4) {
					cylList1.append(" ");
				}
				if(k%2==0) {
					cylList1.append(" ");
				}
				String num = String.valueOf(z);
				cylList1.append(num+spaceCheck(num));
			}
			cylList1.append(System.getProperty("line.separator"));
			cylList1.append(System.getProperty("line.separator"));
		}
		cylList1.append(System.getProperty("line.separator"));
		}
	}
	
	StringBuilder capline =new StringBuilder();
	if(c7!=0) {		
		capline.append(nextCyl+c7+space6+"7.00 Cu.m \t"+q7);
		capline.append(System.getProperty("line.separator"));
	}
	
	if(c6!=0) {
		capline.append(nextCyl+c6+space6+"6.00 Cu.m \t"+q6);
		capline.append(System.getProperty("line.separator"));
	}
	if(c5!=0) {
		capline.append(nextCyl+c5+space6+"5.00 Cu.m \t"+q5);
		capline.append(System.getProperty("line.separator"));
	}
	if(c3!=0) {
		capline.append(nextCyl+c3+space6+"3.00 Cu.m \t"+q3);
		capline.append(System.getProperty("line.separator"));
	}
	if(c1_5!=0) {
		capline.append(nextCyl+c1_5+space6+"1.50 Cu.m \t"+q1_5);
		capline.append(System.getProperty("line.separator"));
	}
	if(c1!=0) {
		capline.append(nextCyl+c1+space6+"1.00 Cu.m \t"+q1);
		capline.append(System.getProperty("line.separator"));
	}
	
	StringBuilder cylListline = new StringBuilder();
	
	if(!cylList7.toString().isEmpty()) {
		cylListline.append(cylList7);
		cylListline.append(System.getProperty("line.separator"));
	}
	if(!cylList6.toString().isEmpty()) {
		cylListline.append(cylList6);
		cylListline.append(System.getProperty("line.separator"));
	}
	if(!cylList5.toString().isEmpty()) {
		cylListline.append(cylList5);
		cylListline.append(System.getProperty("line.separator"));
	}
	if(!cylList3.toString().isEmpty()) {
		cylListline.append(cylList3);
		cylListline.append(System.getProperty("line.separator"));
	}
	if(!cylList1_5.toString().isEmpty()) {
		cylListline.append(cylList1_5);
		cylListline.append(System.getProperty("line.separator"));
	}
	if(!cylList1.toString().isEmpty()) {
		cylListline.append(cylList1);
		cylListline.append(System.getProperty("line.separator"));
	}
	
	LocalDate dt = piv.getDate();
	
	String date = dt.format(formatter);
	
	List<String> lines = Arrays.asList("","","",billNoSpace+piv.getBillNo(),
			dealerName+spaceDN+date,
			daFl+spaceDA+piv.getVehicleNo(),daSl+" Contact No "+piv.getContactNo(),"",
			tinSpace+"TIN# "+piv.getTin(),"","","","",piv.getGasType(),capline.toString(),"",
			nextCyl+(c1+c1_5+c3+c5+c6+c7)+"\t\t\t"+piv.getQuantity()+"\t"+space5+format.format(piv.getRate()),
			"","","",saleSpace+"Sales Value "+"\t\t"+space3+format.format(piv.getSaleValue()),
			saleSpace+"ADD: CGST @ 9%"+"\t\t"+space5+format.format(piv.getCGST()),saleSpace+"ADD: SGST @ 9%"
			+"\t\t"+space5+format.format(piv.getSGST()),"","",saleSpace+"Bill - Value"
			+"\t\t"+space3+format.format(piv.getBillValue()),"", 
			"",piv.getNumtoword()+" ONLY","","","","","",cylListline.toString());
	Path file = Paths.get("E:\\PIONEER\\Bill\\"+piv.getBillNo()+".txt");
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