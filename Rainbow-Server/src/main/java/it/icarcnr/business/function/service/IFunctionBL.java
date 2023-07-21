/**
 * 
 */
package it.icarcnr.business.function.service;

import it.icarcnr.integration.dao.generated.Function;
import it.icarcnr.integration.dao.generated.Sensore;

import java.util.List;

  public interface IFunctionBL {
	
	public List<Function> findAll(List<Integer> enabledNetworkFunctionList, Integer networkId) throws Exception;
	public Function findById(List<Integer> enabledNetworkFunctionList, Integer functionId) throws Exception;
	public List<Sensore> findBySensore(List<Integer> enabledNetworkFunctionList, Integer sensorId);
}
