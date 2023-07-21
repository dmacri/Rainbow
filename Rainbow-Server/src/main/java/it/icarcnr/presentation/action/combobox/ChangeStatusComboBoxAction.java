package it.icarcnr.presentation.action.combobox;

import it.icarcnr.business.servicestatus.impl.ServiceStatusChangeBL;
import it.icarcnr.business.servicestatus.service.IServiceStatusChangeBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Request;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.SecurityUtil;
import it.icarcnr.util.IDateFormatUtil;

import java.text.SimpleDateFormat;
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

public class ChangeStatusComboBoxAction extends Action{

	private static final Log log = LogFactory.getLog(ChangeStatusComboBoxAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";

		try {
			
			Calendar calendar = Calendar.getInstance();
			Date currentDate = calendar.getTime();
			
			SimpleDateFormat dateTimeFormat= new SimpleDateFormat(IDateFormatUtil.DATE_TIME_FORMAT);
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
			Date startDate = request.getParameter("startDetectionDate")!=null?dateTimeFormat.parse(request.getParameter("startDetectionDate")):null;

			Map criteriaMap = request.getParameterMap();

			String loadOption = request.getParameter("loadOption");

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();


			IServiceStatusChangeBL iServiceStatusChangeBL = new ServiceStatusChangeBL();

			List<Request> serviceRequestList = iServiceStatusChangeBL.getStatusChangeRequestList(enabledNetworkFunctionList, currentDate, startDate ,criteriaMap);
			JSONObject record = null;
			if(loadOption!=null && loadOption.equals("ALL_SELECT")){
				record = new JSONObject();
				record = new JSONObject();
				record.put("id", "-1");
				record.put("description","-- Tutti --");
				records.put(record);
			}

			for (Request serviceRequest : serviceRequestList) {				
				record = new JSONObject();
				record.put("id", serviceRequest.getId());
				record.put("description", serviceRequest.getDescription());
				records.put(record);
			}


			result.put("root", records);
			result.put("totalCount", serviceRequestList.size());
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