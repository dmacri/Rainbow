/**
 * 
 */
package it.icarcnr.integration.dao.security.service;

import it.icarcnr.integration.dao.generated.Area;

import java.util.List;


public interface IAreaAdvancedDAO {

	/**
	 * @param enabledNetworkFunctionList
	 * @return
	 */
	List<Area> findAll(List<Integer> enabledNetworkFunctionList);

}
