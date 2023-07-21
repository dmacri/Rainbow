/**
 * 
 */
package it.icarcnr.business.request.service;

import it.icarcnr.integration.dao.generated.Request;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface IRequestBL {
	public List<Request> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount) throws Exception;

}
