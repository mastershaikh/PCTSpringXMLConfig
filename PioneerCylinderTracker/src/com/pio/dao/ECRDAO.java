package com.pio.dao;

import com.pio.model.ECRTransactionBean;
import com.pio.model.GenerateECRBean;

public interface ECRDAO {

	public String updateECR(GenerateECRBean ecr);
	public Long generateECRSequence();
	public Long findECRTxSequenceID ();
	public String transact(ECRTransactionBean ecr);
	public String getECR(Long ecrNo);
	public String saveECR(GenerateECRBean ecr);
	public String checkECRGenStatus(String dealerId, String lorryNo);
	public String updateECRTxBatch(ECRTransactionBean[] ecr);
}
