package com.pio.dao;

import java.math.BigDecimal;
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

import com.pio.model.ECRTransactionBean;
import com.pio.model.GenerateECRBean;

@Repository
public class ECRDAOI implements ECRDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public String updateECR(GenerateECRBean ecr) {
		Session session = sessionFactory.getCurrentSession();
		String result = "FAIL";
		try {
			String hql ="select cylinderId,txId from ECRTransactionBean where dealerId=:dId and lorryNo=:lorry"
					+ " and ecrStatus='N'";
			Query query = session.createQuery(hql).setParameter("dId", ecr.getDealerId()).
					setParameter("lorry",ecr.getLorryNo());
			List list = query.list();
			String cylList ="";
			for(Iterator i = list.iterator();i.hasNext();){
				Object[] object =  (Object[])i.next();
				if(object instanceof Object) {
				cylList += (Long)object[0]+";";
					hql ="update ECRTransactionBean set ecrStatus='Y' where txId=:tx";
					session.createQuery(hql).setParameter("tx", (String)object[1]).executeUpdate();
					}
				}
			if(!cylList.isEmpty()) {
			hql ="update GenerateECRBean set outstanding='N',ecrDate=:ecrDt,lorryNo=:lorry,cylinderNo=:cno,dealerId=:dId where ecrNo=:ecrno";
			session.createQuery(hql).setParameter("dId", ecr.getDealerId()).setParameter("ecrno", ecr.getEcrNo()).
			setParameter("lorry", ecr.getLorryNo()).setParameter("ecrDt", ecr.getEcrDate()).
			setParameter("cno", cylList).executeUpdate();
			result ="SUCCESS";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	@Transactional
	public Long generateECRSequence () {
		Long result = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createSQLQuery("select PIO_ECR_SEQ.NEXTVAL from dual");
		result = ((BigDecimal) query.uniqueResult()).longValue();
		return result;
	}
	@Override
	@Transactional
	public Long findECRTxSequenceID () {
		Long result = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createSQLQuery("select PIO_ECRTX_SEQ.NEXTVAL from dual");
		result = ((BigDecimal) query.uniqueResult()).longValue();
		System.out.println("sequence = "+result);
		return result;
	}

	@Override
	@Transactional
	public String transact(ECRTransactionBean ecr) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(ecr);
		return "SUCCESS";
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public String getECR(Long ecrNo) {
		Session session = sessionFactory.getCurrentSession();
		String result = "FAIL";
		try {
			String hql ="select cylinderNo,lorryNo,ecrDate from GenerateECRBean where ecrNo=:ecrNo";
			 Query query = session.createQuery(hql).setParameter("ecrNo", ecrNo);
			List list = query.list();
			String cylList ="",lorryNo="";
			Timestamp ecrDate = null;
			for(Iterator i = list.iterator();i.hasNext();){
				Object[] object = (Object[]) i.next();
				cylList= (String)object[0];
				lorryNo = (String)object[1];
				ecrDate = (Timestamp)object[2];
			}
			result = cylList+"#"+lorryNo+"#"+ecrDate;
			System.out.println("result in getECR at ECRDAOI = "+result);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional
	public String saveECR(GenerateECRBean ecr) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(ecr);
		return "SUCCESS";
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public String checkECRGenStatus(String dealerId, String lorryNo) {
		Session session = sessionFactory.getCurrentSession();
		String result ="FAIL";
		try {
			String hql ="select cylinderId from ECRTransactionBean where ecrStatus='N' and dealerId=:dealerId and "
					+ "lorryNo=:lorryNo";
			 Query query = session.createQuery(hql).setParameter("dealerId", dealerId).setParameter("lorryNo", lorryNo);
			List list = query.list();
			String cylList ="";
			for(Iterator i = list.iterator();i.hasNext();){
				Object object =  (Object) i.next();//Error HERE!!!!
				if(object instanceof Object)
				cylList += (Long)object+";";
				}
			if(!cylList.isEmpty()) {
				result ="NOECR";
			}
			else {
				
				Criteria crit = session.createCriteria(GenerateECRBean.class).
						setProjection(Projections.max("ecrDate"));
					crit.add(Restrictions.eq("dealerId", dealerId)).add(Restrictions.eq("lorryNo", lorryNo)).setMaxResults(1);
				
					Timestamp geb = (Timestamp)crit.uniqueResult();
					
					hql = "select ecrNo from GenerateECRBean where ecrDate=:geb and lorryNo=:lorryNo";
					Long ecrNo = (Long)session.createQuery(hql).setParameter("geb", geb).setParameter("lorryNo", lorryNo).uniqueResult();
				
				if(ecrNo instanceof Long)
				result = ecrNo+"";
				
				else result ="FAIL";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional
	public String updateECRTxBatch(ECRTransactionBean[] ecr) {
		Session session = sessionFactory.getCurrentSession();
		for(ECRTransactionBean ect:ecr)
		session.saveOrUpdate(ect);
		return "SUCCESS";
	}
	
}
