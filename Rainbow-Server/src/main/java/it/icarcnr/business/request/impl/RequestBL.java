/**
 * 
 */
package it.icarcnr.business.request.impl;

import it.icarcnr.business.request.service.IRequestBL;
import it.icarcnr.integration.dao.generated.Request;
import it.icarcnr.integration.dao.request.impl.RequestAdvancedDAO;
import it.icarcnr.integration.dao.request.service.IRequestAdvancedDAO;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Graziano
 *
 */
public class RequestBL implements IRequestBL {
	
	private static final Log log = LogFactory.getLog(RequestBL.class);

	/* (non-Javadoc)
	 * @see it.icarcnr.business.request.service.IRequestBL#findAll(java.util.List, java.util.Map, int[])
	 */
	public List<Request> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate,
			Map criteriaMap, int... rowStartIdxAndCount) throws Exception {
		final String method = "findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount)";
		log.info(method);
		try{
			IRequestAdvancedDAO iRequestAdvancedDAO = new RequestAdvancedDAO();
			return iRequestAdvancedDAO.findAll(enabledNetworkFunctionList, currentDate, criteriaMap, rowStartIdxAndCount);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

}
