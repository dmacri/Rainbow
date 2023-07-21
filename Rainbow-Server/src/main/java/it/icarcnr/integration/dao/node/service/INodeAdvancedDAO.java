package it.icarcnr.integration.dao.node.service;

import it.icarcnr.integration.dao.generated.Node;
import it.icarcnr.integration.dao.generated.NodeRelation;
import it.icarcnr.integration.dao.generated.Type;

import java.util.Date;
import java.util.List;

public interface INodeAdvancedDAO {
	
	public List<Node> findByTypeAndLocation(Integer idType, Integer idLocation);
	
	public List<NodeRelation> findByParentAndLocation(Integer idParent, Integer idLocation);
	
	public List<Type> findNodeTypeByIdNetwork(Integer idNetwork);

	public List<Type> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Integer networkId, Integer functionId, int... rowStartIdxAndCount);

	/**
	 * @param enabledNetworkFunctionList
	 * @param idNetwork
	 * @return
	 */
	public List<Node> getRootNodes(List<Integer> enabledNetworkFunctionList, Integer idNetwork);
	
	public String getStatus(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Integer nodeId, Date currentDate);

}
