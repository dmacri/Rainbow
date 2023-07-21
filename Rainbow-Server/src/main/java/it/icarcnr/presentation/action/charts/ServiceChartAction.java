package it.icarcnr.presentation.action.charts;

import it.icarcnr.business.charts.impl.ServiceChartBL;
import it.icarcnr.business.charts.service.IServiceChartBL;
import it.icarcnr.business.charts.util.IChartsConstants.ChartMode;
import it.icarcnr.business.report.bean.ChartBean;
import it.icarcnr.business.report.bean.ChartBean.ServiceValue;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.servicestatus.bean.CriteriaSensorValue;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;
import it.icarcnr.presentation.action.util.SecurityUtil;
import it.icarcnr.util.IDateFormatUtil;
import it.icarcnr.util.JSONUtil;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
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

public class ServiceChartAction extends Action {

	private static final Log log = LogFactory.getLog(ServiceChartAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";

		try {
			JSONObject result = new JSONObject();
		
			Integer sensorId = request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)):null;
			SimpleDateFormat dateFormat = new SimpleDateFormat(IDateFormatUtil.DATE_FORMAT);
			SimpleDateFormat dateTimeFormat= new SimpleDateFormat(IDateFormatUtil.DATE_TIME_FORMAT);
			SimpleDateFormat timeDateFormat = new SimpleDateFormat(IDateFormatUtil.TIME_DATE_FORMAT);
			Date dateStart = dateFormat.parse(request.getParameter("dateStart"));
			Date timeStart = timeDateFormat.parse(request.getParameter("timeStart"));
			Date dateEnd = dateFormat.parse(request.getParameter("dateEnd"));
			Date timeEnd = timeDateFormat.parse(request.getParameter("timeEnd"));
			Date startDate = new Date(dateStart.getTime()+timeStart.getTime()+60*60*1000);
			Date endDate = new Date(dateEnd.getTime()+timeEnd.getTime()+60*60*1000);
			IServiceChartBL iServiceChart = new ServiceChartBL();	
	
			JSONArray valuesRecord = new JSONArray();
			JSONObject valueRecord=null;
			List<CriteriaSensorValue> chartBean = iServiceChart.getChartDataBean(sensorId, startDate, endDate);
          
             Long xValueL=null;
             Long startDateL=null;
             Long endDateL=null;
			
			for (CriteriaSensorValue criteriaSensorValue : chartBean) {
				valueRecord= new JSONObject();	
			
				xValueL=criteriaSensorValue.getTimestamp().getTime();
				valueRecord.put("xValue", xValueL);
				valueRecord.put("yValue", criteriaSensorValue.getValue());
				startDateL=startDate.getTime();
				Double stD=startDateL.doubleValue();
				valueRecord.put("startDate", stD);
				endDateL=endDate.getTime();
				Double endD=endDateL.doubleValue();
				valueRecord.put("endDate",endD);
				valueRecord.put("name", criteriaSensorValue.getName());
				valueRecord.put("lat", criteriaSensorValue.getLat());
				valueRecord.put("lng", criteriaSensorValue.getLng());
							valuesRecord.put(valueRecord);
				}
		
//			lineRecord.put("values", valuesRecord);
//			records.put(lineRecord);
			result.put("root", valuesRecord);

			HttpServletResponseUtil.setJsonResponse(response);
           System.out.println(result.toString());
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
