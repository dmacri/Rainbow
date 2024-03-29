package it.icarcnr.presentation.action.login;

import it.icarcnr.business.config.util.IApplicationGlobalConstants;
import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.business.login.impl.LoginUserBL;
import it.icarcnr.business.login.service.ILoginUserBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.ISessionConstants;
import it.icarcnr.util.IDateUtilConstants;

import java.util.Calendar;

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

public class LoginAction extends Action {

	private static final Log log = LogFactory.getLog(LoginAction.class);


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";

		String userName = request.getParameter("userName");
		String password = request.getParameter("pswd");
		ILoginUserBL iLogin = new LoginUserBL();
		UserLoginBean userLoginBean = null;
		Integer accessAttempts = null;

		try {
			HttpServletResponseUtil.setJsonResponse(response);

			JSONObject result = new JSONObject();
			JSONArray errors = new JSONArray();


			HttpSession httpSession = request.getSession();
			if(httpSession != null){
				accessAttempts = httpSession.getAttribute(ISessionConstants.ACCESS_ATTEMPTS) != null? (Integer)httpSession.getAttribute(ISessionConstants.ACCESS_ATTEMPTS):0;
				accessAttempts++;
				httpSession.setAttribute(ISessionConstants.ACCESS_ATTEMPTS,accessAttempts);
				if(accessAttempts <= IApplicationGlobalConstants.MAX_ATTEMPS_LOGIN){
					userLoginBean = iLogin.login(userName, password);
					accessAttempts = 0; //reset accessAttemps
				}
			}

			if(userLoginBean!=null){
				request.getSession().setAttribute(ISessionConstants.USER_SESSION, userLoginBean);
				request.getSession().setAttribute(ISessionConstants.JSESSIONID, request.getSession().getId());

				if(userLoginBean.getFirstAccess()){
					result.put("success", false);
					JSONObject error = new JSONObject();
					error.put("id", "firstAccess");
//					error.put("msg", "First access");
					error.put("msg", "Primo accesso");
					errors.put(error);
				}else if (userLoginBean.getExpired()){
					result.put("success", false);
					JSONObject error = new JSONObject();
					error.put("id", "expiredPassword");
//					error.put("msg", "Expired password");
					error.put("msg", "Password scaduta");
					errors.put(error);
				}
				else {
					result.put("success", true);
					if(userLoginBean.getExpiryDate()!=null){
						long expiryDate = userLoginBean.getExpiryDate().getTime();
						long currentDate = Calendar.getInstance().getTimeInMillis();
						double dif = (double)(expiryDate-currentDate) ;
						int daysToExpiryDate = (int) Math.ceil(dif/IDateUtilConstants.MILLISECONDS_FOR_DAY);
						if(daysToExpiryDate<=7){
							JSONObject error = new JSONObject();
							error.put("id", "expiringPassword");
//							error.put("msg", "Your password will expire in "+daysToExpiryDate+" days");
							error.put("msg", "La tua password scadr� tra "+daysToExpiryDate+" giorni");
							errors.put(error);
						}

					}

				}
			}else{
				result.put("success", false);
				JSONObject error = new JSONObject();
				error.put("id", "loginFailed");
//				String msg = "Login failed!";
				String msg = "Login fallita!";
				if(accessAttempts > IApplicationGlobalConstants.MAX_ATTEMPS_LOGIN){
//					msg = msg + " Number of attempts for logging is exhausted.";
					msg = msg + " Numero di tentativi a disposizione esaurito.";
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}else{
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
				error.put("msg", msg);
				errors.put(error);
			}

			result.put("errors", errors);

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
