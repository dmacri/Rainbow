package it.icarcnr.business.servicestatus.impl;

import it.icarcnr.business.servicestatus.service.IDisableThresholdBL;
import it.icarcnr.integration.dao.generated.Criteria;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.generated.ServiceOperation;
import it.icarcnr.integration.dao.servicestatus.impl.ServiceAdvancedDAO;
import it.icarcnr.integration.dao.servicestatus.service.IServiceAdvancedDAO;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DisableThresholdBL implements IDisableThresholdBL {

	private static final Log log = LogFactory.getLog(DisableThresholdBL.class);

	public void disabledThreshold(List<Integer> enabledNetworkFunctionList,Integer serviceId, Map<Integer,Boolean> suspendedOperationMap) throws Exception{

		final String method = "disabledThreshold(List<Integer> enabledNetworkFunctionList,Integer serviceId, Map<Integer,Boolean> suspendedOperationMap)";

		IServiceAdvancedDAO iServiceAdvancedDAO = new ServiceAdvancedDAO();
		try {			
			EntityManagerHelper.beginTransaction();
			Service service = iServiceAdvancedDAO.findById(enabledNetworkFunctionList,serviceId);
			Set<ServiceOperation> serviceOperationSet = service.getServiceOperations();
			for (ServiceOperation serviceOperation : serviceOperationSet) {
				Set<Criteria> criteriaSet = serviceOperation.getCriterias();
				for (Criteria criteria : criteriaSet) {
					if(criteria.getEnabled()){
						criteria.setSuspended(suspendedOperationMap.get(serviceOperation.getOperation().getId()));
					}
				}
			}
			EntityManagerHelper.commit();
		} catch (Exception e) {
			if ( EntityManagerHelper.getEntityManager().getTransaction().isActive() ){
				EntityManagerHelper.rollback();
			}
			log.error(method,e);
			throw e;
		}

	}

}
