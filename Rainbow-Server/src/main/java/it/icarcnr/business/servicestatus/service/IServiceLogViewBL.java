package it.icarcnr.business.servicestatus.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IServiceLogViewBL {
	
	public String getLog(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap) throws Exception;
	
}
