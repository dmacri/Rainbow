package it.icarcnr.presentation.action.portal;

import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.business.security.impl.SecurityBL;
import it.icarcnr.business.security.service.ISecurityBL;
import it.icarcnr.business.servicestatus.impl.ServiceBL;
import it.icarcnr.business.servicestatus.service.IServiceBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.util.IServiceConstants;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IActionConstants;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;
import it.icarcnr.presentation.action.util.SecurityUtil;

import java.util.Calendar;
import java.util.Date;
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

public class SummaryPanelServiceStatusAction extends Action {

	private static final Log log = LogFactory.getLog(SummaryPanelServiceStatusAction.class);


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String method = "execute";

		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);

			Integer networkId = request.getParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID)):null;

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();

			IServiceBL iServiceBL = new ServiceBL();

			Calendar calendar = Calendar.getInstance();
			Date currentDate = calendar.getTime();

			Long critical=null;
			Long major=null;
			Long normal=null;
			Long suspended=null;
			

			if(networkId!=null){
				JSONObject netRecord = new JSONObject();
				critical = iServiceBL.getTotalServiceByStatus(enabledNetworkFunctionList, networkId ,currentDate, IServiceConstants.CRITICAL);
				major = iServiceBL.getTotalServiceByStatus(enabledNetworkFunctionList, networkId, currentDate, IServiceConstants.MAJOR);
				normal = iServiceBL.getTotalServiceByStatus(enabledNetworkFunctionList,networkId, currentDate, IServiceConstants.NORMAL);
				suspended = iServiceBL.getTotalServiceByStatus(enabledNetworkFunctionList,networkId, currentDate, IServiceConstants.SUSPENDED);


				netRecord.put(IServiceConstants.CRITICAL, critical);
				netRecord.put(IServiceConstants.MAJOR, major);
				netRecord.put(IServiceConstants.NORMAL,normal);
				netRecord.put(IServiceConstants.SUSPENDED,suspended);
				
				ISecurityBL iSecurityBL = new SecurityBL();
				UserLoginBean userLoginBean = SecurityUtil.getUser(request);
				Boolean viewGraphPermission = iSecurityBL.hasPermission(userLoginBean.getId(), IActionConstants.LOAD_GRAPH_ACTION_PATH, networkId, null);
				netRecord.put("viewGraphPermission", viewGraphPermission);
			
				records.put(netRecord);
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
