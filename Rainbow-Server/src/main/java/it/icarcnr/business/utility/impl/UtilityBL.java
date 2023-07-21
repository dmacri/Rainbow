package it.icarcnr.business.utility.impl;

import it.icarcnr.business.utility.service.IUtilityBL;
import it.icarcnr.integration.dao.cronjob.impl.CronjobAdvancedDAO;
import it.icarcnr.integration.dao.cronjob.service.ICronjobAdvancedDAO;
import it.icarcnr.integration.dao.generated.Action;
import it.icarcnr.integration.dao.generated.Cronjob;
import it.icarcnr.integration.dao.generated.CronjobDAO;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.ICronjobDAO;
import it.icarcnr.integration.dao.generated.UserDAO;
import it.icarcnr.integration.dao.generated.Utility;
import it.icarcnr.integration.dao.generated.UtilityAnf;
import it.icarcnr.integration.dao.utility.impl.UtilityAdvancedDAO;
import it.icarcnr.integration.dao.utility.service.IUtilityAdvancedDAO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;

public class UtilityBL implements IUtilityBL {

	private static final Log log = LogFactory.getLog(UtilityBL.class);
	
	public List<UtilityAnf> getUtilityAnf(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer idUtility, String actionPath) throws Exception {
		final String method = "getUtilityAnf(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer idUtility, String actionPath)";
		log.info(method);
		try {
			IUtilityAdvancedDAO iUtilityAdvancedDAO = new UtilityAdvancedDAO();
			return iUtilityAdvancedDAO.getUtilityAnf(enabledNetworkFunctionList, networkId, idUtility, actionPath);
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}
		
	}
	
	public List<Action> getUtilityParentActions(List<Integer> enabledNetworkFunctionList) throws Exception {
		final String method = "getUtilityParentActions(enabledNetworkFunctionList)";
		log.info(method);
		try {
			IUtilityAdvancedDAO iUtilityAdvancedDAO = new UtilityAdvancedDAO();
			return iUtilityAdvancedDAO.getUtilityParentActions(enabledNetworkFunctionList,null);
			
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}
	
	public void setCronjob(Integer idUser, String namejob, String nametrigger, Date date, Utility groupjob, String output, List<String> command, Cronjob parent) throws Exception {
		final String method = "setCronjob(....)";
		log.info(method);
		try {
			
			JSONArray commandArray = new JSONArray(command);
	        ICronjobDAO cjd = new CronjobDAO();
			List<Cronjob> listcronjob = cjd.findByNamejob(namejob);
			if (listcronjob.isEmpty()) {
				Cronjob cj = new Cronjob();
				EntityManagerHelper.beginTransaction();
				cjd.save(cj);			
				EntityManagerHelper.commit();
			}
			
		} catch (Exception e) {
			if ( EntityManagerHelper.getEntityManager().getTransaction().isActive() ){
				EntityManagerHelper.rollback();
			}
			log.error(method,e);
			throw e;
		}
		
	}
	
	public List<Cronjob> getParentCronjobs(List<Integer> enabledNetworkFunctionList,Integer utilityGroupId, Cronjob cronjob, String view) throws Exception {
		final String method = "getParentCronjobs(....)";
		log.info(method);
		
		try {
			ICronjobAdvancedDAO iCronjobAdvancedDAO = new CronjobAdvancedDAO();
			return iCronjobAdvancedDAO.findByParentCronjob(enabledNetworkFunctionList, utilityGroupId, cronjob, view);
			
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see it.icarcnr.businesslogic.utility.service.IUtilityBL#getUtilities(java.util.List, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public List<Utility> getUtilities(List<Integer> enabledNetworkFunctionList, String actionPath, Integer networkId)
			throws Exception {
		final String method = "getUtilities(List<Integer> enabledNetworkFunctionList, String actionPath, Integer networkId)";
		log.info(method);
		try {
			IUtilityAdvancedDAO iUtilityAdvancedDAO = new UtilityAdvancedDAO();
			return iUtilityAdvancedDAO.getUtilities(enabledNetworkFunctionList, actionPath, networkId);
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

}
