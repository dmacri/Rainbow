package it.icarcnr.integration.dao.district.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.icarcnr.integration.dao.district.service.IDistrictAdvancedDAO;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
//import it.icarcnr.integration.dao.generated.JobDAO;
//import it.icarcnr.integration.dao.generated.Prefix;
import it.icarcnr.integration.dao.util.JPAUtil;

public class DistrictAdvancedDAO implements IDistrictAdvancedDAO{

	private static final Log log = LogFactory.getLog(IDistrictAdvancedDAO.class);

//	public List<Prefix> findAll(List<Integer> enabledNetworkFunctionList, String prefixValue, int... rowStartIdxAndCount) {
//		EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList, int... rowStartIdxAndCount)", Level.INFO, null);
//		try {
//			EntityManager em = EntityManagerHelper.getEntityManager();
//
//			StringBuilder queryString = new StringBuilder();
//			queryString.append("SELECT p FROM "+Prefix.class.getName()+" as p ");
//			Boolean firstWhereCondition = Boolean.TRUE;
//
//			if ( (prefixValue!=null) && !prefixValue.equals("")){
//				String condition = "p.prefix like :queryStr";
//				firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);
//			}
//			Query querySQL = em.createQuery(queryString.toString());
//			if ( (prefixValue!=null) && !prefixValue.equals("")){
//				querySQL.setParameter("queryStr", prefixValue+"%");
//			}
//			JPAUtil.setPagination(querySQL, rowStartIdxAndCount);
//			return querySQL.getResultList();
//
//		} catch (RuntimeException re) {
//			EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList, Map criteriaMap, Boolean orderByService, Boolean orderByNode, int... rowStartIdxAndCount)",
//					Level.SEVERE, re);
//			throw re;
//		}
	//}



//	public Long getTotalDistrict(List<Integer> enabledNetworkFunctionList,String prefixValue) {
//		EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList)", Level.INFO, null);
//		try {
//			EntityManager em = EntityManagerHelper.getEntityManager();
//
//			StringBuilder queryString = new StringBuilder();
//			queryString.append("SELECT COUNT ( p ) FROM "+Prefix.class.getName()+" as p ");
//			Boolean firstWhereCondition = Boolean.TRUE;
//			
//			if ( (prefixValue!=null) && !prefixValue.equals("")){
//				String condition = "p.prefix like :queryStr";
//				firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);
//			}
//			Query query = em.createQuery(queryString.toString());
//			if ( (prefixValue!=null) && !prefixValue.equals("")){
//				query.setParameter("queryStr", prefixValue+"%");
//			}
//			return (Long)query.getSingleResult();
//
//		} catch (RuntimeException re) {
//			EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList)", Level.INFO, null);
//			throw re;
//		}
//	}

}
