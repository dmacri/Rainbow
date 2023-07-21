package it.icarcnr.integration.dao.network.service;

import it.icarcnr.integration.dao.generated.Network;

import java.util.List;

public interface INetworkAdvancedDAO {

	/**
	 * @param areaId
	 * @param enabledNetworkFunctionList
	 * @return
	 */
	public List<Network> findAll(List<Integer> enabledNetworkFunctionList, int... rowStartIdxAndCount);

	/**
	 * @param enabledNetworkFunctionList
	 * @param networkId
	 * @return
	 */
	public Network findById(List<Integer> enabledNetworkFunctionList, Integer networkId);


}
