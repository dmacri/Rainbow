//package it.icarcnr.presentation.action.job;
//
//import it.icarcnr.business.job.impl.JobBL;
//import it.icarcnr.business.job.service.IJobBL;
//import it.icarcnr.integration.dao.generated.EntityManagerHelper;
//import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
//import it.icarcnr.presentation.action.util.IErrorMessageConstants;
//import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;
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
//public class ChangeCommentAction extends Action {
//	private static final Log log = LogFactory.getLog(AnomalyTakingCareAction.class);
//
//	@Override
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//	throws Exception {
//		final String method = "execute";
//		IJobBL iJobBL = new JobBL();
//
//		try {
//			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
//
//			Integer jobId = request.getParameter(IParameterHttpServletRequestContants.JOB_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.JOB_ID)):null;			
//			String comment = request.getParameter("comment");
//
//			
//			JSONObject result = new JSONObject();
//			JSONArray errors = new JSONArray();
//
//			
//			iJobBL.changeCommentJob(enabledNetworkFunctionList, jobId,  comment);
//
//			result.put("success", iJobBL!=null);
//
//			result.put("errors", errors);
//
//			HttpServletResponseUtil.setJsonResponse(response);
//
//			response.getOutputStream().println(result.toString());
//			response.flushBuffer();
//
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
