package it.icarcnr.integration.dao.servicestatus.service;

import it.icarcnr.business.servicestatus.bean.ServiceStatusBean;
import it.icarcnr.integration.dao.generated.Node;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.servicestatus.bean.LightServiceSatusBean;
import it.icarcnr.integration.dao.util.IServiceConstants.Status;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IServiceAdvancedDAO {

	public List<Service> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, Boolean orderByService, Boolean orderByNode, final int... rowStartIdxAndCount);

	public Service findById(List<Integer> enabledNetworkFunctionList, Integer serviceId);

	public Long getTotalServices(List<Integer> enabledNetworkFunctionList, Date currentDate, Integer idNode, Integer idFunction);

	public List<ServiceStatusBean> getServicesStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap, int... rowStartIdxAndCount);

	public Long getTotalServiceStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap);

	public Long getTotalServiceByStatus(List<Integer> enabledNetworkFunctionList,Integer networkId, Date currentDate, String status);

	public List<LightServiceSatusBean> getServicesByStatus(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Integer nodeId, Date currentDate, String serviceStatus);

	public List<Service> findByUtility(List<Integer> enabledNetworkFunctionList, Integer idUtility);

	/**
	 * @param enabledNetworkFunctionList
	 * @param currentDate 
	 * @param serviceId
	 * @return
	 */
	public Status getCurrentStatus(List<Integer> enabledNetworkFunctionList, Date currentDate, Integer serviceId);

	public List<Node> findServiceReferenceNode(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap,	Boolean orderByService, Boolean orderByNode,int[] rowStartIdxAndCount);

}
