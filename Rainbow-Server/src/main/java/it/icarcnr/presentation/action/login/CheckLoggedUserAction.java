package it.icarcnr.presentation.action.login;

import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.ISessionConstants;
import it.icarcnr.presentation.action.util.SecurityUtil;

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

public class CheckLoggedUserAction extends Action {
	
	private static final Log log = LogFactory.getLog(CheckLoggedUserAction.class);

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String method = "execute";
	
		String sessionId = SecurityUtil.getCookieSessionID(request);
		boolean alive = false;
		if (sessionId!=null){
			String jSessionId = (String)request.getSession().getAttribute(ISessionConstants.JSESSIONID);
			if (jSessionId!=null && jSessionId.equals(sessionId) && request.getSession().getId().equals(sessionId) ){
				UserLoginBean userLoginBean = SecurityUtil.getUser(request);
				if(userLoginBean!= null && userLoginBean.isEnabled()){
					alive = true;
				}
			}
		}
		
		JSONObject result = new JSONObject();
		JSONArray records = new JSONArray();
		JSONObject record = new JSONObject();
		
		try {
			
			record.put(ISessionConstants.ALIVE, alive);
			
			result.put("root", records);
			result.put("threadid",Thread.currentThread().getId());
			
			records.put(record);
			
			response.getOutputStream().print(result.toString());
			response.flushBuffer();
		} catch (Exception e) {
			log.error(method,e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,IErrorMessageConstants.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
}
