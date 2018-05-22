package com.pio.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pio.model.GenerateInvoiceBean;

@Repository
public class RectifyDAOI implements RectifyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public String rectifyCylinder(String wrongCid, String rightCid, String dealerId) {
		String result ="FAIL";
		Session session = sessionFactory.getCurrentSession();
		Long wCid = Long.valueOf(wrongCid);
		Long rCid = Long.valueOf(rightCid);
		try {
			String hql = "update CylinderTransactions set cylinderId=:rCid where cylinderId=:wCid and dealerId=:dealerId"
					+ " and billGenerated='N' and live ='Y'";
			session.createQuery(hql).setParameter("rCid", rCid).setParameter("wCid", wCid).
			setParameter("dealerId", dealerId).executeUpdate();
			hql = "update Cylinder set usageStatus='0',billGenerated='N',damage='N' where cylinderId=:wCid";
			session.createQuery(hql).setParameter("wCid", wCid).executeUpdate();
			hql = "update Cylinder set usageStatus=:dealerId,billGenerated='N',damage='N' where cylinderId=:rCid";
			session.createQuery(hql).setParameter("rCid", rCid).setParameter("dealerId", dealerId).executeUpdate();
			result ="SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	@Transactional
	public String rectifyLorry(String billNo, String lorryNo) {
		String result ="FAIL";
		Session session = sessionFactory.getCurrentSession();
		Long bNo = Long.valueOf(billNo);
		if(bNo instanceof Long) {
		try {
			String hql = "select billNo from GenerateInvoiceBean where billNo=:bNo";
			Long bill = (Long)session.createQuery(hql).setParameter("bNo", bNo).uniqueResult();
			if(bill instanceof Long) {
			hql = "update GenerateInvoiceBean set vehicleNo=:vehicleNo where billNo=:bNo";
			session.createQuery(hql).setParameter("vehicleNo", lorryNo).setParameter("bNo", bNo).executeUpdate();
			result ="SUCCESS";
			}else
				{
					result ="FAIL";
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		}else {
			result ="FAIL";
		}
		return result;
	}

	@Override
	@Transactional
	public GenerateInvoiceBean reprintBill(String billNo) {
		GenerateInvoiceBean result =new GenerateInvoiceBean();
		Session session = sessionFactory.getCurrentSession();
		Long bNo = Long.valueOf(billNo);
		if(bNo instanceof Long) {
		try {
			
			Criteria crit = session.createCriteria(GenerateInvoiceBean.class);
			crit.add(Restrictions.eq("billNo", bNo));
			result = (GenerateInvoiceBean) crit.uniqueResult();
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		}else {
			result =null;
		}
		return result;
	}

}
