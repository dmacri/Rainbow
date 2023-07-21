package it.icarcnr.presentation.action.combobox;

import it.icarcnr.business.config.util.IApplicationGlobalConstants;
import it.icarcnr.business.utility.util.FilesManager;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IComboboxActionConstants;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.ILabelFilesGeoArea;

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


public class GeoAlignListOutputFilesAction extends Action {

	private static final Log log = LogFactory.getLog(GeoAlignListOutputFilesAction.class);
	private static final String GEOAREALISTOUTPUTFILES_ESITI_LISTAGEO = "esiti_listageo";
	

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

			if (filesList==null){	
					filesList = FilesManager.listFiles(IApplicationGlobalConstants.UTILITY_GEO_FILES_PATH);
			}
				
			if(filesList!=null){
				if(loadOption!=null && loadOption.equals(IComboboxActionConstants.LOAD_OPTION_ALL_SELECT)){
					JSONObject record = new JSONObject();
					record.put("id", "-1");
					record.put("description","All");
					records.put(record);
				}
				for (int i=0; i<filesList.length; i++) {
					if (filesList[i].indexOf(GEOAREALISTOUTPUTFILES_ESITI_LISTAGEO) != -1)  {	
						if (filesList[i].endsWith(net)){
							JSONObject record = new JSONObject();
							record.put("id", filesList[i]); // name file type String
							record.put("description", ILabelFilesGeoArea.labelFilesTable.get(filesList[i]));				
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
