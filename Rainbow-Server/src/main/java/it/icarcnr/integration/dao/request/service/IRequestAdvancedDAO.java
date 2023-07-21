/**
 * 
 */
package it.icarcnr.integration.dao.request.service;

import it.icarcnr.integration.dao.generated.Request;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface IRequestAdvancedDAO {
	
	public List<Request> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, final int... rowStartIdxAndCount);


}
