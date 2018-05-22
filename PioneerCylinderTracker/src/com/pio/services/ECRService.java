package com.pio.services;

import com.pio.model.ECRTransactionBean;
import com.pio.model.GenerateECRBean;
import com.pio.vo.PrintECRVO;

public interface ECRService {
	public String updateECRTx(ECRTransactionBean ecr);

	public Long generateECR(GenerateECRBean ecr);

	public String generateID();

	public String getECR(Long ecrNo);

	public String printECR(PrintECRVO pecr);

	String updateECR(GenerateECRBean ecr);

	public String checkECRGenStatus(String dealerId, String lorryNo);

	public String updateECRTxBatch(ECRTransactionBean[] ecr);
}
