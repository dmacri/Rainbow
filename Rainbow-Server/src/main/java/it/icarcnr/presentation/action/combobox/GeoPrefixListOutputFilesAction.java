package it.icarcnr.presentation.action.combobox;
import it.icarcnr.business.config.util.IApplicationGlobalConstants;
import it.icarcnr.business.utility.util.FilesManager;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IComboboxActionConstants;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;

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


public class GeoPrefixListOutputFilesAction extends Action {

	private static final Log log = LogFactory.getLog(GeoPrefixListOutputFilesAction.class);
	private static final String GEOPREFIXLISTOUTPUTFILES_ESITI_DISTRETTO_PK3 = "esiti_geoarea_";
	private static final String GEOPREFIXLISTOUTPUTFILES_ESITI_DISTRETTO_PK0_VTCD = "esiti_VTCD";
	private static final String GEOPREFIXLISTOUTPUTFILES_ESITI_DISTRETTO_PK0_BSH = "esiti_BSH";
	private static final String GEOPREFIX_NETWORK_PK3 = "PK3";
	private static final String GEOPREFIX_NETWORK_PK0 = "PK0";


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";
		try {
			String loadOption = request.getParameter(IComboboxActionConstants.LOAD_OPTION);
			String net = request.getParameter("net");

			String[] filesList = null;

			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();

			if (filesList==null) {
				if (net.equals(GEOPREFIX_NETWORK_PK3)){
					filesList = FilesManager.listFiles(IApplicationGlobalConstants.UTILITY_GEOPREFIX_PK3_FILES_PATH);    
				}
				else if (net.equals(GEOPREFIX_NETWORK_PK0)){
					filesList = FilesManager.listFiles(IApplicationGlobalConstants.UTILITY_GEOPREFIX_PK0_FILES_PATH);   
				}
			}
			if(filesList!=null){
				if(loadOption!=null && loadOption.equals(IComboboxActionConstants.LOAD_OPTION_ALL_SELECT)){
					JSONObject record = new JSONObject();
					record.put("id", "-1");
					record.put("description","All");
					records.put(record);
				}
				for (int i=0; i<filesList.length; i++) {
					if (net.equals(GEOPREFIX_NETWORK_PK3)){
						if  (filesList[i].indexOf(GEOPREFIXLISTOUTPUTFILES_ESITI_DISTRETTO_PK3) != -1)  {	
							if (filesList[i].endsWith("public")){
								JSONObject record = new JSONObject();
								record.put("id", filesList[i]); // name file type String
								record.put("description", filesList[i]);				
								records.put(record); 
							}
						} 
					}
					else if ( (net.equals(GEOPREFIX_NETWORK_PK0)) &&(!filesList[i].endsWith("public") )){
						if  (  (filesList[i].indexOf(GEOPREFIXLISTOUTPUTFILES_ESITI_DISTRETTO_PK0_VTCD) != -1)  || 
								( (filesList[i].indexOf(GEOPREFIXLISTOUTPUTFILES_ESITI_DISTRETTO_PK0_BSH) ) != -1) )     
						{
							JSONObject record = new JSONObject();
							record.put("id", filesList[i]); // name file type String
							record.put("description", filesList[i]);				
							records.put(record); 
						}
					}	

				}
			}
			if(records.length() == 0){
				JSONObject record = new JSONObject();
				record.put("id", "-2");
				record.put("description","File non disponibili");
				records.put(record);
			}

			result.put("root", records);
			result.put("threadid",Thread.currentThread().getId());

			HttpServletResponseUtil.setJsonResponse(response);

			response.getOutputStream().println(result.toString());
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
