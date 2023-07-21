package it.icarcnr.integration.dao.servicestatus.service;

import it.icarcnr.integration.dao.generated.Request;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.servicestatus.bean.CriteriaHistoryBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IServiceStatusChangeAdvancedDAO {
	public List<Request> getStatusChangeRequestList(List<Integer> enabledNetworkFunctionList,  Date currentDate, Date startDetectionDate, Map criteriaMap);
	public List<Service> getServiceStatusChangeList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap);
	public Long getTotalServiceStatusChangeList(List<Integer> enabledNetworkFunctionList,  Date currentDate, Date startDetectionDate, Map criteriaMap);
	public CriteriaHistoryBean getServiceHistory (Integer serviceOperationId, Date currentDate,Integer networkId); 
}
