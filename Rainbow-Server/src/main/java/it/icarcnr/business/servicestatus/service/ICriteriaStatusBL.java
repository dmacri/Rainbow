package it.icarcnr.business.servicestatus.service;

import it.icarcnr.integration.dao.generated.Criteria;

import java.util.Date;
import java.util.List;


public interface ICriteriaStatusBL {
	
	public Criteria getActiveCriteria(Date currentDate, Integer serviceOperationId) throws Exception;
	
	public List<Criteria> getActiveCriterias(Date currentDate, Integer serviceId) throws Exception;
	
	public Criteria findById(List<Integer> enabledNetworkFunctionList, Integer criteriaId) throws Exception;

}
