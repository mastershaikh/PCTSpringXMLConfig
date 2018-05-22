package com.pio.util;

import java.util.Date;

public interface SalesUtil {

	String fetchDealerSales(Date frDate, Date tDate, String dealerId);

}