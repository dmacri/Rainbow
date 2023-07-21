/**
 * 
 */
package it.icarcnr.presentation.action.util;


import javax.servlet.http.HttpServletResponse;


public class HttpServletResponseUtil {
	
	public static void setJsonResponse(HttpServletResponse response){
		response.reset();
		response.setContentType("application/json; charset=ISO-8859-1");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}
}
