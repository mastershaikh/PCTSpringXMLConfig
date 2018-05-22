package com.pio.services;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pio.dao.ECRDAO;
import com.pio.model.ECRTransactionBean;
import com.pio.model.GenerateECRBean;
import com.pio.vo.PrintECRVO;

@Service
public class ECRServices implements ECRService{

	@Autowired
	private ECRDAO ecrDAO;
	
	@Override
	public String updateECRTx(ECRTransactionBean ecr) {
			String result="";
			String ECRTxId= generateID();
			ecr.setTxId(ECRTxId);
			result=ecrDAO.transact(ecr);
			return result;
		}
	
	@Override
	public String generateID(){
		String result = "FAIL";
		Long sequence = ecrDAO.findECRTxSequenceID();
		result="EC"+sequence;
		return result;
	}

	@Override
	public Long generateECR(GenerateECRBean ecr) {
		Long ecrno = ecrDAO.generateECRSequence();
		ecr.setEcrNo(ecrno);
		ecrDAO.saveECR(ecr);
		return ecrno;
	}
	
	@Override
	public String printECR(PrintECRVO pecr) {
		String result ="FAIL";
		String space =" ";
		for(int i=0;i<60;i++) {
			space +=" ";
		}
		
		int maxAdd = pecr.getAddress().length();
		String daFl = "",daSl="";
		if(maxAdd<60) {
			daFl = pecr.getAddress().substring(0);
		}else {
			daSl = pecr.getAddress().substring(50);
		}
		int dalength = daFl.length();
		String spaceDA=" ";
		for(int i = 0;i<(60-dalength);i++) {
			spaceDA += " ";
			}
		String ecrNo = pecr.getEcrNo();
		LocalDate dt = pecr.getEcrDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
		String ecrDate = dt.format(formatter);
		String lorryNo = pecr.getLorryNo();
		StringBuilder cylList=new StringBuilder();
		int total = pecr.getCylinderList().size();
		int a = (int)Math.floor((double)total/8.0);
		int b = total%8;
		int i =0;
			if(total>8) {
				for(int k=0;k<a;k++) {
					for(int j=0;j<8;j++,i++)
						{
						cylList.append(pecr.getCylinderList().get(i)+"   ");
						}
				cylList.append(System.getProperty("line.separator"));
			}
				if(b!=0) {
					for(int j=0;j<b;j++,i++) {
						cylList.append(pecr.getCylinderList().get(i) + "   ");
					}
				}
			}else {
				for(int j=0;j<b;j++,i++) {
					cylList.append(pecr.getCylinderList().get(i)+"   ");
				}
			
		}
		
		List<String> lines = Arrays.asList(space+ecrNo,space+ecrDate+"\t\t"+lorryNo,
				space+pecr.getDealerName().toUpperCase(),space+daFl+spaceDA,
				space+daSl+pecr.getContactNo(),"","","","",cylList.toString());
		Path file = Paths.get("E:\\PIONEER\\ECR\\"+ecrNo+".txt");
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
	public String getECR(Long ecrNo) {
		String result ="";
		result = ecrDAO.getECR(ecrNo);
		return result;
	}
	@Override
	public String updateECR(GenerateECRBean ecr) {
		return ecrDAO.updateECR(ecr);
	}

	@Override
	public String checkECRGenStatus(String dealerId, String lorryNo) {
		String result ="";
		result = ecrDAO.checkECRGenStatus(dealerId,lorryNo);
		return result;
	}

	@Override
	public String updateECRTxBatch(ECRTransactionBean[] ecr) {
		for(ECRTransactionBean etb:ecr) {
		String ECRTxId= generateID();
		etb.setTxId(ECRTxId);
		}
		return ecrDAO.updateECRTxBatch(ecr);
	}
}
