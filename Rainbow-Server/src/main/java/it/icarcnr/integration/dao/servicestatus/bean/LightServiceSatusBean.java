package it.icarcnr.integration.dao.servicestatus.bean;

import it.icarcnr.integration.dao.generated.Service;

import java.io.Serializable;

public class LightServiceSatusBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5302640363345335194L;
	
	private Service service;
	private String status;
	
	
	public LightServiceSatusBean(Service service, String status) {
		super();
		this.service = service;
		this.status = status;
	}


	public Service getService() {
		return service;
	}


	public void setService(Service service) {
		this.service = service;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
	
	

}
