package it.icarcnr.business.servicestatus.impl;

import it.icarcnr.business.servicestatus.service.IServiceLogViewBL;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.servicestatus.impl.ServiceAdvancedDAO;
import it.icarcnr.integration.dao.servicestatus.service.IServiceAdvancedDAO;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceLogViewBL implements IServiceLogViewBL {

	private static final Log log = LogFactory.getLog(ServiceLogViewBL.class);

	public String getLog(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap) throws Exception {
		// TODO Auto-generated method stub
		final String method = "getLog(List<Integer> enabledNetworkFunctionList, Date currentDate, Map criteriaMap) ";
		log.info(method);
		try {
			IServiceAdvancedDAO iServiceAdvancedDAO = new ServiceAdvancedDAO();
			List<Service> services = iServiceAdvancedDAO.findAll(enabledNetworkFunctionList, currentDate, criteriaMap, false /*orderByService*/, false /*orderByNode*/);
			if (services.size()==1) {
				return (services.get(0).getLog());
			}
			return null;
		}
		catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

}
