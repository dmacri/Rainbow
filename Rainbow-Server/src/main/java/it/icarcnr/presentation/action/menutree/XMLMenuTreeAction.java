package it.icarcnr.presentation.action.menutree;

import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.business.menutree.impl.MenuTreeBL;
import it.icarcnr.business.menutree.service.IMenuTreeBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.presentation.action.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;

public class XMLMenuTreeAction extends Action {

	private static final Log log = LogFactory.getLog(XMLMenuTreeAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String method = "execute";
		try {
		
			UserLoginBean userLoginBean = SecurityUtil.getUser(request);
			
			IMenuTreeBL menuTree = new MenuTreeBL();
			
			Document xmlMenuTree =menuTree.getXmlMenuTree(userLoginBean.getId());

			response.reset();
			response.setContentType("text/xml; charset=ISO-8859-1");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			DOMSource source = new DOMSource(xmlMenuTree);
			StreamResult result = new StreamResult(response.getWriter());
			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.transform(source, result); 
			
			response.flushBuffer();
	
		} catch (Exception e) {
			log.error(method,e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Internal server error");
			throw e;
		}
		finally{
			EntityManagerHelper.closeEntityManager();
		}
		return null;

	}
}
