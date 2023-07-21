/**
 * 
 */
package it.icarcnr.integration.dao.security.impl;

import it.icarcnr.integration.dao.generated.ActionNetworkFunction;
import it.icarcnr.integration.dao.generated.Area;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.security.service.IAreaAdvancedDAO;
import it.icarcnr.integration.dao.util.JPAUtil;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class AreaAdvancedDAO implements IAreaAdvancedDAO {

	/* (non-Javadoc)
	 * @see it.icarcnr.persistence.security.service.IAreaAdvancedDAO#findAll(java.util.List)
	 */
	public List<Area> findAll(List<Integer> enabledNetworkFunctionList) {
		EntityManagerHelper.log("List<Area> findAll(List<Integer> enabledNetworkFunctionList)", Level.INFO, null);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT DISTINCT actionNetworkFunction.action.area ");
			queryString.append("FROM ");
			queryString.append(ActionNetworkFunction.class.getName()+" as actionNetworkFunction ");	
			
			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			
			queryString.append(" WHERE ");
			queryString.append(" actionNetworkFunction.action.action IS NULL ");
			
			Boolean firstWhereCondition = Boolean.FALSE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "actionNetworkFunction.networkFunction.", "actionNetworkFunction.networkFunction.", firstWhereCondition);

			queryString.append(" ORDER BY  actionNetworkFunction.action.area.id ASC ");
			
			Query query = em.createQuery(queryString.toString());
			
			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("List<Area> findAll(List<Integer> enabledNetworkFunctionList)",
					Level.SEVERE, re);
			throw re;
		}
	}

}
