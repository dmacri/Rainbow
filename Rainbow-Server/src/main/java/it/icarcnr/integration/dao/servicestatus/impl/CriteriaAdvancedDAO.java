package it.icarcnr.integration.dao.servicestatus.impl;

import it.icarcnr.integration.dao.generated.Criteria;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.servicestatus.service.ICriteriaAdvancedDAO;
import it.icarcnr.integration.dao.util.DateUtil;
import it.icarcnr.integration.dao.util.JPAUtil;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CriteriaAdvancedDAO implements ICriteriaAdvancedDAO {
	
	private static final Log log = LogFactory.getLog(ICriteriaAdvancedDAO.class);


	public List<Criteria> getActiveCriterias(Date currentDate, Integer serviceId) {
		EntityManagerHelper.log("getActiveCriterias(Date currentDate, Integer serviceId)", Level.INFO, null);
		Time currentTime = new Time(currentDate.getTime());
		String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
		java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT ");
			queryString.append("criteria FROM "+Criteria.class.getName()+" as criteria ");

//			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);

			queryString.append("WHERE criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			queryString.append("AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				queryString.append("AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			queryString.append("AND criteria.enabled = :trueValue ");
			queryString.append("AND criteria.serviceOperation.service.id = :serviceId ");

//			Boolean firstWhereCondition = Boolean.FALSE;
//			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.node.", "criteria.serviceOperation.service.", firstWhereCondition);

			Query query = em.createQuery(queryString.toString());
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);
			query.setParameter(IParameterHttpServletRequestContants.SERVICE_ID, serviceId);

//			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("getActiveCriteria(Date currentDate, Integer serviceId)",
					Level.SEVERE, re);
			throw re;
		}
	}
	
	public Criteria findById(List<Integer> enabledNetworkFunctionList, Integer criteriaId){
		EntityManagerHelper.log("findById(List<Integer> enabledNetworkFunctionList, Integer criteriaId)", Level.INFO, null);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			StringBuilder queryString = new StringBuilder();
			queryString.append(" SELECT criteria FROM "+Criteria.class.getName()+" as criteria");
			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			queryString.append(" WHERE criteria.id = :criteriaId  ");
			
			Boolean firstWhereCondition = Boolean.FALSE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);

			Query query = em.createQuery(queryString.toString());
			query.setParameter(IParameterHttpServletRequestContants.CRITERIA_ID, criteriaId);
			
			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			Criteria result = null;
			List<Criteria> list = query.getResultList();
			if(list.size() == 1){
				result = list.get(0);
			}else if(list.size() > 1){
				log.error("findById(List<Integer> enabledNetworkFunctionList, Integer criteriaId) returned more than one result.");
			}
			return result;

		} catch (RuntimeException re) {
			EntityManagerHelper.log("findById(List<Integer> enabledNetworkFunctionList, Integer criteriaId)",
					Level.SEVERE, re);
			throw re;
		}
		
	}

	@Override
	public Criteria getActiveCriteria(Date currentDate, Integer serviceOperationId) {
		EntityManagerHelper.log("getActiveCriteria(Date currentDate, Integer serviceOperationId)", Level.INFO, null);
		Time currentTime = new Time(currentDate.getTime());
		String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
		java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT ");
			queryString.append("criteria FROM "+Criteria.class.getName()+" as criteria ");

//			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);

			queryString.append("WHERE criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			queryString.append("AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				queryString.append("AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			queryString.append("AND criteria.enabled = :trueValue ");
			queryString.append("AND criteria.serviceOperation.id = :serviceOperationId ");

//			Boolean firstWhereCondition = Boolean.FALSE;
//			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.node.", "criteria.serviceOperation.service.", firstWhereCondition);

			Query query = em.createQuery(queryString.toString());
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);
			query.setParameter("serviceOperationId", serviceOperationId);

//			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);
			Criteria result = null;
			List<Criteria> list = query.getResultList();
			if(list.size() == 1){
				result = list.get(0);
			}else if(list.size() > 1){
				log.error("getActiveCriteria(Date currentDate, Integer serviceOperationId)");
			}
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("getActiveCriteria(Date currentDate, Integer serviceOperationId)",
					Level.SEVERE, re);
			throw re;
		}
	}




}
