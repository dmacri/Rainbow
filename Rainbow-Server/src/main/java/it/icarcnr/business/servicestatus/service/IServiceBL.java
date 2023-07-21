package it.icarcnr.business.servicestatus.service;

import it.icarcnr.business.servicestatus.bean.ServiceStatusBean;
import it.icarcnr.integration.dao.generated.Node;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.util.IServiceConstants.Status;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IServiceBL {

	public List<ServiceStatusBean> getServiceStatus (List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount) throws Exception;

	public Long getTotalServiceStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap) throws Exception;

	public Service findById(Integer id) throws Exception;

	public List<Service> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, Boolean orderByService, Boolean orderByNode, int... rowStartIdxAndCount) throws Exception;

	public Long getTotalServiceByStatus(List<Integer> enabledNetworkFunctionList, Integer networkId, Date currentDate, String status) throws Exception;

	public Boolean isTakingCharge(Service service)throws Exception;

	public String getSources(Service service) throws Exception;

	public Boolean logPresent(Service service)throws Exception;
	
	public List<Service> findByUtility(List<Integer> enabledNetworkFunctionList, Integer idUtility) throws Exception;
	
	public Status getCurrentStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Integer idService) throws Exception;

	public List<Node> findServiceReferenceNode(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, Boolean orderByService, Boolean orderByNode, int... rowStartIdxAndCount) throws Exception;
	

}
