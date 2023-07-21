package it.icarcnr.presentation.action.menutree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DashboardAction extends Action {

	private static final Log log = LogFactory.getLog(DashboardAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String method = "execute";

//		try {
//			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
//
//			JSONObject result = new JSONObject();
//			JSONArray records = new JSONArray();
//
//			IMenuTreeBL tree = new MenuTreeBL();
//
//			List<MenuTreeItemBean> listitem=tree.getMenuTreeItems(enabledNetworkFunctionList);
//
//			for (MenuTreeItemBean iterator : listitem) {
//				JSONObject record = new JSONObject();
//				record.put("id", iterator.getId());
//				record.put("title", iterator.getTitle());
//				record.put("iconCls", iterator.getIconCls());
//				record.put("thumbnail", iterator.getThumbnail());
//				record.put("qtip", iterator.getQtip());
//				record.put("className", iterator.getClassName());
//				record.put("parameters", iterator.getParameters());
//				records.put(record);
//			}
//
//			result.put("root", records);			
//			HttpServletResponseUtil.setJsonResponse(response);
//
//			response.getOutputStream().println(result.toString());
//			response.flushBuffer();
//		} catch (Exception e) {
//			log.error(method,e);
//			throw e;
//		}
//		finally{
//			EntityManagerHelper.closeEntityManager();
//		}
		return null;

	}
}
