package com.pio.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pio.dao.CylinderDetailsTab;
import com.pio.dao.CylinderRegisterDAO;
import com.pio.model.Cylinder;

@Service
public class CylinderUtility implements CylinderUtil {

	@Autowired
	private CylinderRegisterDAO cylinderDAO;
	
	@Autowired
	private CylinderDetailsTab cdtDAO;
	
	
	@Override
	public String register(Cylinder cylinder) {
		Timestamp time = Timestamp.valueOf(LocalDateTime.now());
		cylinder.setLastModifiedDate(time);
		cylinder.setUsageStatus("0");
		return cylinderDAO.cylinderRegister(cylinder);
	}

	@Override
	public String findCylinder(Long cylinderId) {
		String result ="";
		result = cdtDAO.findCylinder(cylinderId);
		return result;
	}

	@Override
	public Double getCylinderCapacity(Long cylinderId) {
		return cdtDAO.getCylinderCapacity(cylinderId);
	}

	@Override
	public String checkCylinder(Long cylinderId) {
		return cylinderDAO.checkCylinder(cylinderId);
	}

	@Override
	public String getCylinderType(Long cylinderId) {
		return cdtDAO.typeOfCylinders(cylinderId);
	}

	@Override
	public String getUsageAndType(Long cylinderId) {
		return cdtDAO.getUsageAndType(cylinderId);
	}
	

}
