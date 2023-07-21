package it.icarcnr.business.security.service;

import java.util.List;



public interface ISecurityBL {
	
	public enum PermissionType{READ,WRITE};

	/**
	 * @param userId
	 * @param actionPath
	 * @param networkId
	 * @param functionId
	 * @return
	 */
	public boolean hasPermission(Integer userId, String actionPath, Integer networkId, Integer functionId)throws Exception;

	/**
	 * @param userId
	 * @param actionPath
	 * @param networkId
	 * @param functionId
	 * @return
	 */
	public List<Integer> getEnabledNetworkFunctionList(Integer userId, String actionPath, Integer networkId, Integer functionId) throws Exception;

	/**
	 * @param userId
	 * @param area
	 * @param networkId
	 * @param functionId
	 * @return
	 */
	public boolean hasPermission(Integer userId, Integer areaId, Integer networkId, Integer functionId)throws Exception;

}
