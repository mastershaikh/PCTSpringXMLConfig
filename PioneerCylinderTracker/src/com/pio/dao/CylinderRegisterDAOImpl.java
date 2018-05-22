package com.pio.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pio.model.Cylinder;

@Repository
public class CylinderRegisterDAOImpl implements CylinderRegisterDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public String cylinderRegister(Cylinder cylinder) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cylinder);
		return "SUCCESS";
	}

	@Override
	@Transactional
	public String checkCylinder(Long cylinderId) {
		Session session = sessionFactory.getCurrentSession();
		String result ="";
		try {
			String hql ="select cylinderId,capacity,cylinderType,usageStatus from Cylinder where cylinderId=:cId";
			Query query = session.createQuery(hql).setParameter("cId", cylinderId);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			@SuppressWarnings("rawtypes")
			Iterator i = list.iterator();
			if(i.hasNext()) {
				Object[] ob = (Object[])i.next();
				result = (Long)ob[0]+";"+(Double)ob[1]+";"+(String)ob[2]+";"+(String)ob[3];
			}
			
			if(!result.isEmpty()) {
				return result;
			}else {
				return "SUCCESS";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
