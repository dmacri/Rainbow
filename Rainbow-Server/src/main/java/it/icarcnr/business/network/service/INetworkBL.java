package it.icarcnr.business.network.service;

import it.icarcnr.business.network.bean.AlarmedNodeBean;
import it.icarcnr.business.network.bean.NetworkItemBean;
import it.icarcnr.integration.dao.generated.Network;
import it.icarcnr.integration.dao.generated.Node;

import java.util.Date;
import java.util.List;

public interface INetworkBL {
	
	public Network findById(Integer networkId) throws Exception;
	
	public Network findById(List<Integer> enabledNetworkFunctionList, Integer networkId) throws Exception;
	
	public List<Network> findAll(List<Integer> enabledNetworkFunctionList, int... rowStartIdxAndCount) throws Exception;
	
	public List<Node> getRootNodes (List<Integer> enabledNetworkFunctionList, Integer idNetwork) throws Exception;
	
	public NetworkItemBean getNetworkItemBean (List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Date currentDate) throws Exception;
	
	public List<AlarmedNodeBean> getAlarmedNodes(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Date currentDate) throws Exception;

	public List<AlarmedNodeBean> getAlarmedNodes(List<Integer> enabledNetworkFunctionList, NetworkItemBean networkItemBean, Date currentDate) throws Exception;

	
}
