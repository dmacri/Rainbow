package it.icarcnr.business.servicestatus.service;

import it.icarcnr.business.servicestatus.bean.ServiceSuspendedInfoBean;

import java.util.List;

public interface IServiceOperationListBL {
	
	public List<ServiceSuspendedInfoBean> getServiceSuspendedInfo(List<Integer> enabledNetworkFunctionList, Integer serviceId) throws Exception;
}
