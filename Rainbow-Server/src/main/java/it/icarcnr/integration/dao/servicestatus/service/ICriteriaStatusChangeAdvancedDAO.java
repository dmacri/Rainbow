package it.icarcnr.integration.dao.servicestatus.service;

import it.icarcnr.integration.dao.generated.CriteriaChangeStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface ICriteriaStatusChangeAdvancedDAO {
	
	public List<CriteriaChangeStatus> getCriteriaStatusChangeByService(	Map criteriaMap, Date currentDate, Date startDetectionDate, Integer serviceId);

}


