package it.icarcnr.business.node.impl;

import it.icarcnr.business.node.service.INodeBL;
import it.icarcnr.integration.dao.generated.Function;
import it.icarcnr.integration.dao.generated.ITypeDAO;
import it.icarcnr.integration.dao.generated.Node;
import it.icarcnr.integration.dao.generated.NodeRelation;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.generated.ServiceNodeSource;
import it.icarcnr.integration.dao.generated.Type;
import it.icarcnr.integration.dao.generated.TypeDAO;
import it.icarcnr.integration.dao.node.impl.NodeAdvancedDAO;
import it.icarcnr.integration.dao.node.service.INodeAdvancedDAO;
import it.icarcnr.integration.dao.servicestatus.impl.ServiceAdvancedDAO;
import it.icarcnr.integration.dao.servicestatus.service.IServiceAdvancedDAO;
import it.icarcnr.integration.dao.util.IServiceConstants.Status;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NodeBL implements INodeBL {


	private static final Log log = LogFactory.getLog(NodeBL.class);

	public List<Type> findAll(int... rowStartIdxAndCount) throws Exception {
		final String method = "findAll(int... rowStartIdxAndCount)";
		log.info(method);
		try{
			ITypeDAO iTypeDAO = new TypeDAO();
			return iTypeDAO.findAll();
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	public Boolean hasService(List<Integer> enabledNetworkFunctionList, Date currentDate, Node node, Function function, Boolean recursiveSearch) throws Exception {
		final String method = "hasService(List<Integer> enabledNetworkFunctionList, Date currentDate, Node node, Function function, Boolean recursiveSearch)";
		log.info(method);
		try{
			
			IServiceAdvancedDAO iServiceAdvancedDAO = new ServiceAdvancedDAO();
			Integer functionId = null;
			if(function!=null){
				functionId = function.getId();
			}
			if(iServiceAdvancedDAO.getTotalServices(enabledNetworkFunctionList, currentDate, node.getId(), functionId)>0){
				return true;
			}
			if(recursiveSearch!=null && recursiveSearch){
				Set<NodeRelation> nodeRelationSet = node.getNodeRelationsForIdParent();
				for (NodeRelation nodeRelation : nodeRelationSet) {
					Node childNode = nodeRelation.getNodeByIdChild();
					if( hasService(enabledNetworkFunctionList, currentDate, childNode,function,recursiveSearch)){
						return true;
					}
				}
			}
			return false;
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}

	}

	public Boolean hasService(List<Integer> enabledNetworkFunctionList, Date currentDate, List<NodeRelation> nodeRelationSet, Function function,Boolean recursiveSearch) throws Exception {
		final String method = "hasService(List<Integer> enabledNetworkFunctionList, Date currentDate, List<NodeRelation> nodeRelationSet, Function function,Boolean recursiveSearch)";
		log.info(method);
		try{
			for (NodeRelation nodeRelation : nodeRelationSet) {
				Node childNode = nodeRelation.getNodeByIdChild();
				if( hasService(enabledNetworkFunctionList, currentDate, childNode,function,recursiveSearch)){
					return true;
				}
			}
			return false;
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	public Boolean hasService(List<Integer> enabledNetworkFunctionList, Date currentDate,  Type nodeType, Function function,Boolean recursiveSearch) throws Exception {
		final String method = "hasService(List<Integer> enabledNetworkFunctionList, Date currentDate, Type nodeType, Function function,Boolean recursiveSearch)";
		log.info(method);
		try{
			Set<Node> nodeSet = nodeType.getNodes();
			for (Node node : nodeSet) {
				if(hasService(enabledNetworkFunctionList, currentDate, node, function,recursiveSearch)){
					return true;
				}
			}
			return false;
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	public boolean containsSourceNode(List<Node> nodes, Service service)
			throws Exception {
		final String method = "containsSourceNode(List<Node> nodes, Service service)";
		log.info(method);
		try{
		Set<ServiceNodeSource> serviceNodeSources = service.getServiceNodeSources();
		for (ServiceNodeSource serviceNodeSource : serviceNodeSources) {
			if(nodes.contains(serviceNodeSource.getNode())){
				return true;
			}
		}
		return false;
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	public List<Type> findAll(List<Integer> enabledNetworkFunctionList, Date currentDate, Integer networkId, Integer functionId, int... rowStartIdxAndCount) throws Exception {
		final String method = "List<Integer> enabledNetworkFunctionList, Date currentDate, Integer networkId, Integer functionId, int... rowStartIdxAndCount)";
		log.info(method);
		try{
			INodeAdvancedDAO iNodeAdvancedDAO = new NodeAdvancedDAO();
			return iNodeAdvancedDAO.findAll(enabledNetworkFunctionList, currentDate, networkId, functionId, rowStartIdxAndCount);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	public Status getStatus(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Integer nodeId, Date currentDate) throws Exception {
		final String method = "getStatus(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Integer nodeId, Date currentDate)";
		log.info(method);
		try{
			INodeAdvancedDAO iNodeAdvancedDAO = new NodeAdvancedDAO();
			String status = iNodeAdvancedDAO.getStatus(enabledNetworkFunctionList, networkId, functionId, nodeId, currentDate);
//			CriteriaStatus criteriaStatus = CriteriaStatus.NORMAL;
			Status criteriaStatus = null;
			if(status!=null){
				criteriaStatus = Status.valueOf(status.toUpperCase());
			}
			return criteriaStatus;
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

}
