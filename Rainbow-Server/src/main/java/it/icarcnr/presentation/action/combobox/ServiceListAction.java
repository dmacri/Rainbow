package it.icarcnr.presentation.action.combobox;


import it.icarcnr.business.servicestatus.impl.ServiceBL;
import it.icarcnr.business.servicestatus.service.IServiceBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.SecurityUtil;

import java.util.Calendar;
import java.util.Date;
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



public class ServiceListAction extends Action {

	private static final Log log = LogFactory.getLog(ServiceListAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";

		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
			
			Calendar calendar = Calendar.getInstance();
			Date currentDate = calendar.getTime();
			
			Map criteriaMap = request.getParameterMap();


			String loadOption = request.getParameter("loadOption");

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();

			IServiceBL iServiceBL = new ServiceBL();

			List<Service> serviceList = iServiceBL.findAll(enabledNetworkFunctionList, currentDate, criteriaMap, true /*orderByService*/, false /*orderByNode*/);

			JSONObject record = null;
			if(loadOption!=null && loadOption.equals("ALL_SELECT")){
				record = new JSONObject();
				record.put("id", "-1");
				record.put("description","All");
				records.put(record);
			}

			for (Service service : serviceList) {
				record = new JSONObject();
				record.put("id", service.getId());
				record.put("description", service.getDescription());
				records.put(record);
			}

			result.put("root", records);
			//			result.put("totalCount", criteriaStatus.getTotalActiveCriteria(currentDate,null));
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
