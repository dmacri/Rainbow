package it.icarcnr.business.node.service;

import it.icarcnr.integration.dao.generated.Function;
import it.icarcnr.integration.dao.generated.Node;
import it.icarcnr.integration.dao.generated.NodeRelation;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.generated.Type;
import it.icarcnr.integration.dao.util.IServiceConstants.Status;

import java.util.Date;
import java.util.List;

public interface INodeBL {

	public List<Type> findAll(int... rowStartIdxAndCount) throws Exception;
	
	public Boolean hasService(List<Integer> enabledNetworkFunctionList, Date currentDate, Node node, Function function, Boolean recursiveSearch) throws Exception;
	
	public Boolean hasService(List<Integer> enabledNetworkFunctionList, Date currentDate, List<NodeRelation> nodeRelationSet, Function function,Boolean recursiveSearch) throws Exception;
	
	public Boolean hasService(List<Integer> enabledNetworkFunctionList, Date currentDate, Type nodeType, Function function,Boolean recursiveSearch) throws Exception;
	
	public boolean containsSourceNode(List<Node> nodes, Service service) throws Exception;

	public List<Type> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Integer networkId, Integer functionId, int... rowStartIdxAndCount)throws Exception;

	/**
	 * @param node
	 * @return
	 */
	public Status getStatus(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Integer nodeId,  Date currentDate)throws Exception;

}
