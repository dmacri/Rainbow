package it.icarcnr.presentation.action.combobox;


import it.icarcnr.business.network.impl.NetworkBL;
import it.icarcnr.business.network.service.INetworkBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.INodeSenDAO;
import it.icarcnr.integration.dao.generated.Network;
import it.icarcnr.integration.dao.generated.NodeSen;
import it.icarcnr.integration.dao.generated.NodeSenDAO;
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


public class NetworkListAction extends Action {

	private static final Log log = LogFactory.getLog(NetworkListAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";

		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
			
			String loadOption = request.getParameter("loadOption");

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();

			INodeSenDAO iNodeSenDAO = new NodeSenDAO();

			List<NodeSen> nodeSensorList = iNodeSenDAO.findAll();

			JSONObject record = null;
			if(loadOption!=null && loadOption.equals("ALL_SELECT")){
				record = new JSONObject();
				record.put("id", "-1");
				record.put("description","All");
				records.put(record);
			}

			for (NodeSen nodeSen : nodeSensorList) {
				record = new JSONObject();
				record.put("id", nodeSen.getId());
				record.put("description", nodeSen.getDescription());
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
