package it.icarcnr.business.menutree.util;

import it.icarcnr.integration.dao.generated.Action;
import it.icarcnr.integration.dao.generated.Function;
import it.icarcnr.integration.dao.generated.Network;
import it.icarcnr.integration.dao.generated.Node;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.generated.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserInterfaceUtil {
	
	private static final Log log = LogFactory.getLog(UserInterfaceUtil.class);
	
	
	public static String getNodeIcon(Node node) {
		final String method = "getNodeIcon(Node node)";
		String icon = IUserInterfaceConstants.GRAPH_ICON_PATH+IUserInterfaceConstants.nodeTypeIcon[0];
		Integer id = node.getType().getId();
		if(id<IUserInterfaceConstants.nodeTypeIcon.length && IUserInterfaceConstants.nodeTypeIcon[id]!=null){
			icon = IUserInterfaceConstants.GRAPH_ICON_PATH+IUserInterfaceConstants.nodeTypeIcon[id];
		}else{
			log.error("Method: "+method+" - error: Node Type icon not found - id: ("+id+")");
		}
		return icon;
	}
	
	public static String getNodeIcon(Network network) {
		final String method = "getNodeIcon(Network network)";
		String icon = IUserInterfaceConstants.GRAPH_ICON_PATH+IUserInterfaceConstants.networkIcon[0];
		Integer id = network.getId();
		if(id<IUserInterfaceConstants.networkIcon.length && IUserInterfaceConstants.networkIcon[id]!=null){
			icon = IUserInterfaceConstants.GRAPH_ICON_PATH + IUserInterfaceConstants.networkIcon[id];
		}else{
			log.error("Method: "+method+" - error: Network icon not found - id: ("+id+")");
			}
		return icon;
	}
	
	public static String getNodeIcon(Function function) {
		final String method = "getNodeIcon(Function function))";
		String icon = IUserInterfaceConstants.GRAPH_ICON_PATH+IUserInterfaceConstants.functionIcon[0];
		Integer id = function.getId();
		if(id<IUserInterfaceConstants.functionIcon.length && IUserInterfaceConstants.functionIcon[id]!=null){
			icon = IUserInterfaceConstants.GRAPH_ICON_PATH+IUserInterfaceConstants.functionIcon[id];
		}else{
			log.error("Method: "+method+" - error: Function icon not found - id: ("+id+")");
		}
		return icon;
	}
	
	public static String getNodeIconCls(Node node) {
		final String method = "getNodeIconCls(Node node)";
		String iconCls = null;
		Integer id = node.getType().getId();
		if(id<IUserInterfaceConstants.nodeTypeIconCls.length && IUserInterfaceConstants.nodeTypeIconCls[id]!=null){
			iconCls = IUserInterfaceConstants.nodeTypeIconCls[id];
		}else{
			log.error("Method: "+method+" - error: Node Type icon Css not found - id: ("+id+")");
		}
		return iconCls;
	}

	public static String getNodeIconCls(Type nodeType) {
		final String method = "getNodeIconCls(Type nodeType)";
		String iconCls = null;
		Integer id = nodeType.getId();
		if(id<IUserInterfaceConstants.nodeTypeIconCls.length && IUserInterfaceConstants.nodeTypeIconCls[id]!=null ){
			iconCls = IUserInterfaceConstants.nodeTypeIconCls[id];
		}else{
			log.error("Method: "+method+" - error: Node Type Css icon not found - id: ("+id+")");
		}
		return iconCls;
	}

	public static String getNodeIconCls(Service service) {
		final String method = "getNodeIconCls(Service service)";
		String iconCls = null;
		Integer id = service.getFunction().getId();
		if(id<IUserInterfaceConstants.functionIconCls.length && IUserInterfaceConstants.functionIconCls[id]!=null ){
			iconCls = IUserInterfaceConstants.functionIconCls[id];
		}else{
			log.error("Method: "+method+" - error: Service icon Css not found - id: ("+id+")");
		}
		return iconCls;
	}

	public static String getNodeIconCls(Function function) {
		final String method = "getNodeIconCls(Function function)";
		String iconCls = null;
		Integer idFunction = function.getId();
		if(idFunction<IUserInterfaceConstants.functionIconCls.length && IUserInterfaceConstants.functionIconCls[idFunction]!=null ){
			iconCls = IUserInterfaceConstants.functionIconCls[idFunction];
		}else{
			log.error("Method: "+method+" - error: Function icon Css not found - id: ("+idFunction+")");
		}
		return iconCls;
	}
	
	public static String getNodeIconCls(Network network) {
		final String method = "getNodeIconCls(Network network)";
		String iconCls = null;
		Integer id = network.getId();
		if(id<IUserInterfaceConstants.networkIconCls.length && IUserInterfaceConstants.networkIconCls[id]!=null){
			iconCls = IUserInterfaceConstants.networkIconCls[id];
		}else{
			log.error("Method: "+method+" - error: Network icon not found - id: ("+id+")");
		}
		return iconCls;
	}
	
	public static String getNodeIconCls(Action action) {
		final String method = "getNodeIconCls(Action action)";
		String actionPath = action.getPath();
		String iconCls = IUserInterfaceConstants.utilityIconCls.get(actionPath);
		if(iconCls==null){
			log.error("Method: "+method+" - error: Utility icon Css not found - action path: ("+actionPath+")");
		}
		return iconCls;
	}
	
	
	public static String getNodeTitle(Action action) {
		final String method = "getNodeTitle(Action action)";
		String actionPath = action.getPath();
		String title = IUserInterfaceConstants.utilityTitle.get(actionPath);
		if(title==null){
			log.error("Method: "+method+" - error: Utility title not found - action path: ("+actionPath+")");
		}
		return title;
	}
	
	public static String getClassName(Action action) {
		final String method = "getClassName(Action action)";
		String actionPath = action.getPath();
		String title = IUserInterfaceConstants.utilityClassName.get(actionPath);
		if(title==null){
			log.error("Method: "+method+" - error: Utility Class Name not found- action path: ("+actionPath+")");
		}
		return title;
	}

}
