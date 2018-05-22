package com.pio.dao;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pio.model.OutstandingBean;

@Repository
public class OutstandingDAOI implements OutstandingDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public String genOutstanding(String dealerId) {
		String result ="FAIL";
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql = "select billNo,billValue,totalCylinders,billDate,CGST,SGST,rate,quantity from GenerateInvoiceBean where dealerId=:dealerId "
					+ "and outstanding ='N'";
			Query query = session.createQuery(hql).setParameter("dealerId", dealerId);
			List list = query.list();
			String billValue = "",billNo="",totalCylinders="",CGST="",SGST="",billDate="",rate="",quantity="";
			for(Iterator i = list.iterator();i.hasNext();){
				Object[] ob = (Object[])i.next();
				billNo +=(long)ob[0]+";";
				hql="update GenerateInvoiceBean set outstanding='Y' where billNo=:bno";
				session.createQuery(hql).setParameter("bno", (long)ob[0]).executeUpdate();
				billValue +=(double)ob[1]+";";
				totalCylinders +=(int)ob[2]+";";
				billDate += (Timestamp)ob[3]+";";
				CGST +=(double)ob[4]+";";
				SGST +=(double)ob[5]+";";
				rate +=(double)ob[6]+";";
				quantity +=(double)ob[7]+";";
			}
			if(!billNo.isEmpty()) {
			result = billNo+"#"+billValue+"#"+totalCylinders+"#"+billDate+"#"+CGST+"#"+SGST+"#"+rate+"#"+quantity;
			}
			else
				result ="FAIL";
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional
	public String updateOutTable(OutstandingBean outb) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(outb);
		return "SUCCESS";
	}

	@Override
	@Transactional
	public Long generateOutSequence () {
		Long result = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createSQLQuery("select PIO_OUT_SEQ.NEXTVAL from dual");
		result = ((BigDecimal) query.uniqueResult()).longValue();
		return result;
	}

	@Override
	@Transactional
	public String updateInvoiceTable(String billNos) {
		String result ="FAIL";
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "update GenerateInvoiceBean set outstanding='Y' where billNo=:bills";
			
			String[] bills = billNos.split(";");
			for(String b:bills) {
				Long bil = Long.valueOf(b);
				if(bil instanceof Long) {
					session.createQuery(hql).setParameter("bills",bil).executeUpdate();
				}
			}
			return "SUCCESS";
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public String genCylstanding(String dealerId) {
		String result ="FAIL";
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "select cylinderId,lastModifiedDate from Cylinder where usageStatus=:dealerId";
			Query query = session.createQuery(hql).setParameter("dealerId", dealerId);
			List list = query.list();
			String cylinderId = "", issueDate="";
			for(Iterator i = list.iterator();i.hasNext();) {
				Object[] ob = (Object[])i.next();
				if(ob instanceof Object) {
				cylinderId +=(long)ob[0]+";";
				issueDate +=(Timestamp)ob[1]+";";
				}
			}
			if(!cylinderId.isEmpty())
			result = cylinderId+"#"+issueDate;
			else {
				result = "FAIL";
			}
			}			
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
