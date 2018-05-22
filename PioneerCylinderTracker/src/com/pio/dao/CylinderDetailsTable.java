package com.pio.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pio.model.DealerCylinderTxn;
import com.pio.vo.PreviewInvoiceVO;


@Repository
public class CylinderDetailsTable implements CylinderDetailsTab{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public long countCylinder (final String cylinderType) {
		long result = 0;
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "select count(*) from Cylinder where cylinderType=:cylinderType and usageStatus='0' and damage='N'";
			result = (Long)session.createQuery(hql).setParameter("cylinderType", cylinderType).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	@Override
	@Transactional
	public String typeOfCylinders(Long cylinderId) {
		Session session = sessionFactory.getCurrentSession();
		String result = "";
		try {
			String hql = "select cylinderType,damage from Cylinder where cylinderId=:cylId";
			Query query = session.createQuery(hql).setParameter("cylId", cylinderId);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			@SuppressWarnings("rawtypes")
			Iterator i = list.iterator();
			String damage = "";
			String cylinderType= "";
			if(i.hasNext()) {
				Object[] obj = (Object[])i.next();
				if(obj instanceof Object[]) {
				cylinderType = (String)obj[0];
				damage = (String)obj[1];
				}
			}
			if(damage.equals("N")) {
				result = cylinderType;
			}
			else if (damage.equals("Y"))result =" This cylinder is Damaged";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	@Transactional
	public String findCylinder(Long cylinderId) {
		Session session = sessionFactory.getCurrentSession();
		String result = "";
		try {
			String hql = "select usageStatus from Cylinder where cylinderId=:cylId";
			String dealerId = (String)session.createQuery(hql).setParameter("cylId", cylinderId).uniqueResult();
			hql = "select damage,remark from Cylinder where cylinderId=:cylId";
			Query query = session.createQuery(hql).setParameter("cylId", cylinderId);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			@SuppressWarnings("rawtypes")
			Iterator i = list.iterator();
			String damage="",remark="";
			if(i.hasNext()) {
				Object[] ob = (Object[])i.next();
				damage =(String)ob[0];
				if(damage.equals("Y")) {
					remark =(String)ob[1];
				}
			}
			System.out.println("Damage ="+damage+"Remark = "+remark);
			if(dealerId instanceof String) {
			if((!dealerId.equals("0"))&&damage.equals("N")) {
			hql = "select dealerName from DealerDetails where dealerId=:dId";
			result = (String) session.createQuery(hql).setParameter("dId", dealerId).uniqueResult();
			result = dealerId +"-"+result;
			}
			else if(damage.equals("Y")){
				result ="DAMAGE"+remark;
			}
			else if(dealerId.equals("0")){
				result ="FAIL";
			}
			}
			else {
				result ="INVALID";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	@Transactional
	public TreeMap<Long,String> capacityOfCylinders(String[] cno) {
		Session session = sessionFactory.getCurrentSession();
		TreeMap<Long,String> result = new TreeMap<>();
		String cylinderType="";
		Double capacity = 0.0;
		Long cylinderId =(long)0;
		try {
			String hql = "select cylinderId,cylinderType,capacity from Cylinder where cylinderId=:cylId";
			@SuppressWarnings("rawtypes")
			List list;
			for(String c:cno) {
				Long l = Long.valueOf(c);
				if(l instanceof Long) {
					Query query = session.createQuery(hql).setParameter("cylId", l);
					list = query.list();
					@SuppressWarnings("rawtypes")
					Iterator i = list.iterator();
					if(i.hasNext()) {
						Object[] obj = (Object[])i.next();
						if(obj instanceof Object[]) {
						cylinderId = (Long)obj[0];
						cylinderType = (String)obj[1];
						capacity = (Double)obj[2];
						result.put(cylinderId, cylinderType+"-"+capacity);
						}
					}
				}
			}						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	@Transactional
	public PreviewInvoiceVO previewInvoice(String dealerId) {
		Session session = sessionFactory.getCurrentSession();
		PreviewInvoiceVO pivo = new PreviewInvoiceVO();
		int o6=0,o7=0,n6=0,n7=0,air6=0,air7=0;
		try {
			String hql ="select cylinderId from CylinderTransactions where liveTx='Y' and billGenerated='N' "
					+ "and dealerId=:dealerId";
			Query query = session.createQuery(hql).setParameter("dealerId", dealerId);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			ArrayList<Long> c = new ArrayList<>();
			System.out.println("Inside PIVO");
			for(@SuppressWarnings("rawtypes")
			Iterator i =list.iterator();i.hasNext();) {
				Object obj =(Object)i.next();
				if(obj instanceof Object) {
					Long l = (Long)obj;
					System.out.println("cno "+l);
					c.add(l);
					hql ="select capacity from Cylinder where cylinderId=:cId";
					Double cap = (Double) session.createQuery(hql).setParameter("cId", l).uniqueResult();
					System.out.println("capacity"+cap);
					hql = "select cylinderType from Cylinder where cylinderId=:cId";
					String type = (String) session.createQuery(hql).setParameter("cId", l).uniqueResult();
					System.out.println("type"+type);
					if(type.contains("OXYGEN")) {
						pivo.setGasO2(type);
						
						if(cap==7.00) {
							o7++;
						}else if(cap ==6.00) {
							o6++;
						}
					}else if(type.contains("NITROGEN")) {
						pivo.setGasN2(type);
						if(cap==7.00) {
							n7++;
						}else if(cap ==6.00) {
							n6++;
						}
					}else if(type.contains("Air")) {
						pivo.setGasAir(type);
						if(cap==7.00) {
							air7++;
						}else if(cap ==6.00) {
							air6++;
						}
					}
				}
			}
			pivo.setCylList(c);
			Double rateo2 = 0.0,raten2=0.0,rateAir=0.0;
			
			hql = "select rateo2,raten2,rateair from DealerDetails where dealerId=:dId";
			query = session.createQuery(hql).setParameter("dId", dealerId);
			list = query.list();
			for(@SuppressWarnings("rawtypes")
			Iterator i=list.iterator();i.hasNext();) {
				Object[] obj = (Object[])i.next();
				rateo2 = (Double)obj[0];
				raten2 = (Double)obj[1];
				rateAir = (Double)obj[2];
			}
			int o2 = o6+o7;
			int n2 = n6+n7;
			int air = air6+air7;
			Double totalQuantity =0.0;
			Double bill =0.0;
			if(o2>0) {
				pivo.setNoO2(o2);
				totalQuantity = o6*6.00+o7*7.00;
				try {
					bill = rateo2*totalQuantity;
					}catch(NumberFormatException n) {
						System.out.println("not a number");
					}
				}
			else if(n2>0) {
				pivo.setNoN2(n2);
				totalQuantity = n6*6.00+n7*7.00;
				try {
					bill = raten2*totalQuantity;
					}catch(NumberFormatException n) {
						System.out.println("not a number");
					}
				}
			else if(air>0) {
				pivo.setNoN2(n2);
				totalQuantity = air6*6.00+air7*7.00;
				try {
					bill = rateAir*totalQuantity;
					}catch(NumberFormatException n) {
						System.out.println("not a number");
					}
				}
			Double SGST = bill*0.09;
			SGST = Math.round(SGST*100D)/100D;
			System.out.println("SGST"+SGST);
			Double CGST = bill*0.09;
			CGST = Math.round(CGST*100D)/100D;
			System.out.println("CGST"+CGST);
			Double billValue = bill+SGST+CGST;
			billValue = Math.round(billValue*100D)/100D;//Billing Section ends
			pivo.setBillValue(billValue);
			pivo.setCGST(CGST);
			pivo.setSGST(SGST);
			pivo.setQuantity(totalQuantity);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return pivo;
	}
	@Override
	@Transactional
	public String previewDealerInvoice(String dealerId) {
		Session session = sessionFactory.getCurrentSession();
		String result = "";
		try {
			String hql = "select transactionId from DealerCylinderTxn where dealerId=:dId and billGenerated='N'";
			Query query = session.createQuery(hql).setParameter("dId",dealerId);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			hql = "select cylinderCount,capacity,cylinderType,quantity,billValue from DealerCylinderTxn "
					+ "where transactionId=:tid";
			String cylinderCount="",capacity="",cylinderType="",quantity="",billValue="",TxId = "";
			for(@SuppressWarnings("rawtypes")
			Iterator i = list.iterator();i.hasNext();) {
				Object obj = (Object)i.next();
				if(obj instanceof Object) {
					Long tid = (Long)obj;
					TxId +=tid+";";
					query = session.createQuery(hql).setParameter("tid", tid);
					@SuppressWarnings("rawtypes")
					List list1 = query.list();
					for(@SuppressWarnings("rawtypes")
					Iterator j = list1.iterator();j.hasNext();) {
						Object[] obj1 = (Object[])j.next();
						if(obj1 instanceof Object) {
						cylinderCount +=(Integer)obj1[0]+";";
						capacity +=(Double)obj1[1]+";";
						cylinderType +=(String)obj1[2]+";";
						quantity +=(Double)obj1[3]+";";
						billValue +=(Double)obj1[4]+";";
						}
					}
				}
			}
			result = cylinderCount+"#"+capacity+"#"+cylinderType+"#"+quantity+"#"+billValue+"#"+TxId;
			if(!result.isEmpty()) {
				return result;
			}
			else result ="FAIL";
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	@Transactional
	public Double getCylinderCapacity(Long cylinderId) {
		Session session = sessionFactory.getCurrentSession();
		Double result =0.0;
		try {
			String hql = "select capacity from Cylinder where cylinderId=:cId";
			Double capacity =(Double)session.createQuery(hql).setParameter("cId", cylinderId).uniqueResult();
			if(capacity instanceof Double) {
				result = capacity;
			}
			else {
				result =0.0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	@Transactional
	public DealerCylinderTxn dealerTxnCylinder(Long txID) {
		Session session = sessionFactory.getCurrentSession();
		DealerCylinderTxn result = new DealerCylinderTxn();
		try {
			Criteria crit = session.createCriteria(DealerCylinderTxn.class).add(Restrictions.eq("transactionId", txID));
			
			result = (DealerCylinderTxn)crit.uniqueResult();
			
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public String getUsageAndType(Long cylinderId) {
		Session session = sessionFactory.getCurrentSession();
		String result = "";
		try {
			String hql = "select cylinderType,usageStatus,damage from Cylinder where cylinderId=:cylId";
			Query query = session.createQuery(hql).setParameter("cylId", cylinderId);
			List list = query.list();
			Iterator i = list.iterator();
			String cylinderType= "";
			String usageStatus = "";
			String damage = "";
			if(i.hasNext()) {
				Object[] obj = (Object[])i.next();
				if(obj instanceof Object[]) {
				cylinderType = (String)obj[0];
				usageStatus = (String)obj[1];
				damage = (String) obj[2];
				}
			}
		result = (!damage.isEmpty())?(damage.equals("N")?(cylinderType+";"+usageStatus):("This Cylinder is damaged")):"NEW";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
