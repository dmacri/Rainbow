package it.icarcnr.business.menutree.bean;

import org.json.JSONObject;

public class MenuTreeItemBean {
	
	private String id;
	private String title;
	private String iconCls;
	private String thumbnail;
	private String qtip;
	private String className;
	private JSONObject parameters;
	/**
	 * @param id
	 * @param title
	 * @param iconCls
	 * @param thumbnail
	 * @param qtip
	 * @param className
	 * @param parameters
	 */
	public MenuTreeItemBean(String id, String title, String iconCls,
			String thumbnail, String qtip, String className,
			JSONObject parameters) {
		super();
		this.id = id;
		this.title = title;
		this.iconCls = iconCls;
		this.thumbnail = thumbnail;
		this.qtip = qtip;
		this.className = className;
		this.parameters = parameters;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getQtip() {
		return qtip;
	}
	public void setQtip(String qtip) {
		this.qtip = qtip;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public JSONObject getParameters() {
		return parameters;
	}
	public void setParameters(JSONObject parameters) {
		this.parameters = parameters;
	}
	
	
}