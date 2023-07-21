package it.icarcnr.presentation.action.graph;

import it.icarcnr.business.network.bean.AlarmedNodeBean;
import it.icarcnr.business.network.bean.AlarmedServiceBean;
import it.icarcnr.business.network.impl.NetworkBL;
import it.icarcnr.business.network.service.INetworkBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.util.IServiceConstants;
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
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmedNodeAction extends Action{

	private static final Log log = LogFactory.getLog(AlarmedNodeAction.class);

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
				Date currentDate = Calendar.getInstance().getTime();

				JSONArray alarmedNodesJSONArray = getAlarmedNodesJSONArray(enabledNetworkFunctionList, networkId, functionId, currentDate);


				HttpServletResponseUtil.setJsonResponse(response);

				response.getOutputStream().println(alarmedNodesJSONArray.toString());
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

	private JSONArray getAlarmedNodesJSONArray( List<Integer> enabledNetworkFunctionList, Integer networkId, Integer functionId, Date currentDate) throws Exception, JSONException {
		
		INetworkBL iNetworkBL = new NetworkBL();	
		
		JSONArray alarmedNodesJSONArray = new JSONArray();
		List<AlarmedNodeBean> alarmedNodes = iNetworkBL.getAlarmedNodes(enabledNetworkFunctionList, networkId, functionId, currentDate);
		
		int count = 0;
		for (AlarmedNodeBean alarmedNode : alarmedNodes) {
			JSONObject alarmedNodeRecord = new JSONObject();
			alarmedNodeRecord.put(IParameterHttpServletRequestContants.NODE_UNIQUE_ID, alarmedNode.getNodeUniqueId());
			alarmedNodeRecord.put("id", count++);
			alarmedNodeRecord.put("text", alarmedNode.getName());
			alarmedNodeRecord.put(IParameterHttpServletRequestContants.ICON_CLS, alarmedNode.getIconCls());
			alarmedNodeRecord.put("expanded", Boolean.TRUE);
			alarmedNodeRecord.put("leaf", Boolean.FALSE);
			
			JSONArray alarmedServicesArray = new JSONArray();
			List<AlarmedServiceBean> alarmedServices = alarmedNode.getAlarmedServices();

			for (AlarmedServiceBean alarmedService : alarmedServices) {
				JSONObject alarmedServiceRecord = new JSONObject();
				alarmedServiceRecord.put(IParameterHttpServletRequestContants.NODE_UNIQUE_ID, alarmedService.getNodeUniqueId());
				alarmedNodeRecord.put("id", count++);
				alarmedServiceRecord.put("text", alarmedService.getName());
				alarmedServiceRecord.put(IParameterHttpServletRequestContants.ICON_CLS, alarmedService.getIconCls());
				alarmedServiceRecord.put("expanded", Boolean.FALSE);
				alarmedServiceRecord.put("leaf", Boolean.TRUE);
				alarmedServicesArray.put(alarmedServiceRecord);
			}
			alarmedNodeRecord.put("children", alarmedServicesArray);
			alarmedNodesJSONArray.put(alarmedNodeRecord);
		}
		return alarmedNodesJSONArray;
	}
	
	private String getRenderedServiceName(String serviceName, String status){
		if(status != null){
			StringBuilder text = new StringBuilder();
			if(status.equals(IServiceConstants.NORMAL)){
				text.append("<div style=\"background-color:#ACD373; text-align: center;\">");
				text.append(serviceName);
				text.append("</div>");
			}else if(status.equals(IServiceConstants.MAJOR)){
				text.append("<div style=\"background-color:#F6CC3A; text-align: center;\">");
				text.append(serviceName);
				text.append("</div>");
			}else if(status.equals(IServiceConstants.CRITICAL)){
				text.append("<div style=\"background-color:#F16243; text-align: center;\">");
				text.append(serviceName);
				text.append("</div>");
			}else if(status.equals(IServiceConstants.SUSPENDED)){
				text.append("<div style=\"background-color:#C2C2C2; text-align: center;\">");
				text.append(serviceName);
				text.append("</div>");
			}
			return text.toString();
		}
		return serviceName;
	}

}