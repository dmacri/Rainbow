package it.icarcnr.business.menutree.service;

import org.w3c.dom.Document;

public interface IMenuTreeBL {
	
//	public List<MenuTreeItemBean> getMenuTreeItems(List<Integer> enabledNetworkFunctionList) throws Exception;
	
	public Document getXmlMenuTree(Integer userId)  throws Exception;

}
