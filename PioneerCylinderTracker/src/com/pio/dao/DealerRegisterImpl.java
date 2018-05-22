package com.pio.dao;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pio.model.DealerDetails;

@Repository
public class DealerRegisterImpl implements DealerRegister {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public String dealerRegister(DealerDetails dealer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(dealer);
		return "SUCCESS";
	}
	
	@Transactional
	public Long findSequenceID () {
		Long result = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createSQLQuery("select PIO_DEALER_SEQ.NEXTVAL from dual");
		result = ((BigDecimal) query.uniqueResult()).longValue();
		System.out.println(result);
		return result;
	}
}
