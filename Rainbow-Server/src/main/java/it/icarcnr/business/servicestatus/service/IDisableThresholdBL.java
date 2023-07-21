package it.icarcnr.business.servicestatus.service;

import java.util.List;
import java.util.Map;

public interface IDisableThresholdBL {
	
	public void disabledThreshold(List<Integer> enabledNetworkFunctionList, Integer serviceId, Map<Integer,Boolean> suspendedOperationMap) throws Exception; 

}
