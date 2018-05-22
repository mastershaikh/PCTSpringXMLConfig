package com.pio.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pio.dao.CylinderDetailsTab;
import com.pio.dao.CylinderTransactionDAO;
import com.pio.dao.DealerDAO;
import com.pio.model.CylinderTransactions;
import com.pio.model.DealerCylinderTxn;
import com.pio.vo.PreviewInvoiceVO;

@Service
public class CylinderServices implements CylinderService {

	@Autowired
	private CylinderTransactionDAO txDAO;
	
	@Autowired
	private CylinderDetailsTab cylinderDAO;
	
	@Autowired
	private DealerDAO dDAO;
	
	@Override
	public String purchase(CylinderTransactions buy) {
		String result="";
		String TxId= generateID();
		buy.setTransactionId(TxId);
		System.out.println("TXID ="+TxId);
		Timestamp time = Timestamp.valueOf(LocalDateTime.now());
		buy.setdOTx(time);
		buy.setLiveTx("Y");
		buy.setBillGenerated("N");
		result=txDAO.transact(buy);
		return result;
	}

	@Override
	public long countCylinders(String cylinderType) {
		return cylinderDAO.countCylinder(cylinderType);
	}

	public String generateID(){
		String result = "FAIL";
		Long sequence = txDAO.findSequenceID();
		result="TX"+sequence;
		return result;
	}

	@Override
	public String returnCylinder(CylinderTransactions cylinders) {
		return txDAO.returnCylinders(cylinders);
	}

	@Override
	public TreeMap<Integer, String> listCylinders(String dealerId) {
		TreeMap<Integer, String> result = new TreeMap<Integer,String>();
		result= txDAO.listCylinders(dealerId);
		return result;
	}

	@Override
	public String usageStatus(Long cylinderId) {
		return txDAO.usageStatus(cylinderId);

	}

	@Override
	public String typeOfCylinders(Long cylinderId) {
		String result = cylinderDAO.typeOfCylinders(cylinderId);
		return result;
	}

	@Override
	public String getDealerFromCylinder(Long cylinderId) {
		String result ="";
		result = dDAO.getDealerFCylinder(cylinderId);
		return result;
	}

	@Override
	public TreeMap<Long,String> capacityOfCylinders(String[] cno) {
		TreeMap<Long,String> result = new TreeMap<>();
		result = cylinderDAO.capacityOfCylinders(cno);
		return result;
	}

	@Override
	public String dealerCylinderBuy(DealerCylinderTxn dct) {
		String result ="";
		
		Double rate = dDAO.dealerCylinderRate(dct.getCylinderType(),dct.getDealerId());
		if(rate==0.0) {
			return "FAIL";
		}
		Long dctsequence = dDAO.getDealerCylinderSequence();
		dct.setTransactionId(dctsequence);
		
		Double billValue = rate*dct.getCylinderCount()*dct.getCapacity();
		billValue = Math.round(billValue*100D)/100D;
		dct.setBillValue(billValue);
		
		dct.setBillGenerated("N");
		
		dct.setdOTx(Timestamp.valueOf(LocalDateTime.now()));
		dct.setQuantity(dct.getCapacity()*dct.getCylinderCount());
		result = dDAO.updateDealerCylinderTable(dct);
		
		return result;
	}

	@Override
	public PreviewInvoiceVO previewInvoice(String dealerId) {
		PreviewInvoiceVO piv = new PreviewInvoiceVO();
		piv = cylinderDAO.previewInvoice(dealerId);
		return piv;
	}

	@Override
	public String previewDealerInvoice(String dealerId) {
		String result = "";
		result = cylinderDAO.previewDealerInvoice(dealerId);
		return result;
	}

	@Override
	public DealerCylinderTxn dealerTxnCylinder(Long l) {
		return cylinderDAO.dealerTxnCylinder(l);
	}

	@Override
	public String batchPurchase(CylinderTransactions[] cylinder) {
		for(CylinderTransactions ctx:cylinder) {
		ctx.setTransactionId(generateID());
		ctx.setdOTx(Timestamp.valueOf(LocalDateTime.now()));
		ctx.setLiveTx("Y");
		ctx.setBillGenerated("N");
		}
		return txDAO.batchTransact(cylinder);
	}

	@Override
	public String returnCylinderBatch(ArrayList<Long> cylNumbers, String dealerId) {
		return txDAO.returnCylinderBatch(cylNumbers,dealerId);
	}

}
