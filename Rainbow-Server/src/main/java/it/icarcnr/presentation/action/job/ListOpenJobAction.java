//package it.icarcnr.presentation.action.job;
//
//import it.icarcnr.business.job.bean.TakingChargeBean;
//import it.icarcnr.business.job.impl.JobBL;
//import it.icarcnr.business.job.service.IJobBL;
//import it.icarcnr.business.job.util.IJobContants;
//import it.icarcnr.business.login.bean.UserLoginBean;
//import it.icarcnr.business.security.impl.SecurityBL;
//import it.icarcnr.business.security.service.ISecurityBL;
//import it.icarcnr.integration.dao.generated.EntityManagerHelper;
//import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
//import it.icarcnr.presentation.action.util.IActionConstants;
//import it.icarcnr.presentation.action.util.IErrorMessageConstants;
//import it.icarcnr.presentation.action.util.SecurityUtil;
//import it.icarcnr.util.IDateFormatUtil;
//
//import java.text.SimpleDateFormat;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.struts.action.Action;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class ListOpenJobAction extends Action {
//
//	private static final Log log = LogFactory.getLog(ListOpenJobAction.class);
//	
//	@Override
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//	throws Exception {
//		final String method = "execute";
//
//		try {
//			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
//			
//			JSONObject result = new JSONObject();
//			JSONArray records = new JSONArray();
//
//			IJobBL iJobBL = new JobBL();
//			UserLoginBean userLoginBean = SecurityUtil.getUser(request);
//			ISecurityBL iSecurityBL = new SecurityBL();
//			
//			List<TakingChargeBean> takingChargeBeans = iJobBL.getTakingChargeBeans(enabledNetworkFunctionList,IJobContants.OPEN_STATUS);
//			SimpleDateFormat dateTimeFormat= new SimpleDateFormat(IDateFormatUtil.DATE_TIME_FORMAT);
//			for (TakingChargeBean takingChargeBean : takingChargeBeans) {
//				JSONObject record = new JSONObject();	
//				if(takingChargeBean.getUserId() == userLoginBean.getId()){
//					record.put("userDetected", true);
//				}else{
//					record.put("userDetected", false);
//				}
//				record.put("jobId", takingChargeBean.getJobId());
//				record.put("acknowledgementPermission", iSecurityBL.hasPermission(userLoginBean.getId(), IActionConstants.ACKNOLEDGMENT_ACTION_PATH, takingChargeBean.getNetworkId(), takingChargeBean.getFunctionId()));
//				record.put("status", takingChargeBean.getServiceStatus());
//				String lastCheck = takingChargeBean.getLastCheck() != null ? dateTimeFormat.format(takingChargeBean.getLastCheck()) : null;
//				record.put("lastCheck", lastCheck);
//				record.put("sources",takingChargeBean.getSources());
//				record.put("description",takingChargeBean.getDescription());
//				record.put("serviceDescription", takingChargeBean.getServiceDescription());
//				record.put("reference", takingChargeBean.getReference());
//				record.put("nodeFrom", takingChargeBean.getNodeFrom());
//				record.put("nodeTo", takingChargeBean.getNodeTo());
//				record.put("user",takingChargeBean.getUser());
//				record.put("comment",takingChargeBean.getComment());
//				String startDate = takingChargeBean.getJobStartDate() != null ? dateTimeFormat.format(takingChargeBean.getJobStartDate()) : null;
//				record.put("startDate",startDate);
//				record.put("serviceId", takingChargeBean.getServiceId());
//				record.put("networkName", takingChargeBean.getNetworkName());
//				record.put("functionName", takingChargeBean.getFunctionName());
//				record.put("controlThreshold", takingChargeBean.getSuspendThreshold());
//				records.put(record);
//			}
//
//			result.put("root", records);
//			result.put("totalCount", takingChargeBeans.size());
//			result.put("threadid",Thread.currentThread().getId());
//
//			HttpServletResponseUtil.setJsonResponse(response);
//
//			response.getOutputStream().println(result.toString());
//			response.flushBuffer();
//		} catch (Exception e) {
//			log.error(method,e);
//			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,IErrorMessageConstants.INTERNAL_SERVER_ERROR);
//		}
//		finally{
//			EntityManagerHelper.closeEntityManager();
//		}
//		return null;
//	}
//
//}
