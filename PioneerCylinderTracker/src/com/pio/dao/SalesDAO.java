package com.pio.dao;

import java.util.Date;

public interface SalesDAO {

	String fetchDealerSales(Date frDate, Date tDate, String dealerId);

}