package it.icarcnr.business.servicestatus.service;

import it.icarcnr.business.servicestatus.bean.ServiceStatusChangeBean;
import it.icarcnr.integration.dao.generated.Request;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IServiceStatusChangeBL {
	
	public List<ServiceStatusChangeBean> getServiceStatusChangeList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap) throws Exception;
	public List<Request> getStatusChangeRequestList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap) throws Exception ;
	public Long getTotalServiceStatusChangeList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap) throws Exception;
	
}
