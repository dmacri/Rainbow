/**
 * 
 */
package it.icarcnr.business.servicestatus.bean;

import java.io.Serializable;


public class ServiceSuspendedInfoBean implements Serializable {
	
	Integer serviceOperationId;
	String nameServiceOperation;
	Boolean criteriaEnabled;
	Boolean criteriaSuspended;
	Boolean criteriaSuspendedSystem;
	
	
	
	public Integer getServiceOperationId() {
		return serviceOperationId;
	}
	public void setServiceOperationId(Integer serviceOperationId) {
		this.serviceOperationId = serviceOperationId;
	}
	public String getNameServiceOperation() {
		return nameServiceOperation;
	}
	public void setNameServiceOperation(String nameServiceOperation) {
		this.nameServiceOperation = nameServiceOperation;
	}
	public Boolean getCriteriaEnabled() {
		return criteriaEnabled;
	}
	public void setCriteriaEnabled(Boolean criteriaEnabled) {
		this.criteriaEnabled = criteriaEnabled;
	}
	public Boolean getCriteriaSuspended() {
		return criteriaSuspended;
	}
	public void setCriteriaSuspended(Boolean criteriaSuspended) {
		this.criteriaSuspended = criteriaSuspended;
	}
	public Boolean getCriteriaSuspendedSystem() {
		return criteriaSuspendedSystem;
	}
	public void setCriteriaSuspendedSystem(Boolean criteriaSuspendedSystem) {
		this.criteriaSuspendedSystem = criteriaSuspendedSystem;
	}

}
