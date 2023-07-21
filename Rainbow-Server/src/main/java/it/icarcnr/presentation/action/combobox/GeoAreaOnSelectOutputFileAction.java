package it.icarcnr.presentation.action.combobox;

import it.icarcnr.business.config.util.IApplicationGlobalConstants;
import it.icarcnr.business.utility.util.FilesManager;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;

import java.io.File;

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


public class GeoAreaOnSelectOutputFileAction extends Action {

	private static final Log log = LogFactory.getLog(GeoAreaOnSelectOutputFileAction.class);
	private static final String GEO_ALIGN = "align";
	private static final String GEO_PREFIX = "prefix";
	private static final String GEOPREFIX_NETWORK_PK3 = "PK3";
	private static final String GEOPREFIX_NETWORK_PK0 = "PK0";


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String method = "execute";
		try {
			final String fileName = request.getParameter("filesId");
			final String geo = request.getParameter("geo");
			final String net = request.getParameter("net");
			String fileFullName = null;

			if (geo.equals(GEO_ALIGN)){
				fileFullName = IApplicationGlobalConstants.UTILITY_GEO_FILES_PATH+File.separator+fileName;
			}
			else if (geo.equals(GEO_PREFIX)){
				if (net.equals(GEOPREFIX_NETWORK_PK3)){
					fileFullName = IApplicationGlobalConstants.UTILITY_GEOPREFIX_PK3_FILES_PATH+File.separator+fileName;
				}
				else if (net.equals(GEOPREFIX_NETWORK_PK0)){
					fileFullName = IApplicationGlobalConstants.UTILITY_GEOPREFIX_PK0_FILES_PATH+File.separator+fileName;
				}
			}
			File file = new File(fileFullName);
			String fileContent = "File non disponibile";
			if (file.exists()){
				fileContent = FilesManager.fileReader(fileFullName,"<br>");
			}

			JSONObject record=new JSONObject();

			record.put("outputFile", fileContent);
			JSONObject result = new JSONObject();
			JSONArray records = new JSONArray();
			records.put(record);

			result.put("root", records);

			HttpServletResponseUtil.setJsonResponse(response);

			response.getOutputStream().println(result.toString());
			response.flushBuffer();

		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
		finally{
			EntityManagerHelper.closeEntityManager();
		}
		return null;
	}		

}

