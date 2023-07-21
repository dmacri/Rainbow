/**
 * 
 */
package it.icarcnr.integration.dao.utility.service;

import it.icarcnr.integration.dao.generated.Action;
import it.icarcnr.integration.dao.generated.Utility;
import it.icarcnr.integration.dao.generated.UtilityAnf;

import java.util.List;


public interface IUtilityAdvancedDAO {
	
	public List<Action> getUtilityParentActions(List<Integer> enabledNetworkFunctionList, Integer idNetwork) throws Exception;
	
	public List<UtilityAnf> getUtilityAnf(List<Integer> enabledNetworkFunctionList,  Integer networkId, Integer idUtility, String actionPath) throws Exception;

	public List<Utility> getUtilities (List<Integer> enabledNetworkFunctionList, String actionPath, Integer networkId);

}
