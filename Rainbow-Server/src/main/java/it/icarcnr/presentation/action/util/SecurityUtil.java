package it.icarcnr.presentation.action.util;

import it.icarcnr.business.login.bean.UserLoginBean;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

public class SecurityUtil {

	private static final Log log = LogFactory.getLog(SecurityUtil.class);

	public enum Role{USER_NOT_LOGGED,USER_LOGGED,USER_ENABLED,USER_ENABLED_CHECK_PERMISSION};

	public static Integer getArea(ActionMapping mapping){
		final String method = "getArea(ActionMapping mapping)";
		Integer idArea = null;
		String parameter = mapping.getParameter();
		JSONObject jsonParameter;
		try {
			jsonParameter = new JSONObject(parameter);
			idArea = jsonParameter.has("area")?jsonParameter.getInt("area"):null;
		} catch (JSONException e) {
			log.error(method, e);
		}
		return idArea;
	}

	public static UserLoginBean getUser(HttpServletRequest request){
		UserLoginBean userLoginBean = null;
		HttpSession httpSession = request.getSession();
		if(httpSession !=null){
			Object object = httpSession.getAttribute(ISessionConstants.USER_SESSION);
			if(object instanceof UserLoginBean){
				userLoginBean = (UserLoginBean)object;
			}
		}
		return userLoginBean;
	}


	public static Role getRole(ActionMapping mapping){
		Role role = Role.USER_ENABLED;
		String[] roles = mapping.getRoleNames();
		if(roles.length>0){
			role = Role.valueOf(roles[0]);
		}
		return role;
	}
	
	public static List<Integer> getEnabledNetworkFunctionList(HttpServletRequest request){
		return (List<Integer>)request.getAttribute(IRequestConstants.ENABLED_NETWORK_FUNCTION_LIST);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getSessionID(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		String sessionId = null;
		if(httpSession !=null){
			sessionId = (String)request.getSession().getAttribute(ISessionConstants.JSESSIONID);
		}
		return sessionId;
	}
	
	/**
	 * @param request
	 * @return
	 */
	public static String getCookieSessionID(HttpServletRequest request) {
		String sessionId = null;
		Cookie[] cookies = request.getCookies();
		boolean found = false;
		if (cookies != null) {
			for (int i =0; !found && i< cookies.length; i++) {
				Cookie cookie = cookies[i];
				if(cookie.getName().equals(ISessionConstants.JSESSIONID)){
					sessionId = cookie.getValue();
					found = true;
				}
			}
		}
		return sessionId;
	}




}
