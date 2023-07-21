/**
 * 
 */
package it.icarcnr.presentation.plugin;

import it.icarcnr.business.release.impl.ServiceReleaseBL;
import it.icarcnr.business.release.service.IServiceReleaseBL;
import it.icarcnr.business.release.util.IReleaseConstants;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Release;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;


public class LogStartApplicationPlugIn implements PlugIn {
	
	private static final Logger vmcLog = Logger.getRootLogger();
	private final static Log systemLog = LogFactory.getLog(LogStartApplicationPlugIn.class);
	
	

	/* (non-Javadoc)
	 * @see org.apache.struts.action.PlugIn#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet, org.apache.struts.config.ModuleConfig)
	 */
	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {
		final String method = "execute";
		try {
			StringBuilder message = new StringBuilder("Startup VasMonCat version ");
			IServiceReleaseBL iServiceRelease = new ServiceReleaseBL();
			Release releaseVersion = iServiceRelease.findById(IReleaseConstants.CURRENT_VERSION_ID);
			message.append(releaseVersion.getVersion());
			systemLog.info(message);
		} catch (Exception e) {
			vmcLog.error(method,e);
		}
		finally{
			EntityManagerHelper.closeEntityManager();
		}

	}

}
