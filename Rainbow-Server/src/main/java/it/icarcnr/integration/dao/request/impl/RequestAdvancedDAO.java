/**
 * 
 */
package it.icarcnr.integration.dao.request.impl;

import it.icarcnr.integration.dao.generated.Criteria;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Request;
import it.icarcnr.integration.dao.request.service.IRequestAdvancedDAO;
import it.icarcnr.integration.dao.util.DateUtil;
import it.icarcnr.integration.dao.util.JPAUtil;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class RequestAdvancedDAO implements IRequestAdvancedDAO {

	/* (non-Javadoc)
	 * @see it.icarcnr.integration.dao.request.service.IRequestAdvancedDAO#findAll(java.util.List, java.util.Map, int[])
	 */
	public List<Request> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount) {
		EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount)", Level.INFO, null);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			
			// verify if associated criteria is active
			Time currentTime = new Time(currentDate.getTime());
			String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
			java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());

			Integer networkId = null;
			Integer functionId = null;
			Integer nodeTypeId = null;


			if(criteriaMap!=null){
				String[] mapValue = (String[])criteriaMap.get("nodeTypeId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					nodeTypeId = Integer.valueOf(mapValue[0]);
				}
				
				mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.NODE_SENSOR_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					networkId = Integer.valueOf(mapValue[0]);
				}
				
				mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.SENSOR_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					functionId = Integer.valueOf(mapValue[0]);
				}
			}

			StringBuilder queryString = new StringBuilder();

			queryString.append("SELECT DISTINCT criteria.serviceOperation.service.request FROM "+Criteria.class.getName()+" as criteria ");

			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);

			Boolean firstWhereCondition = Boolean.TRUE;
			
			
			// condition in order to verify if associated criteria is active
			StringBuilder activeCriteriaConditition = new StringBuilder();
			activeCriteriaConditition.append(" criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			activeCriteriaConditition.append(" AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				activeCriteriaConditition.append(" AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			activeCriteriaConditition.append("AND criteria.enabled = :trueValue ");
			
			firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, activeCriteriaConditition.toString());
			// end condition in order to verify if associated criteria is active
			
			
			if(networkId!=null){
				String condition = " criteria.serviceOperation.service.nodeByIdNode.network.id = :networkId ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);
			}
			if(functionId!=null){
				String condition = " criteria.serviceOperation.service.function.id = :functionId ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);
			}
			if(nodeTypeId!=null){
				String condition = " criteria.serviceOperation.service.nodeByIdNode.type.id = :nodeTypeId ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);
			}

			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);

			queryString.append(" ORDER BY  criteria.serviceOperation.service.request.description ASC ");

			Query query = em.createQuery(queryString.toString());
			
			// set condition parameters in order to verify if associated criteria is active
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);
			// end set condition parameters in order to verify if associated criteria is active

			if(networkId!=null){
				query.setParameter("networkId", networkId);
			}
			if(functionId!=null){
				query.setParameter("functionId", functionId);
			}
			if(nodeTypeId!=null){
				query.setParameter("nodeTypeId", nodeTypeId);
			}

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount)",
					Level.SEVERE, re);
			throw re;
		}
	}

}
