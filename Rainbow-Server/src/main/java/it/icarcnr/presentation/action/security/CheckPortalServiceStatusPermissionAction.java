package it.icarcnr.presentation.action.security;

import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.business.network.impl.NetworkBL;
import it.icarcnr.business.network.service.INetworkBL;
import it.icarcnr.business.security.impl.SecurityBL;
import it.icarcnr.business.security.service.ISecurityBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Network;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IActionConstants;
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

public class CheckPortalServiceStatusPermissionAction extends Action {

	private static final Log log = LogFactory.getLog(CheckPortalServiceStatusPermissionAction.class);


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
			INetworkBL  iNetwork = new NetworkBL();
			/***/
		
			List<Network> net = iNetwork.findAll(null);

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();



			//			Integer networkId = request.getParameter(IParameterHttpServletRequestContants.NETWORK_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.NETWORK_ID)):null;			
			//			Integer functionId = request.getParameter(IParameterHttpServletRequestContants.FUNCTION_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.FUNCTION_ID)):null;			
			for (Network iNet : net) {

				JSONObject record = new JSONObject();
				Boolean permission = iSecurityBL.hasPermission(userLoginBean.getId(), IActionConstants.PORTAL_SUMMARY_SERVICE_STATUS_ACTION_PATH, iNet.getId(), null);

				record.put("permission", permission);
				record.put("idNetwork", iNet.getId());
				record.put("title", iNet.getDescription());

				records.put(record);
			}

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
