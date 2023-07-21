/**
 * 
 */
package it.icarcnr.business.network.bean;

import java.io.Serializable;


public class AlarmedServiceBean implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2935502226128865970L;
	
	private String nodeUniqueId;
	private String name;
	private String iconCls;
	private String status;
	
	public String getNodeUniqueId() {
		return nodeUniqueId;
	}
	public void setNodeUniqueId(String nodeUniqueId) {
		this.nodeUniqueId = nodeUniqueId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		
	
}
