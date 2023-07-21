package it.icarcnr.integration.dao.security.impl;

import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.GroupAnf;
import it.icarcnr.integration.dao.security.service.IGroupAnfAdvancedDAO;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GroupAnfAdvancedDAO implements IGroupAnfAdvancedDAO{

	/* (non-Javadoc)
	 * @see it.icarcnr.persistence.security.service.IGroupAnfAdvancedDAO#getEnabledNetworkFunctionList(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public List<Integer> getEnabledNetworkFunctionList(Integer groupId, String actionPath, Integer networkId, Integer functionId) {
		final String method = "getEnabledNetworkFunctionList(Integer groupId, String actionPath, Integer networkId, Integer functionId)";
		EntityManagerHelper.log(method, Level.INFO, null);
		try {
			Boolean trueValue = Boolean.TRUE;
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT groupAnf.actionNetworkFunction.networkFunction.id ");
			queryString.append("FROM ");
			queryString.append(GroupAnf.class.getName()+" as groupAnf ");
			queryString.append(" WHERE ");
			queryString.append(" groupAnf.group.id = :groupId ");
			queryString.append(" AND ");
			queryString.append(" groupAnf.read = :trueValue  ");
			queryString.append(" AND ");
			queryString.append(" groupAnf.actionNetworkFunction.action.path = :actionPath ");


			if(networkId!=null){
				queryString.append(" AND ");
				queryString.append(" groupAnf.actionNetworkFunction.networkFunction.network.id = :networkId  ");
			}

			if(functionId!=null){
				queryString.append(" AND ");
				queryString.append(" groupAnf.actionNetworkFunction.networkFunction.function.id = :functionId  ");
			}

			Query query = em.createQuery(queryString.toString());

			query.setParameter("groupId", groupId);
			query.setParameter("trueValue", trueValue);
			query.setParameter("actionPath", actionPath);
			if(networkId!=null){
				query.setParameter("networkId", networkId);
			}

			if(functionId!=null){
				query.setParameter("functionId", functionId);
			}

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log(method, Level.SEVERE, re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see it.icarcnr.persistence.security.service.IGroupAnfAdvancedDAO#hasPermission(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public boolean hasPermission(Integer groupId, String actionPath, Integer networkId, Integer functionId) {
		final String method = "hasPermission(Integer groupId, String actionPath, Integer networkId, Integer functionId)";
		EntityManagerHelper.log(method, Level.INFO, null);
		try {
			Boolean trueValue = Boolean.TRUE;
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT COUNT(groupAnf.actionNetworkFunction.networkFunction.id) ");
			queryString.append("FROM ");
			queryString.append(GroupAnf.class.getName()+" as groupAnf ");
			queryString.append(" WHERE ");
			queryString.append(" groupAnf.group.id = :groupId ");
			queryString.append(" AND ");
			queryString.append(" groupAnf.read = :trueValue  ");
			queryString.append(" AND ");
			queryString.append(" groupAnf.actionNetworkFunction.action.path = :actionPath ");


			if(networkId!=null){
				queryString.append(" AND ");
				queryString.append(" groupAnf.actionNetworkFunction.networkFunction.network.id = :networkId  ");
			}

			if(functionId!=null){
				queryString.append(" AND ");
				queryString.append(" groupAnf.actionNetworkFunction.networkFunction.function.id = :functionId  ");
			}

			Query query = em.createQuery(queryString.toString());

			query.setParameter("groupId", groupId);
			query.setParameter("trueValue", trueValue);
			query.setParameter("actionPath", actionPath);
			if(networkId!=null){
				query.setParameter("networkId", networkId);
			}

			if(functionId!=null){
				query.setParameter("functionId", functionId);
			}


			Long count = (Long)query.getSingleResult();

			return count>0;
		} catch (RuntimeException re) {
			EntityManagerHelper.log(method, Level.SEVERE, re);
			throw re;

		}
	}

	/* (non-Javadoc)
	 * @see it.icarcnr.persistence.security.service.IGroupAnfAdvancedDAO#hasPermission(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public boolean hasPermission(Integer groupId, Integer areaId, Integer networkId,
			Integer functionId) {
		final String method = "hasPermission(Integer groupId, Integer areaId, Integer networkId, Integer functionId)";
		EntityManagerHelper.log(method, Level.INFO, null);
		try {
			Boolean trueValue = Boolean.TRUE;
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT COUNT(groupAnf.actionNetworkFunction.networkFunction.id) ");
			queryString.append("FROM ");
			queryString.append(GroupAnf.class.getName()+" as groupAnf ");
			queryString.append(" WHERE ");
			queryString.append(" groupAnf.group.id = :groupId ");
			queryString.append(" AND ");
			queryString.append(" groupAnf.read = :trueValue  ");
			queryString.append(" AND ");
			queryString.append(" groupAnf.actionNetworkFunction.action.area.id = :areaId ");
			queryString.append(" AND ");
			queryString.append(" groupAnf.actionNetworkFunction.action.action IS NULL ");

			if(networkId!=null){
				queryString.append(" AND ");
				queryString.append(" groupAnf.actionNetworkFunction.networkFunction.network.id = :networkId  ");
			}

			if(functionId!=null){
				queryString.append(" AND ");
				queryString.append(" groupAnf.actionNetworkFunction.networkFunction.function.id = :functionId  ");
			}

			Query query = em.createQuery(queryString.toString());

			query.setParameter("groupId", groupId);
			query.setParameter("trueValue", trueValue);
			query.setParameter("areaId", areaId);
			if(networkId!=null){
				query.setParameter("networkId", networkId);
			}

			if(functionId!=null){
				query.setParameter("functionId", functionId);
			}


			Long count = (Long)query.getSingleResult();

			return count>0;
			
		} catch (RuntimeException re) {
			EntityManagerHelper.log(method, Level.SEVERE, re);
			throw re;

		}
	}

}
