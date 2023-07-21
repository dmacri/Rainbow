/**
 * 
 */
package it.icarcnr.integration.dao.serviceoperation.service;

import it.icarcnr.integration.dao.generated.ServiceOperation;

import java.util.List;


public interface IServiceOperationAdvancedDAO {
	
	public Integer getServiceOperationId(List<Integer> enabledNetworkFunctionList, Integer serviceId, String operation );
	
	public List<ServiceOperation> findAll(List<Integer> enabledNetworkFunctionList, Integer serviceId);

}
