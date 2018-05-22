package com.pio.dao;

import com.pio.model.DealerDetails;

public interface DealerRegister {
	
	public String dealerRegister(DealerDetails dealer);
	public Long findSequenceID ();

}
