package com.pio.util;

import com.pio.model.Cylinder;

public interface CylinderUtil {
	
	public String register(Cylinder cylinder);

	public String findCylinder(Long cylinderId);
	
	public Double getCylinderCapacity(Long cylinderId);

	public String checkCylinder(Long cylinderId);

	public String getCylinderType(Long cylinderId);

	public String getUsageAndType(Long cylinderId);
}
