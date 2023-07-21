/**
 * 
 */
package it.icarcnr.business.servicestatus.impl;

import it.icarcnr.business.servicestatus.bean.ServiceSuspendedInfoBean;
import it.icarcnr.business.servicestatus.service.IServiceOperationListBL;
import it.icarcnr.integration.dao.generated.Criteria;
import it.icarcnr.integration.dao.generated.ServiceOperation;
import it.icarcnr.integration.dao.serviceoperation.impl.ServiceOperationAdvancedDAO;
import it.icarcnr.integration.dao.serviceoperation.service.IServiceOperationAdvancedDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Faber
 *
 */
public class ServiceOperationListBL implements IServiceOperationListBL {
	private static final Log log = LogFactory.getLog(ServiceOperationListBL.class);

	public List<ServiceSuspendedInfoBean> getServiceSuspendedInfo(List<Integer> enabledNetworkFunctionList, Integer serviceId) throws Exception{

		List<ServiceSuspendedInfoBean> serviceSuspendedInfoBeans = new ArrayList<ServiceSuspendedInfoBean>();

		List<ServiceOperation> serviceOperationList = getServiceOperationList(enabledNetworkFunctionList, serviceId);

		for (ServiceOperation serviceOperation : serviceOperationList) {
			ServiceSuspendedInfoBean serviceSuspendedInfoBean = new ServiceSuspendedInfoBean();
			serviceSuspendedInfoBean.setServiceOperationId(serviceOperation.getOperation().getId());
			serviceSuspendedInfoBean.setNameServiceOperation(serviceOperation.getOperation().getName());
			serviceSuspendedInfoBean.setCriteriaSuspended(isAllCriteriaSuspended(serviceOperation.getCriterias()));
			serviceSuspendedInfoBean.setCriteriaEnabled(isAllCriteriaEnabled(serviceOperation.getCriterias()));
			serviceSuspendedInfoBean.setCriteriaSuspendedSystem(isSuspededBySystem(serviceOperation.getCriterias()));
			serviceSuspendedInfoBeans.add(serviceSuspendedInfoBean);
		}
		return serviceSuspendedInfoBeans;
	}

	private boolean isAllCriteriaEnabled(Set<Criteria> criterias) {
		if(criterias==null || criterias.isEmpty()){
			return false;
		}
		boolean isAllCriteriaEnabled = true;
		for (Criteria criteria : criterias) {
			if(!criteria.getEnabled()){
				return false;
			}
		}
		return isAllCriteriaEnabled;

	}


	private boolean isAllCriteriaSuspended(Set<Criteria> criterias) {
		if(criterias==null || criterias.isEmpty()){
			return false;
		}
		boolean isAllCriteriaSuspended = true;
		for (Criteria criteria : criterias) {
			if(criteria.getEnabled()&& !criteria.getSuspended()){

				return false;
			}

		}
		return isAllCriteriaSuspended;
	}
	private boolean isSuspededBySystem(Set<Criteria> criterias){
		if(criterias==null || criterias.isEmpty()){
			return false;
		}
		boolean isSuspendedBySystem= true;
		for(Criteria criteria :criterias){
			if(criteria.getSuspended() || !criteria.getStatus().equals("suspended")){
				return false;
			}

		}
		return isSuspendedBySystem;
	}



	private List<ServiceOperation> getServiceOperationList(List<Integer> enabledNetworkFunctionList, Integer serviceId) throws Exception{

		final String method = "ServiceOperationList(List<Integer> enabledNetworkFunctionList, Integer serviceId)";
		try {
			IServiceOperationAdvancedDAO iServiceOperationAdvancedDAO = new ServiceOperationAdvancedDAO();
			return iServiceOperationAdvancedDAO.findAll(enabledNetworkFunctionList,serviceId);
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}		


	}

}
