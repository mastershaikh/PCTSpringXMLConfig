package com.pio.dao;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pio.model.GenerateInvoiceBean;

@Repository
public class GenerateInvoiceDAOI implements GenerateInvoiceDAO {

	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public String generateInvoice(GenerateInvoiceBean gi) {
		Session session = sessionFactory.getCurrentSession();
		String result ="FAIL";
		try {
			String hql = "select cylinderId from CylinderTransactions "
					+ "where dealerId=:dealerId and liveTx='Y' and billGenerated='N'";
			String dealerId = gi.getDealerId();
			Query query = session.createQuery(hql).setParameter("dealerId", dealerId);	
			List txList =  query.list();
			String list = "";
			for(Iterator<Long> i = txList.iterator();i.hasNext();){
				Long val =  i.next();
				hql ="update CylinderTransactions set billGenerated='Y' where cylinderId =:cyl"
						+ " and dealerId=:dealerId and liveTx='Y'";
				session.createQuery(hql).setParameter("cyl", val).setParameter("dealerId", dealerId).executeUpdate();
				
				hql ="update Cylinder set billGenerated='Y' where cylinderId =:cyl"
						+ " and usageStatus=:dealerId";
				session.createQuery(hql).setParameter("cyl", val).setParameter("dealerId", dealerId).executeUpdate();
				
				list +=val+"#";				
				}
			gi.setCylinderNo(list);
			session.saveOrUpdate(gi);
			result =list;
		}
			catch (Exception e) {
				System.out.println("Exception occured");
				e.printStackTrace();
			}
		return result;
	}
	@Override
	@Transactional
	public String makeInvoice(Long billNo) {
		Session session = sessionFactory.getCurrentSession();
		String result ="FAIL";
		try {
			String hql = "select cylinderNo from GenerateInvoiceBean where billNo=:bNo";
			result = (String)session.createQuery(hql).setParameter("bNo", billNo).uniqueResult();
		}
			catch (Exception e) {
				System.out.println("Exception occured");
				e.printStackTrace();
			}
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public String checkInvoiceGenStatus(GenerateInvoiceBean gi) {
		String result ="";
		Session session = sessionFactory.getCurrentSession();
		String lorryNo = gi.getVehicleNo();
		String dealerId = gi.getDealerId();
		try {
			String hql = "select billGenerated from Cylinder where usageStatus=:dealerId and billGenerated='N'";
			Query query = session.createQuery(hql).setParameter("dealerId", dealerId).setMaxResults(1);
			List list = query.list();
			Iterator i = list.iterator();
			if(i.hasNext()) {
				String bill = (String)i.next();
				if(bill instanceof String)
				result ="SUCCESS";
			}
			else {
				Criteria crit = session.createCriteria(GenerateInvoiceBean.class).
						setProjection(Projections.max("billDate"));
					crit.add(Restrictions.eq("dealerId", dealerId)).add(Restrictions.eq("vehicleNo", lorryNo)).setMaxResults(1);
				
					Timestamp gib = (Timestamp)crit.uniqueResult();
					
					hql = "select billNo from GenerateInvoiceBean where billDate=:gib";
					result = (Long)session.createQuery(hql).setParameter("gib", gib).uniqueResult()+"";
				}		
		}catch (Exception e) {
				System.out.println("Exception occured");
				e.printStackTrace();
			}
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public Double billTillDate(String dealerId) {
		Session session = sessionFactory.getCurrentSession();
		Double result =0.0;
		try {
			
			String hql = "select billValue from GenerateInvoiceBean where dealerId=:dId and outstanding='N'";
			Query query =session.createQuery(hql).setParameter("dId", dealerId);
			List list = query.list();
			Double bill = 0.0;
			for(Iterator i = list.iterator();i.hasNext();) {
				Object obj = (Object)i.next();
				if(obj instanceof Object) {
					bill +=(Double)obj;
				}
			}
			result = bill;
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	@Override
	@Transactional
	public String generateDealerInvoice(GenerateInvoiceBean gi) {
		Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(gi);
			return "SUCCESS";	
			}

}
