package it.icarcnr.business.release.impl;

import it.icarcnr.business.release.service.IServiceReleaseBL;
import it.icarcnr.integration.dao.generated.IReleaseDAO;
import it.icarcnr.integration.dao.generated.Release;
import it.icarcnr.integration.dao.generated.ReleaseDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceReleaseBL implements IServiceReleaseBL{
	
	private static final Log log = LogFactory.getLog(ServiceReleaseBL.class);

	public Release findById(Integer id) throws Exception {

		final String method = "findById(Integer id))";
		log.info(method);
		try{
			IReleaseDAO iReleaseDAO = new ReleaseDAO();
			return iReleaseDAO.findById(id);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}
}
