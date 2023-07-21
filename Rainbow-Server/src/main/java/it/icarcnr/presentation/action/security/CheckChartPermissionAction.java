/**
 * 
 */
package it.icarcnr.presentation.action.security;

import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.business.security.impl.SecurityBL;
import it.icarcnr.business.security.service.ISecurityBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IActionConstants;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;
import it.icarcnr.presentation.action.util.SecurityUtil;

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


public class CheckChartPermissionAction extends Action {

	private static final Log log = LogFactory.getLog(CheckChartPermissionAction.class);

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String method = "execute";

		try {

			UserLoginBean userLoginBean = SecurityUtil.getUser(request);
			ISecurityBL iSecurityBL = new SecurityBL();
			Integer networkId = request.getParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID)):null;			
			Integer functionId = request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)):null;			
			
			Boolean drawChartDisabled = !iSecurityBL.hasPermission(userLoginBean.getId(), IActionConstants.SERVICE_CHART_ACTION_PATH, networkId, functionId);
			Boolean pdfReportDisabled = !iSecurityBL.hasPermission(userLoginBean.getId(), IActionConstants.CHART_REPORT_ACTION, networkId, functionId);
			Boolean pdfOnlyChartReportDisabled = !iSecurityBL.hasPermission(userLoginBean.getId(), IActionConstants.ONLY_CHART_REPORT_ACTION, networkId, functionId);
			Boolean csvReportDisabled = !iSecurityBL.hasPermission(userLoginBean.getId(), IActionConstants.CSV_REPORT_ACTION, networkId, functionId);

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();
			JSONObject record = new JSONObject();

			record.put("drawChartDisabled", drawChartDisabled);		
			record.put("pdfReportDisabled", pdfReportDisabled);
			record.put("pdfOnlyChartReportDisabled", pdfOnlyChartReportDisabled);
			record.put("csvReportDisabled", csvReportDisabled);
			
			records.put(record);

			result.put("root", records);

			HttpServletResponseUtil.setJsonResponse(response);

			response.getOutputStream().println(result.toString());
			response.flushBuffer();
		} catch (Exception e) {
			log.error(method,e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Internal server error");
			//			throw e;
		}
		finally{
			EntityManagerHelper.closeEntityManager();
		}
		return null;
	}

}
