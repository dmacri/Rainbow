package it.icarcnr.business.servicestatus.impl;

import it.icarcnr.business.servicestatus.service.ICriteriaStatusBL;
import it.icarcnr.integration.dao.generated.Criteria;
import it.icarcnr.integration.dao.servicestatus.impl.CriteriaAdvancedDAO;
import it.icarcnr.integration.dao.servicestatus.service.ICriteriaAdvancedDAO;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CriteriaStatusBL implements ICriteriaStatusBL{

	private static final Log log = LogFactory.getLog(CriteriaStatusBL.class);

	public List<Criteria> getActiveCriterias(Date currentDate, Integer serviceId) throws Exception {
		final String method = "getActiveCriterias(Date currentDate, Integer id)";
		log.info(method);
		try{
			ICriteriaAdvancedDAO iCriteriaDAO = new CriteriaAdvancedDAO();
			return iCriteriaDAO.getActiveCriterias(currentDate,serviceId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}
	public Criteria findById(List<Integer> enabledNetworkFunctionList,Integer criteriaId) throws Exception {
		final String method = "findById(List<Integer> enabledNetworkFunctionList,Integer criteriaId)";
		log.info(method);
		try{
			ICriteriaAdvancedDAO iCriteriaAdvancedDAO = new CriteriaAdvancedDAO();
			return iCriteriaAdvancedDAO.findById(enabledNetworkFunctionList, criteriaId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}
	@Override
	public Criteria getActiveCriteria(Date currentDate,
			Integer serviceOperationId) throws Exception {
		final String method = "getActiveCriteria(Date currentDate, Integer serviceOperationId)";
		log.info(method);
		try{
			ICriteriaAdvancedDAO iCriteriaDAO = new CriteriaAdvancedDAO();
			return iCriteriaDAO.getActiveCriteria(currentDate,serviceOperationId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

}
