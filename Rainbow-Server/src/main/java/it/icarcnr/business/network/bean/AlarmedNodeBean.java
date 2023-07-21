/**
 * 
 */
package it.icarcnr.business.network.bean;

import java.io.Serializable;
import java.util.List;


public class AlarmedNodeBean implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7909699079955148682L;
	
	private String nodeUniqueId;
	private String name;
	private String iconCls;
	private String status;
	private Integer functionId;
	
	private List<AlarmedServiceBean> alarmedServices;
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
	public List<AlarmedServiceBean> getAlarmedServices() {
		return alarmedServices;
	}
	public void setAlarmedServices(List<AlarmedServiceBean> alarmedServices) {
		this.alarmedServices = alarmedServices;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Integer integer) {
		this.functionId = integer;
	}
	
	
}
