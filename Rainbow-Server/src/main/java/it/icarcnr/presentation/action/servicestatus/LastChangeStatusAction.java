package it.icarcnr.presentation.action.servicestatus;

import it.icarcnr.business.servicestatus.impl.ServiceStatusChangeBL;
import it.icarcnr.business.servicestatus.service.IServiceStatusChangeBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
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


public class LastChangeStatusAction extends Action{

	private static final Log log = LogFactory.getLog(LastChangeStatusAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";
		SimpleDateFormat dateTimeFormat= new SimpleDateFormat(IDateFormatUtil.DATE_TIME_FORMAT);

		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		Date startDetectionDate = request.getParameter("startDetectionDate")!=null?dateTimeFormat.parse(request.getParameter("startDetectionDate")):null;
		Map criteriaMap = request.getParameterMap();
		Boolean changeStatus =false;

		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
			JSONArray records = new JSONArray();
			JSONObject result = new JSONObject();
			JSONObject service = new JSONObject();

			result.put("root", records);

			if(startDetectionDate!=null){
				IServiceStatusChangeBL iServiceStatusChangeBL = new ServiceStatusChangeBL();
				Long total  = iServiceStatusChangeBL.getTotalServiceStatusChangeList(enabledNetworkFunctionList, currentDate, startDetectionDate, criteriaMap);
				if(total>0){
					changeStatus = true;
				}
			}
			
			service.put("changeStatus", changeStatus);
			service.put("currentDate",dateTimeFormat.format(currentDate));
						
			records.put(service);

			result.put("root", records);

			HttpServletResponseUtil.setJsonResponse(response);
			response.getWriter().println(result.toString());
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
