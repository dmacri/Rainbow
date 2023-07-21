/**
 * 
 */
package it.icarcnr.integration.dao.function.service;

import it.icarcnr.integration.dao.generated.Function;
import it.icarcnr.integration.dao.generated.Sensore;

import java.util.List;


public interface IFunctionAdvancedDAO {
	
	List<Function> findAll(List<Integer> enabledNetworkFunctionList, Integer areaId, Integer networkId);
	
	List<Function> findAll(List<Integer> enabledNetworkFunctionList, Integer networkId);
	
	Function findById(List<Integer> enabledNetworkFunctionList, Integer functionId);
	
	List<Sensore> findBySensore(List<Integer> enabledNetworkFunctionList, Integer sensoreId);
}
