/**
 * 
 */
package it.icarcnr.presentation.action.combobox;

import it.icarcnr.business.servicestatus.bean.ServiceSuspendedInfoBean;
import it.icarcnr.business.servicestatus.impl.ServiceOperationListBL;
import it.icarcnr.business.servicestatus.service.IServiceOperationListBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.SecurityUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Faber
 *
 */
public class ServiceOperationListAction extends Action  {
	private static final Log log = LogFactory.getLog(ServiceOperationListAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";

		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
			Integer serviceId = request.getParameter("serviceId")!=null?Integer.valueOf(request.getParameter("serviceId")):null;


			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();

			IServiceOperationListBL iServiceOperationListBL = new ServiceOperationListBL();
			
			List<ServiceSuspendedInfoBean>  serviceSuspendedInfoBeans = iServiceOperationListBL.getServiceSuspendedInfo(enabledNetworkFunctionList, serviceId);

			JSONObject record = null;


			for (ServiceSuspendedInfoBean serviceSuspendedInfoBean : serviceSuspendedInfoBeans) {
				record = new JSONObject();
				record.put("idServiceOperation", serviceSuspendedInfoBean.getServiceOperationId());
				record.put("criteriaSuspended",serviceSuspendedInfoBean.getCriteriaSuspended());
				record.put("criteriaSuspendedBySystem", serviceSuspendedInfoBean.getCriteriaSuspendedSystem());
				records.put(record);
			}

			result.put("root", records);
			result.put("threadid",Thread.currentThread().getId());

			HttpServletResponseUtil.setJsonResponse(response);

			response.getOutputStream().println(result.toString());
			response.flushBuffer();
		} catch (Exception e) {
			log.error(method,e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,IErrorMessageConstants.INTERNAL_SERVER_ERROR);
		}
		finally{
			EntityManagerHelper.closeEntityManager();
		}
		return null;
	}


}
