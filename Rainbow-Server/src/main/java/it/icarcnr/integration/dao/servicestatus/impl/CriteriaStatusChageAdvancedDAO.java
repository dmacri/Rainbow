package it.icarcnr.integration.dao.servicestatus.impl;

import it.icarcnr.integration.dao.generated.Criteria;
import it.icarcnr.integration.dao.generated.CriteriaChangeStatus;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.servicestatus.service.ICriteriaStatusChangeAdvancedDAO;
import it.icarcnr.integration.dao.util.DateUtil;
import it.icarcnr.integration.dao.util.JPAUtil;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CriteriaStatusChageAdvancedDAO implements ICriteriaStatusChangeAdvancedDAO {


	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
	public List<CriteriaChangeStatus> getCriteriaStatusChangeByService(Map criteriaMap, Date currentDate, Date startDate, Integer serviceId) {
		EntityManagerHelper.log("getCriteriaStatusChangeByService(Map criteriaMap, Date currentDate, Date startDate, Integer serviceId)",
				Level.INFO, null);
		
		// verify if associated criteria is active
		Time currentTime = new Time(currentDate.getTime());
		String dayOfWeekString = DateUtil.getDayOfWeek(currentDate);
		java.sql.Date currentSqlDate = new java.sql.Date(currentTime.getTime());
		
		//Data about one week
		Calendar calendar = Calendar.getInstance();
		Integer period = 1;
		if(startDate!=null){
			calendar.setTime(startDate);
		}else if(criteriaMap!=null && criteriaMap.containsKey("period")){
			String[] mapValue = (String[])criteriaMap.get("period");
			if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
				period = Integer.valueOf(mapValue[0]);
			}
			calendar.add(Calendar.DATE, -period);
		}
		Boolean criticalChangeStatus=false;
		if(criteriaMap!=null && criteriaMap.containsKey("criticalChangeStatus")){
			String [] mapValue = (String[])criteriaMap.get("criticalChangeStatus");
			if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
				criticalChangeStatus = Boolean.valueOf(mapValue[0]);
			}
		}
		String criticalStatus =null;
		if(criteriaMap!=null && criteriaMap.containsKey("criticalStatus")){
			String [] mapValue = (String[])criteriaMap.get("criticalStatus");
			if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
				criticalStatus = String.valueOf(mapValue[0]);
			}
		}
		
		try {
			StringBuilder queryString = new StringBuilder("select criteriaChangeStatus ");
			queryString.append(" from ");
			queryString.append(CriteriaChangeStatus.class.getName()+" criteriaChangeStatus ");


			Boolean firstWhereCondition = Boolean.TRUE;
			
			
			// subQueryString verify if associated criteria is active
			
			StringBuilder subQueryString = new StringBuilder();
			
			subQueryString.append(" criteriaChangeStatus.serviceOperation.id = ");
			subQueryString.append(" ( ");
			subQueryString.append("SELECT criteria.serviceOperation.id ");
			subQueryString.append(" FROM ");
			subQueryString.append(Criteria.class.getName());
			subQueryString.append(" as criteria ");
			subQueryString.append("WHERE criteria.startDate <= :currentSqlDate AND criteria.endDate>= :currentSqlDate ");
			subQueryString.append("AND criteria.startTime <= :currentTime AND criteria.endTime>= :currentTime ");
			if(dayOfWeekString!=null){
				subQueryString.append("AND criteria."+dayOfWeekString+" = :trueValue ");
			}
			subQueryString.append("AND criteria.enabled = :trueValue ");
			subQueryString.append("AND criteria.serviceOperation.id = criteriaChangeStatus.serviceOperation.id ");
			
			subQueryString.append(" ) ");
			
			firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, subQueryString.toString());
			
			// end subQueryString verify if associated criteria is active
			
			
			String condition = null;
			if(startDate!=null){
				condition = " criteriaChangeStatus.dateDetection >= :startDate ";
			}else{
				condition = " criteriaChangeStatus.date >= :startDate ";
			}
			
			firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);

			if(serviceId!=null){
				condition = " criteriaChangeStatus.serviceOperation.service.id = :serviceId ";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);
			}
			if(criticalChangeStatus){
				 condition ="(criteriaChangeStatus.statusFrom= :criticalStatus OR criteriaChangeStatus.statusTo= :criticalStatus)";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
			}
			

			queryString.append(" ORDER BY criteriaChangeStatus.date ASC");

			Query query = getEntityManager().createQuery(queryString.toString());
			
			// set subQueryString parameters in order to verify if associated criteria is active
			query.setParameter("currentSqlDate", currentSqlDate);
			query.setParameter("currentTime", currentTime);
			query.setParameter("trueValue", Boolean.TRUE);
			//end set  subQueryString parameters
			
			query.setParameter("startDate", calendar.getTime());

			if(serviceId!=null && serviceId>0){
				query.setParameter(IParameterHttpServletRequestContants.SERVICE_ID, serviceId);
			}
			if(criticalChangeStatus){
				query.setParameter("criticalStatus", criticalStatus);
			}


			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("getCriteriaStatusChangeByService(Map criteriaMap, Date currentDate, Date startDate, Integer serviceId)", Level.SEVERE, re);
			throw re;
		}
	}

}
