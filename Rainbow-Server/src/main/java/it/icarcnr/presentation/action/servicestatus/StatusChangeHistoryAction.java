package it.icarcnr.presentation.action.servicestatus;

import it.icarcnr.business.servicestatus.bean.CriteriaStatusChangeBean;
import it.icarcnr.business.servicestatus.bean.CriteriaStatusChangeSequenceBean;
import it.icarcnr.business.servicestatus.bean.ServiceStatusChangeBean;
import it.icarcnr.business.servicestatus.impl.ServiceStatusChangeBL;
import it.icarcnr.business.servicestatus.service.IServiceStatusChangeBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.util.IServiceConstants;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;
import it.icarcnr.presentation.action.util.SecurityUtil;
import it.icarcnr.util.IDateFormatUtil;
import it.icarcnr.util.JSONUtil;

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

public class StatusChangeHistoryAction extends Action{

	private static final Log log = LogFactory.getLog(StatusChangeHistoryAction.class);

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

			List<String> servciceIdList = null;
			String[] mapValue = (String[])criteriaMap.get("servciceIdList");
			if(mapValue!=null && mapValue.length>0 && mapValue[0]!=null){
				servciceIdList = JSONUtil.getStringList(mapValue[0]);
			}

			JSONArray records = new JSONArray();

			IServiceStatusChangeBL iServiceStatusChangeBL = new ServiceStatusChangeBL();
			// ------ First row
			List<ServiceStatusChangeBean> serviceStatusChangeBeans = iServiceStatusChangeBL.getServiceStatusChangeList(enabledNetworkFunctionList, currentDate, startDate ,criteriaMap);

			for (ServiceStatusChangeBean serviceStatusChangeBean : serviceStatusChangeBeans) {
				JSONObject service = new JSONObject();
				service.put("uiProvider","col");
				String serviceId = serviceStatusChangeBean.getServiceId().toString();

				service.put(IParameterHttpServletRequestContants.SERVICE_ID, serviceId);
				//				service.put("fieldSearch", serviceStatusChangeBean.getFieldSearch());

				service.put("serviceDescription", serviceStatusChangeBean.getServiceDescription());
				service.put("source", serviceStatusChangeBean.getSource());
				service.put("network",serviceStatusChangeBean.getNetwork());
				service.put("networkId",serviceStatusChangeBean.getNetworkId());
				service.put("serviceType", serviceStatusChangeBean.getServiceType());
				service.put("reference", serviceStatusChangeBean.getReference());
				service.put("nodeFrom", serviceStatusChangeBean.getNodeFrom());
				service.put("nodeTo", serviceStatusChangeBean.getNodeTo());
				service.put("fromStatus", "");
				service.put("toStatus", "");
				service.put("value", "");
				service.put("date", "");
				service.put("iconCls", "change-status-icon");
				if(servciceIdList.contains(serviceId)){
					service.put("expanded", Boolean.TRUE);
				}
				JSONArray arraySequence = new JSONArray();
				// ----- Second row				
				List<CriteriaStatusChangeSequenceBean> criteriaStatusChangeSequenceBeans = serviceStatusChangeBean.getCriteriaStatusChangeSequenceBeans();
				for (CriteriaStatusChangeSequenceBean criteriaStatusChangeSequenceBean : criteriaStatusChangeSequenceBeans) {

					JSONObject sequence = new JSONObject();
					sequence.put("uiProvider","col");
					serviceId = serviceStatusChangeBean.getServiceId()+criteriaStatusChangeSequenceBean.getDescription();
					sequence.put(IParameterHttpServletRequestContants.SERVICE_ID, serviceId);
					sequence.put("source","");
					sequence.put("serviceDescription", criteriaStatusChangeSequenceBean.getDescription());
					sequence.put("network", "");
					//					sequence.put("target",criteriaStatusChangeSequenceBean.getTarget());
					sequence.put("fromStatus", "");
					sequence.put("toStatus", "");
					sequence.put("value", "");
					sequence.put("date", "");
					if(IServiceConstants.STEP_BY_STEP.equals(criteriaStatusChangeSequenceBean.getOperationType())){
						sequence.put("iconCls", "delta-icon" );//criteriaStatusChangeSequenceBean.getDescription()
					}else{
						sequence.put("iconCls", "value-icon");
					}
					if(servciceIdList.contains(serviceId)){
						sequence.put("expanded", Boolean.TRUE);
					}
					arraySequence.put(sequence);
					// ----third row				
					JSONArray arrayChangeStatus = new JSONArray();
					List<CriteriaStatusChangeBean> criteriaStatusChangeBeans = criteriaStatusChangeSequenceBean.getCriteriaStatusChangeBeans();
					int i = 1;
					for (CriteriaStatusChangeBean criteriaStatusChangeBean : criteriaStatusChangeBeans) {				
						JSONObject changeStatus = new JSONObject();
						changeStatus.put("uiProvider","col");
						changeStatus.put(IParameterHttpServletRequestContants.SERVICE_ID, serviceStatusChangeBean.getServiceId()+criteriaStatusChangeSequenceBean.getDescription()+"-"+i);
						changeStatus.put("source","");

						changeStatus.put("network", "");

						changeStatus.put("serviceDescription",i+"&#176 event" );
						i++;
						changeStatus.put("fromStatus", getRenderedStatus(criteriaStatusChangeBean.getFromStatus()));
						changeStatus.put("toStatus", getRenderedStatus(criteriaStatusChangeBean.getToStatus()));

						changeStatus.put("valueMajor", criteriaStatusChangeBean.getMajorValue());
						changeStatus.put("valueCritical", criteriaStatusChangeBean.getCriticalValue());
						changeStatus.put("criticalDeltaValue",criteriaStatusChangeBean.getCriticalDeltaValue());
						changeStatus.put("majorDeltaValue", criteriaStatusChangeBean.getMajorDeltaValue());
						//tooltip field value
						String tooltip = getTooltipText(criteriaStatusChangeSequenceBean,criteriaStatusChangeBean);

						changeStatus.put("value", "<b><center>"+getToolTip(criteriaStatusChangeBean.getValue().intValue(), tooltip)+"</center></b>");
						changeStatus.put("secondaryMajorValue", criteriaStatusChangeBean.getSecondaryMajorValue());
						changeStatus.put("secondaryMajorDeltaValue", criteriaStatusChangeBean.getSecondaryMajorDeltaValue());
						changeStatus.put("secondaryCriticalValue", criteriaStatusChangeBean.getSecondaryCriticalValue());
						changeStatus.put("secondaryCriticalDeltaValue", criteriaStatusChangeBean.getSecondaryCriticalDeltaValue());
						//						changeStatus.put("target",criteriaStatusChangeBean.getTarget());
						changeStatus.put("date", dateTimeFormat.format(criteriaStatusChangeBean.getDate()));
						changeStatus.put("iconCls", "event-icon");
						changeStatus.put("leaf", Boolean.TRUE);

						arrayChangeStatus.put(changeStatus);
					}
					sequence.put("children", arrayChangeStatus);
				}
				service.put("children", arraySequence);
				records.put(service);
			}

			//			result.put("root", records);
			//			result.put("totalCount", serviceStatusChangeBeans.size());
			//			result.put("threadid",Thread.currentThread().getId());

			HttpServletResponseUtil.setJsonResponse(response);

			//			response.getOutputStream().println(result.toString());
			response.getOutputStream().println(records.toString());
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

	private String getTooltipText(CriteriaStatusChangeSequenceBean criteriaStatusChangeSequenceBean,
			CriteriaStatusChangeBean criteriaStatusChangeBean) {

		final String method = "execute";
		StringBuilder tooltip = new StringBuilder();

		try{

			tooltip.append("Tipo di controllo: "+"<b>");

			if(criteriaStatusChangeBean.getTypeCheck().equals("below")){
				tooltip.append("Inferiore"+"</b><br>");
				getTooltipWithBelowValue(criteriaStatusChangeBean,tooltip);
			}else if(criteriaStatusChangeBean.getTypeCheck().equals("above")){
				tooltip.append("Superiore"+"</b><br>");
				getTootipWithAboveValue(criteriaStatusChangeBean, tooltip);

			}else if(criteriaStatusChangeBean.getTypeCheck().equals(IServiceConstants.RANGE)){
				tooltip.append("superiore ed inferiore"+"</b><br>");
				getTooltipWithRangeValue(criteriaStatusChangeBean, tooltip);
			}else{
				tooltip.append(" da definire"+"<br></b>");
			}

		}catch (Exception e) {
			log.error(method,e);
		}
		return tooltip.toString();
	}

	private void getTooltipWithBelowValue(CriteriaStatusChangeBean criteriaStatusChangeBean,StringBuilder tooltip) {
		if(criteriaStatusChangeBean.getOperation().equals(IServiceConstants.STEP_BY_STEP)){
			getTooltipStepByStep(criteriaStatusChangeBean, tooltip);

		}else{
			getTooltipOnTime(criteriaStatusChangeBean, tooltip);	
		}
	}
	private void getTootipWithAboveValue(CriteriaStatusChangeBean criteriaStatusChangeBean,	StringBuilder tooltip) {

		if(criteriaStatusChangeBean.getOperation().equals(IServiceConstants.STEP_BY_STEP)){
			getTooltipStepByStep(criteriaStatusChangeBean, tooltip);

		}else{
			getTooltipOnTime(criteriaStatusChangeBean, tooltip);

		}
	}

	
	private void getTooltipStepByStep(
			CriteriaStatusChangeBean criteriaStatusChangeBean,
			StringBuilder tooltip) {
		tooltip.append("Valore Major: <b>");
		if(criteriaStatusChangeBean.getMajorDeltaValue()==null){
			tooltip.append("da definire<br></b>");
		}else{
			if(criteriaStatusChangeBean.getMajorDeltaValue().intValue()==criteriaStatusChangeBean.getMajorDeltaValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getMajorDeltaValue().intValue()+"<br></b>");
			}else{
				tooltip.append(criteriaStatusChangeBean.getMajorDeltaValue()+"<br></b>");
			}	

		}
		tooltip.append("Valore Critical: <b>");
		if(criteriaStatusChangeBean.getCriticalDeltaValue()==null){
			tooltip.append("da definire<br></b>");
		}else{
			if(criteriaStatusChangeBean.getCriticalDeltaValue().intValue()==criteriaStatusChangeBean.getCriticalDeltaValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getCriticalDeltaValue().intValue()+"<br></b>");
			}else{
				tooltip.append(criteriaStatusChangeBean.getCriticalDeltaValue()+"<br></b>");
			}
		}

	}
	private void getTooltipOnTime(
			CriteriaStatusChangeBean criteriaStatusChangeBean,
			StringBuilder tooltip) {
		tooltip.append("Valore Major: <b>");
		if(criteriaStatusChangeBean.getMajorValue()==null){
			tooltip.append("da definire<br></b>");
		}else{
			if(criteriaStatusChangeBean.getMajorValue().intValue()==criteriaStatusChangeBean.getMajorValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getMajorValue().intValue()+"<br></b>");

			}else{
				tooltip.append(criteriaStatusChangeBean.getMajorValue()+"<br></b>");
			}	
		}
		tooltip.append("Valore Critical: <b>");
		if(criteriaStatusChangeBean.getCriticalValue()==null){
			tooltip.append("da definire<br></b>");
		}else{
			if(criteriaStatusChangeBean.getCriticalValue().intValue()==criteriaStatusChangeBean.getCriticalValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getCriticalValue().intValue()+"<br></b>");
			}else{
				tooltip.append(criteriaStatusChangeBean.getCriticalValue()+"<br></b>");
			}
		}
	}

	private void getTooltipWithRangeValue(CriteriaStatusChangeBean criteriaStatusChangeBean,
			StringBuilder tooltip) {
		tooltip.append("Valore Major : "+"<b>");
		if(criteriaStatusChangeBean.getTypeCheck().equals(IServiceConstants.STEP_BY_STEP)){
			if(criteriaStatusChangeBean.getSecondaryMajorDeltaValue()==null){
				tooltip.append(" - ");
			}else if(criteriaStatusChangeBean.getSecondaryMajorDeltaValue().intValue()==criteriaStatusChangeBean.getSecondaryMajorDeltaValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getSecondaryMajorDeltaValue().intValue());
			}else{
				tooltip.append(criteriaStatusChangeBean.getSecondaryMajorDeltaValue());
			}
			tooltip.append(" (superiore)");
			tooltip.append(",&nbsp;");

			if(criteriaStatusChangeBean.getMajorDeltaValue()==null){
				tooltip.append(" - ");
			}else if(criteriaStatusChangeBean.getMajorDeltaValue().intValue()==criteriaStatusChangeBean.getMajorDeltaValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getMajorDeltaValue().intValue());
			}else{
				tooltip.append(criteriaStatusChangeBean.getMajorDeltaValue().doubleValue());						
			}
			tooltip.append(" (inferiore) "+"</b><br>");
			tooltip.append("Valore Critical : "+"<b>");
			
			if(criteriaStatusChangeBean.getSecondaryCriticalDeltaValue()==null){
				tooltip.append(" - ");
			}else if(criteriaStatusChangeBean.getSecondaryCriticalDeltaValue().intValue()==criteriaStatusChangeBean.getSecondaryCriticalDeltaValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getSecondaryCriticalDeltaValue().intValue());
			}else{
				tooltip.append(criteriaStatusChangeBean.getSecondaryCriticalDeltaValue().doubleValue());						
			}		

			tooltip.append(" (superiore)");
			tooltip.append(",&nbsp;");

			if(criteriaStatusChangeBean.getCriticalDeltaValue()==null){
				tooltip.append(" - ");
			}else if(criteriaStatusChangeBean.getCriticalDeltaValue().intValue()==criteriaStatusChangeBean.getCriticalDeltaValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getCriticalDeltaValue().intValue());
			}else{
				tooltip.append(criteriaStatusChangeBean.getCriticalDeltaValue());
			}
			tooltip.append(" (inferiore)"+"</b><br>");

		}else{

			if(criteriaStatusChangeBean.getSecondaryMajorValue()==null){
				tooltip.append(" - ");
			}else if(criteriaStatusChangeBean.getSecondaryMajorValue().intValue()==criteriaStatusChangeBean.getSecondaryMajorValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getSecondaryMajorValue().intValue());
			}else{
				tooltip.append(criteriaStatusChangeBean.getSecondaryMajorValue());
			}
			tooltip.append(" (superiore)");
			tooltip.append(",&nbsp;");

			if(criteriaStatusChangeBean.getMajorValue()==null){
				tooltip.append(" - ");
			}else if(criteriaStatusChangeBean.getMajorValue().intValue()==criteriaStatusChangeBean.getMajorValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getMajorValue().intValue());
			}else{
				tooltip.append(criteriaStatusChangeBean.getMajorValue().doubleValue());						
			}
			tooltip.append(" (inferiore)"+"</b><br>");
			tooltip.append("Valore Critical : "+"<b>");
			
			if(criteriaStatusChangeBean.getSecondaryCriticalValue()==null){
				tooltip.append(" - ");
			}else if(criteriaStatusChangeBean.getSecondaryCriticalValue().intValue()==criteriaStatusChangeBean.getSecondaryCriticalValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getSecondaryCriticalValue().intValue());
			}else{
				tooltip.append(criteriaStatusChangeBean.getSecondaryCriticalValue().doubleValue());						
			}		

			tooltip.append(" (superiore)");
			tooltip.append(",&nbsp;");

			if(criteriaStatusChangeBean.getCriticalValue()==null){
				tooltip.append(" - ");
			}else if(criteriaStatusChangeBean.getCriticalValue().intValue()==criteriaStatusChangeBean.getCriticalValue().doubleValue()){
				tooltip.append(criteriaStatusChangeBean.getCriticalValue().intValue());
			}else{
				tooltip.append(criteriaStatusChangeBean.getCriticalValue());
			}
			tooltip.append(" (inferiore)"+"</b><br>");
		}
	}

	private String getRenderedStatus(String status){
		if(status != null){
			StringBuilder text = new StringBuilder();
			if(status.equals(IServiceConstants.NORMAL)){
				text.append("<div style=\"background-color:#ACD373; text-align: center;\">");
				text.append("NORMAL");
				text.append("</div>");
			}else if(status.equals(IServiceConstants.MAJOR)){
				text.append("<div style=\"background-color:#F6CC3A; text-align: center;\">");
				text.append("MAJOR");
				text.append("</div>");
			}else if(status.equals(IServiceConstants.CRITICAL)){
				text.append("<div style=\"background-color:#F16243; text-align: center;\">");
				text.append("CRITICAL");
				text.append("</div>");
			}else if(status.equals(IServiceConstants.SUSPENDED)){
				text.append("<div style=\"background-color:#C2C2C2; text-align: center;\">");
				text.append("SUSPENDED");
				text.append("</div>");
			}
			return text.toString();
		}
		return status;
	}


	private String getToolTip(Integer value,String tooltip){
		StringBuilder text = new StringBuilder();
		text.append("<div \" ext:qtitle=\"\""+" ext:qtip=\"");
		text.append(tooltip);
		text.append("\">"+value+"</div>");
		return text.toString();
	}

}