package com.pio.dao;

import com.pio.model.Cylinder;

public interface CylinderRegisterDAO {
	
	public String cylinderRegister(Cylinder cylinder);

	public String checkCylinder(Long cylinderId);

}
