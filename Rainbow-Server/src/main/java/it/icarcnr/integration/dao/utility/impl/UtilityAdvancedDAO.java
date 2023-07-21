/**
 * 
 */
package it.icarcnr.integration.dao.utility.impl;

import it.icarcnr.integration.dao.generated.Action;
import it.icarcnr.integration.dao.generated.ActionNetworkFunction;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Utility;
import it.icarcnr.integration.dao.generated.UtilityAnf;
import it.icarcnr.integration.dao.security.util.IAreaConstants;
import it.icarcnr.integration.dao.util.JPAUtil;
import it.icarcnr.integration.dao.utility.service.IUtilityAdvancedDAO;
import it.icarcnr.presentation.action.util.IUtilityActionConstants;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class UtilityAdvancedDAO implements IUtilityAdvancedDAO {

	/* (non-Javadoc)
	 * @see it.icarcnr.persistence.utility.service.IUtilityAdvancedDAO#getUtilities(java.util.List, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public List<Utility> getUtilities(List<Integer> enabledNetworkFunctionList, String actionPath, Integer networkId) {
		EntityManagerHelper.log("getUtilities(List<Integer> enabledNetworkFunctionList, String actionPath, Integer networkId)", Level.INFO, null);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT utility FROM "+Utility.class.getName()+" as utility");
			queryString.append(" ,"+Action.class.getName()+" as action ");
			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
			Boolean firstWhereCondition = Boolean.TRUE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "utility.", null, firstWhereCondition);
			
			String condition = "";
			if (networkId!=null) {
				condition = "utility.network.id = :networkId";
				JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
			}
			if (actionPath!=null) {
				condition = "action.path = :actionPath";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
				condition = "utility.utilityGroup.id = action.utilityGroup.id";
				firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
			}
			
			Query query = em.createQuery(queryString.toString());
			
			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);
			if (networkId!=null) { query.setParameter("networkId", networkId); }
			if (actionPath!=null) { query.setParameter("actionPath", actionPath); }
			
			return query.getResultList();
			
		} catch (RuntimeException re) {
			EntityManagerHelper.log("getUtilities(List<Integer> enabledNetworkFunctionList, String actionPath, Integer networkId)",
					Level.SEVERE, re);
			throw re;
		}
	}
	
	public List<UtilityAnf> getUtilityAnf(List<Integer> enabledNetworkFunctionList,  Integer networkId, Integer idUtility, String actionPath)
	throws Exception {
EntityManagerHelper.log("getUtilityAnf(List<Integer> enabledNetworkFunctionList,  Integer networkId, Integer idUtility, String actionPath)", Level.INFO, null);
try {
	EntityManager em = EntityManagerHelper.getEntityManager();
	StringBuilder queryString = new StringBuilder();
	queryString.append("SELECT utilityAnf FROM "+UtilityAnf.class.getName()+" as utilityAnf");
	JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
	Boolean firstWhereCondition = Boolean.TRUE;
	firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "utilityAnf.utility.", null, firstWhereCondition);
	
	String condition = "";
	if (networkId!=null) {
		condition = "utilityAnf.utility.network.id = :networkId";
		firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
	}
	if (actionPath!=null) {
		condition = "utilityAnf.actionNetworkFunction.action.path = :actionPath";
		firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
	}
	if (idUtility!=null) {
		condition = "utilityAnf.utility.id = :utilitylabel";
		JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
	}
	
	Query query = em.createQuery(queryString.toString());
	
	JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);
	if (actionPath!=null) { query.setParameter("networkId", networkId); }
	if (actionPath!=null) { query.setParameter("actionPath", actionPath); }
	if (idUtility!=null) { query.setParameter("utilitylabel", idUtility); }
	
	return query.getResultList();
	
} catch (RuntimeException re) {
	EntityManagerHelper.log("getUtilityAnf(List<Integer> enabledNetworkFunctionList,  Integer networkId, Integer idUtility, String actionPath)",
			Level.SEVERE, re);
	throw re;
}
}

public List<Action> getUtilityParentActions(List<Integer> enabledNetworkFunctionList, Integer idNetwork)
throws Exception {

EntityManagerHelper.log("getUtilityPanels(enabledNetworkFunctionList)", Level.INFO, null);
try {
	EntityManager em = EntityManagerHelper.getEntityManager();
	
	StringBuilder queryString = new StringBuilder();
	queryString.append("SELECT DISTINCT actionNetworkFunction.action FROM "+ActionNetworkFunction.class.getName()+" as actionNetworkFunction");
	JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);
	Boolean firstWhereCondition = Boolean.TRUE;
	firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "actionNetworkFunction.networkFunction.", null, firstWhereCondition);
	
	String condition = "actionNetworkFunction.action.area.id = :area";
	firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
	condition = "actionNetworkFunction.action.action.id = :utilityParent";
	firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
	condition = "actionNetworkFunction.action.utilityGroup.id IS NOT NULL";
	JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
	
	if (idNetwork != null){
	   condition = "actionNetworkFunction.networkFunction.network.id = :idNetwork";
	   firstWhereCondition = JPAUtil.addWhereCondition(queryString, firstWhereCondition, condition);
	}
	
	Query query = em.createQuery(queryString.toString());
	
	JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);
	query.setParameter("area", IAreaConstants.UTILITY_AREA);
	query.setParameter("utilityParent", it.icarcnr.integration.dao.util.IUtilityConstants.ID_UTILITY_ACTION_PARENT);
    query.setParameter("idNetwork",idNetwork) ;
	return query.getResultList();
	
} catch (RuntimeException re) {
	EntityManagerHelper.log("getUtilityPanels(enabledNetworkFunctionList)",
			Level.SEVERE, re);
	throw re;
}
}


}
