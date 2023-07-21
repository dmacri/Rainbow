package it.icarcnr.presentation.plugin;

import it.icarcnr.business.quartz.impl.CronScheduler;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

public class SchedulerPlugIn implements PlugIn {

	private static final Log log = LogFactory.getLog(SchedulerPlugIn.class);

	public static final String PLUGIN_NAME_KEY = SchedulerPlugIn.class.getName();

	public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {

		final String method = "execute";

		try {
			ServletContext context = null;
			context = servlet.getServletContext();
			CronScheduler objPlugin = new CronScheduler();
			context.setAttribute(PLUGIN_NAME_KEY, objPlugin);
		}
		catch (Exception e) {
			log.error(method,e);
			throw new ServletException(e);

		}finally{
			EntityManagerHelper.closeEntityManager();
		}

	}

	public void destroy()
	{

	}

}
