/**
 * 
 */
package it.icarcnr.business.network.bean;

import it.icarcnr.integration.dao.util.IServiceConstants.Status;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkItemBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4974385024281643190L;
	
	private String id;
	private Integer nodeId;
	private Integer networkId;
	private Integer functionId;
	private String name;
	private String fullName;
	private Status status;
	private Status childrenStatus;
	private String image;
	private List<NetworkItemBean> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getNodeId() {
		return nodeId;
	}
	public Integer getNetworkId() {
		return networkId;
	}
	public void setNetworkId(Integer networkId) {
		this.networkId = networkId;
	}
	public Integer getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Status getStatus() {
		return status;
	}
	public Status getChildrenStatus() {
		return childrenStatus;
	}
	public void setChildrenStatus(Status childrenStatus) {
		this.childrenStatus = childrenStatus;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<NetworkItemBean> getChildren() {
		return children;
	}
	public void setChildren(List<NetworkItemBean> children) {
		this.children = children;
	}
	
	public JSONObject toJSONObject (){
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("name", name);
			JSONObject data = new JSONObject();
			if(status!=null){
				data.put("status", status.getSeverityName());
			}
			if(childrenStatus!=null){
				data.put("childrenStatus", childrenStatus.getSeverityName());
			}
			data.put("image", image);
			data.put(IParameterHttpServletRequestContants.NODE_SENSOR_ID, networkId);
			data.put(IParameterHttpServletRequestContants.SENSOR_ID, functionId);
			data.put(IParameterHttpServletRequestContants.NODE_ID, nodeId);
			data.put("fullName", fullName);
			jsonObject.put("data", data);
			JSONArray childrenJSONArray = new JSONArray();
			for (NetworkItemBean networkItemBean : children) {
				childrenJSONArray.put(networkItemBean.toJSONObject());
			}
			jsonObject.put("children", childrenJSONArray);
			
		} catch (JSONException e) {
			
		}
		return jsonObject;
	}

}
