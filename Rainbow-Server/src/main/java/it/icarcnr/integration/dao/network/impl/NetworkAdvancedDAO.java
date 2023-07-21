/**
 * 
 */
package it.icarcnr.integration.dao.network.impl;

import it.icarcnr.integration.dao.generated.ActionNetworkFunction;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Network;
import it.icarcnr.integration.dao.network.service.INetworkAdvancedDAO;
import it.icarcnr.integration.dao.util.JPAUtil;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class NetworkAdvancedDAO implements INetworkAdvancedDAO {


	private static final Log log = LogFactory.getLog(NetworkAdvancedDAO.class);

	public List<Network> findAll(List<Integer> enabledNetworkFunctionList,  int... rowStartIdxAndCount) {
		EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList, int... rowStartIdxAndCount)", Level.INFO, null);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT DISTINCT actionNetworkFunction.networkFunction.network ");
			queryString.append("FROM ");
			queryString.append(ActionNetworkFunction.class.getName()+" as actionNetworkFunction ");

			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);

			Boolean firstWhereCondition = Boolean.TRUE;


			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "actionNetworkFunction.networkFunction.", null, firstWhereCondition);

			queryString.append(" ORDER BY  actionNetworkFunction.networkFunction.network.id ");

			Query query = em.createQuery(queryString.toString());

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);



			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("findAll(List<Integer> enabledNetworkFunctionList, int... rowStartIdxAndCount)",
					Level.SEVERE, re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see it.icarcnr.persistence.network.service.INetworkAdvancedDAO#findById(java.util.List, java.lang.Integer)
	 */
	public Network findById(List<Integer> enabledNetworkFunctionList, Integer networkId) {
		EntityManagerHelper.log("findById(List<Integer> enabledNetworkFunctionList, Integer networkId)", Level.INFO, null);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT DISTINCT actionNetworkFunction.networkFunction.network ");
			queryString.append("FROM ");
			queryString.append(ActionNetworkFunction.class.getName()+" as actionNetworkFunction ");

			JPAUtil.addSecurityFrom(enabledNetworkFunctionList, queryString);

			Boolean firstWhereCondition = Boolean.TRUE;
			firstWhereCondition = JPAUtil.addSecurityCondition(enabledNetworkFunctionList, queryString, "actionNetworkFunction.networkFunction.", null, firstWhereCondition);

			String condition = " actionNetworkFunction.networkFunction.network.id = :networkId ";
			firstWhereCondition = JPAUtil.addWhereCondition(queryString,firstWhereCondition, condition);


			Query query = em.createQuery(queryString.toString());

			query.setParameter("networkId", networkId);

			JPAUtil.addSecurityParameter(enabledNetworkFunctionList, query);

			List<Network> list = query.getResultList();
			if(list.size()==1){
				return list.get(0);
			}else if(list.size() > 1){
				log.error("findById(List<Integer> enabledNetworkFunctionList, Integer networkId) returned more than one result.");
			}
			return null;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("findById(List<Integer> enabledNetworkFunctionList, Integer networkId)",
					Level.SEVERE, re);
			throw re;
		}
	}

}
