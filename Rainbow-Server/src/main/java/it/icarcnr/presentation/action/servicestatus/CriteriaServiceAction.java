package it.icarcnr.presentation.action.servicestatus;

import it.icarcnr.business.servicestatus.impl.CriteriaStatusBL;
import it.icarcnr.business.servicestatus.service.ICriteriaStatusBL;
import it.icarcnr.integration.dao.generated.Criteria;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;
import it.icarcnr.presentation.action.util.SecurityUtil;
import it.icarcnr.util.IDateFormatUtil;

import java.text.SimpleDateFormat;
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

public class CriteriaServiceAction extends Action{

	private static final Log log = LogFactory.getLog(CriteriaServiceAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)

	throws Exception {

		final String method = "execute";

		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
			Integer criteriaId = request.getParameter(IParameterHttpServletRequestContants.CRITERIA_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.CRITERIA_ID)):null;
			SimpleDateFormat dateTimeFormat= new SimpleDateFormat(IDateFormatUtil.DATE_TIME_FORMAT);

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();

			ICriteriaStatusBL iCriteriaServiceBL = new CriteriaStatusBL();

			Criteria criteria = iCriteriaServiceBL.findById(enabledNetworkFunctionList, criteriaId);

			JSONObject record = new JSONObject();
			
			//workaround  threshold must be defined - to be: set to null and not to -1
			Double majorValue = criteria.getValueCheckMajor();
			if(majorValue!=null && majorValue == -1){
				majorValue = null;
			}
			Double criticalValue = criteria.getValueCheckCritical();
			if(criticalValue!=null && criticalValue == -1){
				criticalValue = null;
			}
			
			record.put("majorValue", majorValue);
			record.put("criticalValue", criticalValue);
			record.put("secondaryMajorValue", criteria.getValueCheckSecondaryMajor());
			record.put("secondaryCriticalValue", criteria.getValueCheckSecondaryCritical());
			record.put("samplingPeriod",criteria.getServiceOperation().getService().getSamplingPeriod());
			record.put("thresholdType",criteria.getCalculation());
			record.put("typeCheck", criteria.getTypeCheck());
			record.put("from", dateTimeFormat.format(criteria.getStartTime()));//(startDate.getValue(), IDateFormatUtil.dateFormat
			record.put("to",dateTimeFormat.format(criteria.getEndTime()));
			record.put("monday", criteria.getMonday());
			record.put("tuesday", criteria.getTuesday());
			record.put("wednesday", criteria.getWednesday());
			record.put("thursday", criteria.getThursday());
			record.put("friday", criteria.getFriday());
			record.put("saturday", criteria.getSaturday());
			record.put("sunday", criteria.getSunday());
			record.put("suspended", criteria.getSuspended());
			record.put("status", criteria.getStatus());

			records.put(record);
			result.put("root", records);
			//			result.put("totalCount", iServiceBL.getTotalServiceStatus(enabledNetworkFunctionList, currentDate, criteriaMap));
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
