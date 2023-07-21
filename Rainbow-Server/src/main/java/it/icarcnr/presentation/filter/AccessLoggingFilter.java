/**
 * 
 */
package it.icarcnr.presentation.filter;

import it.icarcnr.business.access.impl.AccessLog;
import it.icarcnr.business.access.service.IAccessLog;
import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.presentation.action.util.SecurityUtil;
import it.icarcnr.presentation.action.util.UserAgentUtil;
import it.icarcnr.presentation.util.StatusExposingServletResponse;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AccessLoggingFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        StatusExposingServletResponse statusExposingServletResponse = new StatusExposingServletResponse((HttpServletResponse)response);
        UserLoginBean userLoginBean = SecurityUtil.getUser((HttpServletRequest)request);
        chain.doFilter(request, statusExposingServletResponse);
        logAccess((HttpServletRequest)request, statusExposingServletResponse, userLoginBean);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @param request
	 * @param response
	 */
	private void logAccess(HttpServletRequest request,
			StatusExposingServletResponse response, UserLoginBean userLoginBean) {

		StringBuilder message = new StringBuilder();
		
		message.append("SI: ");
		String sessionId = SecurityUtil.getCookieSessionID(request);
		message.append(sessionId);
		
		message.append(" Us: ");
		if(userLoginBean == null){
			userLoginBean = SecurityUtil.getUser(request);
		}
		message.append("[");
		if(userLoginBean!=null){
			message.append(userLoginBean.getUsername());
		}else{
			message.append("not logged");
		}
		message.append("]");

		message.append(" Req: ");
		message.append(request.getRequestURL());

		message.append(" SC: ");
		message.append( response.getStatusCode() +" ["+response.getStatusMessage()+"]");

		String userAgent = request.getHeader("User-Agent");
		String[] operatingSystem = UserAgentUtil.getOS(userAgent);
		String[] browser = UserAgentUtil.getBrowser(userAgent);
		

		message.append(" OS: ");
		message.append(operatingSystem[2]);
		message.append(" Br: ");
		message.append(browser[2]);
		message.append(" LA: ");
		message.append(request.getLocalAddr());
		message.append(" LN: ");
		message.append(request.getLocalName());
		message.append(" LP: ");
		message.append(request.getLocalPort());

		IAccessLog iAccessLog = AccessLog.getInstance();
		iAccessLog.log(message);
	}

}
