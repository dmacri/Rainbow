package it.icarcnr.presentation.action.combobox;

//import it.icarcnr.business.district.impl.DistrictBL;
//import it.icarcnr.business.district.service.IDistrictBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
//import it.icarcnr.integration.dao.generated.Prefix;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IComboboxActionConstants;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
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

import java.util.List;


public class GeoAreaDistrictListAction extends Action{
	private static final Log log = LogFactory.getLog(GeoAreaDistrictListAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

//		final String method = "execute";
//		try {
//			
//			int start = request.getParameter("start")!=null?Integer.valueOf( request.getParameter("start")):0;
//			int limit = request.getParameter("limit")!=null?Integer.valueOf( request.getParameter("limit")):30;
//			String prefixValue = request.getParameter("query");
//			
//			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
//			
//			String loadOption = request.getParameter(IComboboxActionConstants.LOAD_OPTION);
//			HttpServletResponseUtil.setJsonResponse(response);
//
//			JSONObject result = new JSONObject();
//			JSONArray records = new JSONArray();
//			
//			IDistrictBL idistrictBL = new DistrictBL();
//        	List<Prefix> prefixes = idistrictBL.getDistricts(enabledNetworkFunctionList,prefixValue,start,limit);
//        	
//        	JSONObject record=null;
//        	for (Prefix prefix : prefixes){
//        		record = new JSONObject();
//        		record.put("id", prefix.getId());
//        		record.put("description", prefix.getPrefix());
//        		records.put(record);
//        	}
//        	
//			result.put("root", records);
//			result.put("totalCount", idistrictBL.getTotalDistricts(enabledNetworkFunctionList,prefixValue));
//			result.put("threadid",Thread.currentThread().getId());
//
//			HttpServletResponseUtil.setJsonResponse(response);
//			
//			response.getOutputStream().println(result.toString());
//			response.flushBuffer();
//			
//		} catch (Exception e) {
//			log.error(method,e);
//			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,IErrorMessageConstants.INTERNAL_SERVER_ERROR);
//		}
//		finally{
//			EntityManagerHelper.closeEntityManager();
//		}
		return null;
	}

}



