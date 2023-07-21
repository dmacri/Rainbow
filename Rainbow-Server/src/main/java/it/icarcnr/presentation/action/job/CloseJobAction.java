//package it.icarcnr.presentation.action.job;
//
//import it.icarcnr.business.job.impl.JobBL;
//import it.icarcnr.business.job.service.IJobBL;
//import it.icarcnr.business.login.bean.UserLoginBean;
//import it.icarcnr.integration.dao.generated.EntityManagerHelper;
//import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
//import it.icarcnr.presentation.action.util.IErrorMessageConstants;
//import it.icarcnr.presentation.action.util.ISessionConstants;
//import it.icarcnr.presentation.action.util.SecurityUtil;
//
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
//public class CloseJobAction extends Action {
//	
//	private static final Log log = LogFactory.getLog(CloseJobAction.class);
//	
//	@Override
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		final String method = "execute";
//
//		try {
//			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
//			
//			Integer jobId = request.getParameter("jobId")!=null?Integer.valueOf(request.getParameter("jobId")):null;
//			
//			UserLoginBean userLoginBean = (UserLoginBean) request.getSession().getAttribute(ISessionConstants.USER_SESSION);
//			Integer userId = userLoginBean!=null?userLoginBean.getId():null;
//
//			JSONObject result = new JSONObject();
//			JSONArray records = new JSONArray();
//
//			IJobBL iJobBL = new JobBL();
//			Boolean success = iJobBL.close(enabledNetworkFunctionList,userId, jobId);
//
//			JSONObject record = new JSONObject();
//			record.put("success", success);
//			records.put(record);
//
//			result.put("root", records);
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
//}
