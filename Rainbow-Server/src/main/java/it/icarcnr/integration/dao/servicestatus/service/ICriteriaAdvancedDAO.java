package it.icarcnr.integration.dao.servicestatus.service;

import it.icarcnr.integration.dao.generated.Criteria;

import java.util.Date;
import java.util.List;

public interface ICriteriaAdvancedDAO {
	
	public Criteria getActiveCriteria(Date currentDate, Integer serviceOperationId);

	public List<Criteria> getActiveCriterias(Date currentDate, Integer serviceId);
	
	public Criteria findById(List<Integer> enabledNetworkFunctionList, Integer criteriaId);

}
