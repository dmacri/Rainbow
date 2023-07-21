package it.icarcnr.presentation.action.login;

import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.ISessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserLoginInfoAction extends Action {

	private static final Log log = LogFactory.getLog(UserLoginInfoAction.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String method = "execute";

		try {
			
			HttpSession session =  request.getSession();
			UserLoginBean userLoginBean = (UserLoginBean)session.getAttribute(ISessionConstants.USER_SESSION);

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();
			JSONObject record = new JSONObject();

			record.put("name", userLoginBean.getName());
			record.put("surname", userLoginBean.getSurname());
			record.put("username", userLoginBean.getUsername());

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
