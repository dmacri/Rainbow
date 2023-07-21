package it.icarcnr.presentation.action.servicestatus;

import it.icarcnr.business.servicestatus.impl.ServiceLogViewBL;
import it.icarcnr.business.servicestatus.service.IServiceLogViewBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.SecurityUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class LogViewAction extends Action {

	private static final Log log = LogFactory.getLog(LogViewAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";

		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);

			Calendar calendar = Calendar.getInstance();
			Date currentDate = calendar.getTime();
			
			Map criteriaMap = request.getParameterMap();

			IServiceLogViewBL iServiceLogViewBL = new ServiceLogViewBL();
			String logFileName = iServiceLogViewBL.getLog(enabledNetworkFunctionList, currentDate, criteriaMap);

			JSONObject record=new JSONObject();

			if (logFileName!=null) {
				File filename = new File(logFileName);
				if (filename.exists()) {
					StringBuilder logContent = new StringBuilder();
					FileReader filereader = new FileReader(logFileName);
					BufferedReader fileBufferedReader = new BufferedReader(filereader);
					String logLine=fileBufferedReader.readLine();
					while(logLine!=null){
						logContent.append(logLine+"<br>");
						logLine=fileBufferedReader.readLine();
					}
					if (logContent.length()>0) {
						record.put("content", logContent.toString());
					}
					else {
						record.put("content", "Nessun Risultato");
					}
				}
				else {
					record.put("content", "<p align= center> File di log non trovato !!</p>");

				}
			}
			else {
				record.put("content", "<p align= center> Nessun log per questo servizio !!</p>");
			}

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();
			records.put(record);

			result.put("root", records);

			HttpServletResponseUtil.setJsonResponse(response);

			response.getOutputStream().println(result.toString());
			response.flushBuffer();

		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}
		finally{
			EntityManagerHelper.closeEntityManager();
		}
		return null;
	}

}
