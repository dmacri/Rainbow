package it.icarcnr.presentation.action.job;

import it.icarcnr.business.servicestatus.impl.DisableThresholdBL;
import it.icarcnr.business.servicestatus.service.IDisableThresholdBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.util.IServiceOperationConstants;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.SecurityUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


public class DisableThresholdAction extends Action {

	private static final Log log = LogFactory.getLog(DisableThresholdAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String method = "execute";

		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
			Integer serviceId = request.getParameter("serviceId")!=null?Integer.valueOf(request.getParameter("serviceId")):null;

			String ontime = request.getParameter("ontime")!=null?request.getParameter("ontime"):"off";
			String stepbystep = request.getParameter("stepbystep")!=null?request.getParameter("stepbystep"):"off";

			JSONObject result = new JSONObject();
			JSONArray errors = new JSONArray();

			IDisableThresholdBL iDisableThresholdBL =  new DisableThresholdBL();

			Map<Integer,Boolean> suspendedOperationMap = new HashMap<Integer, Boolean>();

			if(ontime.equals("on")){
				suspendedOperationMap.put(IServiceOperationConstants.ON_TIME, Boolean.TRUE);
			}else if (ontime.equals("off")){
				suspendedOperationMap.put(IServiceOperationConstants.ON_TIME, Boolean.FALSE);
			}

			if(stepbystep.equals("on")){
				suspendedOperationMap.put(IServiceOperationConstants.STEP_BY_STEP, Boolean.TRUE);
			}else if (stepbystep.equals("off")){
				suspendedOperationMap.put(IServiceOperationConstants.STEP_BY_STEP, Boolean.FALSE);
			}

			iDisableThresholdBL.disabledThreshold(enabledNetworkFunctionList, serviceId, suspendedOperationMap);

			result.put("success", true);
			result.put("errors", errors);

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
