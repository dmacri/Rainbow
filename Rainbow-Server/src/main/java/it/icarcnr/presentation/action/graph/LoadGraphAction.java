/**
 * 
 */
package it.icarcnr.presentation.action.graph;

import it.icarcnr.business.network.bean.NetworkItemBean;
import it.icarcnr.business.network.impl.NetworkBL;
import it.icarcnr.business.network.service.INetworkBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
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

/**
 * @author Graziano
 *
 */
public class LoadGraphAction extends Action {

	private static final Log log = LogFactory.getLog(LoadGraphAction.class);

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String method = "execute";

		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
			
			Integer networkId = request.getParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID)):null;
			Integer functionId = request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)):null;

			if(networkId!=null){
				
				INetworkBL iNetworkBL = new NetworkBL();
				
				Date currentDate = Calendar.getInstance().getTime();
				
				NetworkItemBean networkItemBean = iNetworkBL.getNetworkItemBean(enabledNetworkFunctionList, networkId, functionId, currentDate);

				JSONObject result = new JSONObject();
				JSONArray records = new JSONArray();
				JSONObject record = new JSONObject();
				JSONObject graph =	networkItemBean.toJSONObject();
				record.put("graph", graph);

				records.put(record);

				result.put("root", records);

				HttpServletResponseUtil.setJsonResponse(response);

				response.getOutputStream().println(result.toString());
				response.flushBuffer();
			}
			

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
