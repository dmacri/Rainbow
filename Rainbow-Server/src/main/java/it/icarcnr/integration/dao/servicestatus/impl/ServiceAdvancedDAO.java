package it.icarcnr.integration.dao.servicestatus.impl;

import it.icarcnr.business.servicestatus.bean.ServiceStatusBean;
import it.icarcnr.integration.dao.generated.Criteria;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Node;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.servicestatus.bean.LightServiceSatusBean;
import it.icarcnr.integration.dao.servicestatus.service.IServiceAdvancedDAO;
import it.icarcnr.integration.dao.util.DateUtil;
import it.icarcnr.integration.dao.util.IServiceConstants;
import it.icarcnr.integration.dao.util.IServiceConstants.Status;
import it.icarcnr.integration.dao.util.JPAUtil;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;
import it.icarcnr.util.JSONUtil;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceAdvancedDAO implements IServiceAdvancedDAO {
	
	private static final Log log = LogFactory.getLog(IServiceAdvancedDAO.class);


	public List<Service> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, Boolean orderByService, Boolean orderByNode, int... rowStartIdxAndCount) {
		EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, Boolean orderByService, Boolean orderByNode, int... rowStartIdxAndCount)", Level.INFO, null);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			
			// verify if associated criteria is active
			Time currentTime = new Time(currentDate.getTime());
			String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
			java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());

			Integer networkId = null;
			Integer functionId = null;
			Integer nodeTypeId = null;
			Integer serviceRequestId = null;
			Integer nodeServiceId =null;
			List<Integer> serviceIdList = null;

			if(criteriaMap!=null){				
				String[] mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.NODE_SENSOR_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					networkId = Integer.valueOf(mapValue[0]);
				}
				
				mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.SENSOR_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					functionId = Integer.valueOf(mapValue[0]);
				}
				
				mapValue = (String[])criteriaMap.get("nodeTypeId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					nodeTypeId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get("serviceRequestId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					serviceRequestId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get("nodeServiceId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					nodeServiceId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.SERVICE_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					serviceIdList = JSONUtil.getIntegerList(mapValue[0]);
				}
			}

			StringBuilder queryString = new StringBuilder();

			queryString.append("SELECT DISTINCT criteria.serviceOperation.service FROM "+Criteria.class.getName()+" as criteria ");

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
			if(nodeServiceId!=null){
				String condition = " criteria.serviceOperation.service.nodeByIdNode.id= :nodeServiceId ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
			}
			if(serviceRequestId!=null){
				String condition = " criteria.serviceOperation.service.request.id= :serviceRequestId ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
			}
			if(serviceIdList!=null && serviceIdList.size()>0){
				String condition = " criteria.serviceOperation.service.id in (:serviceIdList) ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
			}

			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);
			
			if(orderByService!=null && orderByService){
				queryString.append(" ORDER BY  criteria.serviceOperation.service.request.description ASC ");
			}else if (orderByNode!=null && orderByNode){
				queryString.append(" ORDER BY  criteria.serviceOperation.service.nodeByIdNode.description ASC ");
			}

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
			if(nodeServiceId!=null){
				query.setParameter("nodeServiceId", nodeServiceId);
			}
			if(serviceRequestId!=null){
				query.setParameter("serviceRequestId", serviceRequestId);
			}
			if(serviceIdList!=null && serviceIdList.size()>0){
				query.setParameter("serviceIdList", serviceIdList);
			}

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, Boolean orderByService, Boolean orderByNode, int... rowStartIdxAndCount)",
					Level.SEVERE, re);
			throw re;
		}
	}

	public Long getTotalServices(List<Integer> enabledNetworkFunctionList, Date currentDate,Integer idNode, Integer idFunction){
		EntityManagerHelper.log("getTotalServices(List<Integer> enabledNetworkFunctionList, Date currentDate,Integer idNode, Integer idFunction)", Level.INFO, null);
		
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			
			// verify if associated criteria is active
			Time currentTime = new Time(currentDate.getTime());
			String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
			java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());

			StringBuilder queryString = new StringBuilder();

			queryString.append(" SELECT COUNT( DISTINCT criteria.serviceOperation.service.id ) ");
			queryString.append(" FROM ");
			queryString.append(Criteria.class.getName());
			queryString.append(" as criteria ");
			
			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			
			// condition in order to verify if associated criteria is active
			queryString.append("WHERE criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			queryString.append("AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				queryString.append("AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			queryString.append("AND criteria.enabled = :trueValue ");
			// end condition in order to verify if associated criteria is active

			Boolean firstWhereCondition = Boolean.FALSE;
			
			String condition = " criteria.serviceOperation.service.nodeByIdNode.id = :idNode ";
			firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);

			if(idFunction!=null){
				condition = " criteria.serviceOperation.service.function.id = :idFunction ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);
			}
			
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);



			Query query = em.createQuery(queryString.toString());
			
			// set subQueryString parameters in order to verify if associated criteria is active
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);
			// end set subQueryString parameters in order to verify if associated criteria is active

			query.setParameter("idNode", idNode);
			if(idFunction!=null){
				query.setParameter("idFunction", idFunction);
			}
			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			return (Long)query.getSingleResult();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("getTotalServices(List<Integer> enabledNetworkFunctionList, Date currentDate,Integer idNode, Integer idFunction)",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<ServiceStatusBean> getServicesStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount) {
		EntityManagerHelper.log("getServicesStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount)", Level.INFO, null);
		Time currentTime = new Time(currentDate.getTime());
		String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
		java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			String sort = null;
			String dir = null;
			Integer nodeTypeId = null;
			Integer serviceRequestId = null;
			List<Integer> nodeIdList = null;
			Integer locationId = null;
			Integer serviceId = null;

			if(criteriaMap!=null){
				// Ordering criteria
				String[] mapValue = (String[])criteriaMap.get("sort");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					sort = mapValue[0];
				}
				mapValue = (String[])criteriaMap.get("dir");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					dir = mapValue[0];
				}

				mapValue = (String[])criteriaMap.get("nodeTypeId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					nodeTypeId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get("serviceRequestId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					serviceRequestId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get("nodeId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					nodeIdList = JSONUtil.getIntegerList(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get("locationId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					locationId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.SERVICE_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					serviceId = Integer.valueOf(mapValue[0]);
				}
			}

			StringBuilder queryString = new StringBuilder();

			queryString.append("SELECT NEW "+ServiceStatusBean.class.getName()+"(criteria.serviceOperation.service.id, MIN(criteria.status), criteria.serviceOperation.service.date, criteria.serviceOperation.service.request.description ) FROM "+Criteria.class.getName()+" as criteria ");

			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);

			queryString.append("WHERE criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			queryString.append("AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				queryString.append("AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			queryString.append("AND criteria.enabled = :trueValue ");

			Boolean firstWhereCondition = Boolean.FALSE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);

			if(nodeTypeId!=null){
				queryString.append("AND criteria.serviceOperation.service.nodeByIdNode.type.id = :nodeTypeId ");
			}
			if(serviceRequestId!=null){
				queryString.append(" AND criteria.serviceOperation.service.request.id= :serviceRequestId ");
			}
			if(nodeIdList!=null && nodeIdList.size()>0){
				queryString.append("AND criteria.serviceOperation.service.nodeByIdNode.id in (:nodeIdList) ");
			}
			if(locationId!=null){
				queryString.append("AND criteria.serviceOperation.service.nodeByIdNode.location.id = :locationId ");
			}
			if(serviceId!=null){
				queryString.append("AND criteria.serviceOperation.service.id = :serviceId ");
			}

			queryString.append(" GROUP BY criteria.serviceOperation.service.id, criteria.serviceOperation.service.date ");


			String orderCondition = " MIN(criteria.status) ";
			String orderDirCondition = " DESC ";
			String orderByDescription = " criteria.serviceOperation.service.request.description ASC ";
			if(sort!=null && dir!=null){
				orderDirCondition = dir;
				if(sort.equals("status")){
					orderCondition = " MIN(criteria.status) "+ orderDirCondition + ", "+orderByDescription ;
				}else if(sort.equals("lastCheck")){
					orderCondition = " criteria.serviceOperation.service.date "+ orderDirCondition + ", "+orderByDescription ;
				}else if(sort.equals("description")){
					orderCondition = " criteria.serviceOperation.service.request.description "+ orderDirCondition;
				}
			}

			queryString.append(" ORDER BY " + orderCondition );


			Query query = em.createQuery(queryString.toString());
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);

			if(nodeTypeId!=null){
				query.setParameter("nodeTypeId", nodeTypeId);
			}
			if(serviceRequestId!=null){
				query.setParameter("serviceRequestId", serviceRequestId);
			}
			if(nodeIdList!=null && nodeIdList.size()>0){
				query.setParameter("nodeIdList", nodeIdList);
			}
			if(locationId!=null){
				query.setParameter("locationId", locationId);
			}
			if(serviceId!=null){
				query.setParameter(IParameterHttpServletRequestContants.SERVICE_ID, serviceId);
			}

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			JPAUtil.setPagination(query, rowStartIdxAndCount);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("getServicesStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount)", Level.SEVERE, re);
			throw re;
		}

	}

	public Long getTotalServiceStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap) {
		EntityManagerHelper.log("getTotalServiceStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap)", Level.INFO, null);
		Time currentTime = new Time(currentDate.getTime());
		String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
		java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			Integer nodeTypeId = null;
			Integer serviceRequestId = null;
			Integer functionId = null;
			List<Integer> nodeIdList = null;
			Integer locationId = null;
			Integer serviceId = null;

			if(criteriaMap!=null){

				String[] mapValue = (String[])criteriaMap.get("nodeTypeId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					nodeTypeId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get("serviceRequestId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					serviceRequestId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get("nodeId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					nodeIdList = JSONUtil.getIntegerList(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get("locationId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					locationId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.SERVICE_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					serviceId = Integer.valueOf(mapValue[0]);
				}
			}


			StringBuilder queryString = new StringBuilder();
			queryString.append(" SELECT COUNT( DISTINCT criteria.serviceOperation.service.id ) ");
			queryString.append(" FROM ");
			queryString.append(Criteria.class.getName());
			queryString.append(" as criteria ");
			
			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			
			queryString.append("WHERE criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			queryString.append("AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				queryString.append("AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			queryString.append("AND criteria.enabled = :trueValue ");

			Boolean firstWhereCondition = Boolean.FALSE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);

			if(nodeTypeId!=null){
				queryString.append("AND criteria.serviceOperation.service.nodeByIdNode.type.id = :nodeTypeId ");
			}
			if(serviceRequestId!=null){
				queryString.append(" AND criteria.serviceOperation.service.request.id= :serviceRequestId ");
			}
			if(nodeIdList!=null && nodeIdList.size()>0){
				queryString.append("AND criteria.serviceOperation.service.nodeByIdNode.id in (:nodeIdList) ");
			}
			if(locationId!=null){
				queryString.append("AND criteria.serviceOperation.service.nodeByIdNode.location.id = :locationId ");
			}
			if(serviceId!=null){
				queryString.append("AND criteria.serviceOperation.service.id = :serviceId ");
			}


			Query query = em.createQuery(queryString.toString());
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);
			
			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			if(nodeTypeId!=null){
				query.setParameter("nodeTypeId", nodeTypeId);
			}
			if(serviceRequestId!=null){
				query.setParameter("serviceRequestId", serviceRequestId);
			}
			if(nodeIdList!=null && nodeIdList.size()>0){
				query.setParameter("nodeIdList", nodeIdList);
			}
			if(locationId!=null){
				query.setParameter("locationId", locationId);
			}
			if(serviceId!=null){
				query.setParameter(IParameterHttpServletRequestContants.SERVICE_ID, serviceId);
			}

			return (Long)query.getSingleResult();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("getTotalServiceStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap)",
					Level.SEVERE, re);
			throw re;
		}

	}


	public Long getTotalServiceByStatus(List<Integer> enabledNetworkFunctionList,Integer networkId, Date currentDate, String status) {
		EntityManagerHelper.log("getTotalServiceByStatus(List<Integer> enabledNetworkFunctionList, Ingerger networkId, Date currentDate, String status)", Level.INFO, null);
		Time currentTime = new Time(currentDate.getTime());
		String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
		java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT criteria.serviceOperation.service.id, MIN(criteria.status) ");
			queryString.append("FROM "+Criteria.class.getName()+" as criteria ");
			
			
			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			
			queryString.append("WHERE criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			queryString.append("AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				queryString.append("AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			queryString.append("AND criteria.enabled = :trueValue ");
			if(networkId!=null){
				queryString.append("AND criteria.serviceOperation.service.nodeByIdNode.network.id = :networkId");
				
			}
			
			Boolean firstWhereCondition = Boolean.FALSE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);

			queryString.append(" GROUP BY criteria.serviceOperation.service.id   ");
			if(status!=null){
				queryString.append(" HAVING MIN(criteria.status)= :status ");
			}

			Query query = em.createQuery(queryString.toString());
			
			if(networkId!=null){
				query.setParameter("networkId",networkId);
			}
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			if(status!=null){
				query.setParameter("status", status);
			}

			return new Long(query.getResultList().size());
		} catch (RuntimeException re) {
			EntityManagerHelper.log("getTotalServiceByStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, String status)",
					Level.SEVERE, re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see it.icarcnr.persistence.servicestatus.service.IServiceAdvancedDAO#findById(java.util.List, java.lang.Integer)
	 */
	public Service findById(List<Integer> enabledNetworkFunctionList, Integer serviceId) {
		EntityManagerHelper.log("findById(List<Integer> enabledNetworkFunctionList, Integer serviceId)", Level.INFO, null);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			StringBuilder queryString = new StringBuilder();

			queryString.append("SELECT service FROM "+Service.class.getName()+" as service ");

			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			
			queryString.append(" WHERE service.id = :serviceId ");

			Boolean firstWhereCondition = Boolean.FALSE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "service.nodeByIdNode.", "service.", firstWhereCondition);

			Query query = em.createQuery(queryString.toString());

			query.setParameter("serviceId", serviceId);

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			List<Service> services = query.getResultList();
			if(services.size()==1){
				return services.get(0);
			}else if(services.size() > 1){
				log.error("findById(List<Integer> enabledNetworkFunctionList, Integer serviceId) returned more than one result.");
			}
			return null;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("findById(List<Integer> enabledNetworkFunctionList, Integer serviceId)",
					Level.SEVERE, re);
			throw re;
		}
	}
	
	public List<Service> findByUtility(List<Integer> enabledNetworkFunctionList, Integer idUtility){
		
		EntityManagerHelper.log("findByUtility(List<Integer> enabledNetworkFunctionList,Integer idUtility)", Level.INFO, null);
		
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT service FROM "+Service.class.getName()+" as service ");
			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			
			String condition = "";
			Boolean firstWhereCondition = Boolean.TRUE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "service.nodeByIdNode.", null, firstWhereCondition);
			condition = "service.utility.id = :utilitylabel";
			firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
			
			Query query = em.createQuery(queryString.toString());
			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);
			query.setParameter("utilitylabel", idUtility);
			
			return query.getResultList();
			
		} catch (RuntimeException re) {
			EntityManagerHelper.log("findByUtility(List<Integer> enabledNetworkFunctionList,Integer idUtility)",
					Level.SEVERE, re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see it.icarcnr.persistence.servicestatus.service.IServiceAdvancedDAO#getCurrentStatus(java.util.List, java.lang.Integer)
	 */
	public Status getCurrentStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Integer serviceId) {
		EntityManagerHelper.log("getCurrentStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Integer serviceId)", Level.INFO, null);
		Time currentTime = new Time(currentDate.getTime());
		String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
		java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT MIN(criteria.status) ");
			queryString.append("FROM "+Criteria.class.getName()+" as criteria ");
			
			
			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			
			queryString.append("WHERE criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			queryString.append("AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				queryString.append("AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			queryString.append("AND criteria.enabled = :trueValue ");
			
			queryString.append("AND criteria.serviceOperation.service.id = :serviceId ");
			
			Boolean firstWhereCondition = Boolean.FALSE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);

			Query query = em.createQuery(queryString.toString());
			
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);
			query.setParameter("serviceId", serviceId);

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);
			
			Object result = query.getSingleResult();
			return IServiceConstants.Status.valueOf(((String)result).toUpperCase());
			
	    } catch(NoResultException e) {
	        return null;
	    } catch (RuntimeException re) {
			EntityManagerHelper.log("getTotalServiceByStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, String status)",
					Level.SEVERE, re);
			throw re;
		}

	}

	public List<LightServiceSatusBean> getServicesByStatus(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Integer nodeId, Date currentDate, String serviceStatus) {
		final String method = "List<LightServiceSatusBean> getServicesByStatus(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Integer nodeId, Date currentDate, String status)";
		EntityManagerHelper.log(method, Level.INFO, null);
		Time currentTime = new Time(currentDate.getTime());
		String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
		java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT NEW "+LightServiceSatusBean.class.getName()+"( criteria.serviceOperation.service, MIN(criteria.status) )");
			queryString.append("FROM "+Criteria.class.getName()+" as criteria ");
			
			
			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			
			queryString.append("WHERE criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			queryString.append("AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				queryString.append("AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			queryString.append(" AND criteria.enabled = :trueValue ");
			
			if(networkId!=null){
				queryString.append(" AND criteria.serviceOperation.service.nodeByIdNode.network.id = :networkId ");
			}
			
			if(functionId!=null){
				queryString.append(" AND criteria.serviceOperation.service.function.id = :functionId ");
			}
			
			if(nodeId!=null){
				queryString.append(" AND criteria.serviceOperation.service.nodeByIdNode.id = :nodeId ");
			}
			
			Boolean firstWhereCondition = Boolean.FALSE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);

			queryString.append(" GROUP BY criteria.serviceOperation.service  ");
			if(serviceStatus!=null){
				queryString.append(" HAVING MIN(criteria.status)= :status ");
			}

			Query query = em.createQuery(queryString.toString());
			
			if(networkId!=null){
				query.setParameter("networkId",networkId);
			}
			if(nodeId!=null){
				query.setParameter("nodeId",nodeId);
			}
			if(functionId!=null){
				query.setParameter("functionId",functionId);
			}
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			if(serviceStatus!=null){
				query.setParameter("status", serviceStatus);
			}
			return query.getResultList();
			
		} catch (RuntimeException re) {
			EntityManagerHelper.log(method, Level.SEVERE, re);
			throw re;
		}
	}
	public List<Node> findServiceReferenceNode(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, Boolean orderByService, Boolean orderByNode, int... rowStartIdxAndCount) {
		EntityManagerHelper.log("findServiceReferenceNode(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, Boolean orderByService, Boolean orderByNode, int... rowStartIdxAndCount)", Level.INFO, null);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			
			// verify if associated criteria is active
			Time currentTime = new Time(currentDate.getTime());
			String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
			java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());

			Integer networkId = null;
			Integer functionId = null;
			Integer nodeTypeId = null;
			Integer serviceRequestId = null;
			List<Integer> serviceIdList = null;

			if(criteriaMap!=null){				
				String[] mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.NODE_SENSOR_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					networkId = Integer.valueOf(mapValue[0]);
				}
				
				mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.SENSOR_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					functionId = Integer.valueOf(mapValue[0]);
				}
				
				mapValue = (String[])criteriaMap.get("nodeTypeId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					nodeTypeId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get("serviceRequestId");
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					serviceRequestId = Integer.valueOf(mapValue[0]);
				}
				mapValue = (String[])criteriaMap.get(IParameterHttpServletRequestContants.SERVICE_ID);
				if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
					serviceIdList = JSONUtil.getIntegerList(mapValue[0]);
				}
			}

			StringBuilder queryString = new StringBuilder();

			queryString.append("SELECT DISTINCT criteria.serviceOperation.service.nodeByIdNode FROM "+Criteria.class.getName()+" as criteria ");

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
			if(serviceRequestId!=null){
				String condition = " criteria.serviceOperation.service.request.id= :serviceRequestId ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
			}
			if(serviceIdList!=null && serviceIdList.size()>0){
				String condition = " criteria.serviceOperation.service.id in (:serviceIdList) ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
			}

			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "criteria.serviceOperation.service.nodeByIdNode.", "criteria.serviceOperation.service.", firstWhereCondition);
			
			if(orderByService!=null && orderByService){
				queryString.append(" ORDER BY  criteria.serviceOperation.service.request.description ASC ");
			}else if (orderByNode!=null && orderByNode){
				queryString.append(" ORDER BY  criteria.serviceOperation.service.nodeByIdNode.description ASC ");
			}

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
			if(serviceRequestId!=null){
				query.setParameter("serviceRequestId", serviceRequestId);
			}
			if(serviceIdList!=null && serviceIdList.size()>0){
				query.setParameter("serviceIdList", serviceIdList);
			}

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("findServiceReferenceNode(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, Boolean orderByService, Boolean orderByNode, int... rowStartIdxAndCount)",
					Level.SEVERE, re);
			throw re;
		}
	}
	

}