package it.icarcnr.business.servicestatus.bean;

import java.io.Serializable;
import java.util.Date;

public class CriteriaStatusChangeBean implements Serializable {
	
	private String fromStatus;
	private String toStatus;
	private String target;
	private Number value;
	private Date date;
	private String operation;
	
	private Double majorValue;
	private Double criticalValue;
	private Double majorDeltaValue;
	private Double criticalDeltaValue;
	
	
	
	private Double secondaryMajorValue;
	private Double secondaryCriticalValue;
	private Double secondaryMajorDeltaValue;
	private Double secondaryCriticalDeltaValue;
	private String typeCheck;
	
	public String getFromStatus() {
		return fromStatus;
	}
	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}
	public String getToStatus() {
		return toStatus;
	}
	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Number getValue() {
		return value;
	}
	public void setValue(Number value) {
		this.value = value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Double getMajorValue() {
		return majorValue;
	}
	public void setMajorValue(Double majorValue) {
		this.majorValue = majorValue;
	}
	public Double getCriticalValue() {
		return criticalValue;
	}
	public void setCriticalValue(Double criticalValue) {
		this.criticalValue = criticalValue;
	}
	public Double getMajorDeltaValue() {
		return majorDeltaValue;
	}
	public void setMajorDeltaValue(Double majorDeltaValue) {
		this.majorDeltaValue = majorDeltaValue;
	}
	public Double getCriticalDeltaValue() {
		return criticalDeltaValue;
	}
	public void setCriticalDeltaValue(Double criticalDeltaValue) {
		this.criticalDeltaValue = criticalDeltaValue;
	}
	
	public Double getSecondaryMajorValue() {
		return secondaryMajorValue;
	}
	public void setSecondaryMajorValue(Double secondaryMajorValue) {
		this.secondaryMajorValue = secondaryMajorValue;
	}
	public Double getSecondaryCriticalValue() {
		return secondaryCriticalValue;
	}
	public void setSecondaryCriticalValue(Double secondaryCriticalValue) {
		this.secondaryCriticalValue = secondaryCriticalValue;
	}
	public Double getSecondaryMajorDeltaValue() {
		return secondaryMajorDeltaValue;
	}
	public void setSecondaryMajorDeltaValue(Double secondaryMajorDeltaValue) {
		this.secondaryMajorDeltaValue = secondaryMajorDeltaValue;
	}
	public Double getSecondaryCriticalDeltaValue() {
		return secondaryCriticalDeltaValue;
	}
	public void setSecondaryCriticalDeltaValue(Double secondaryCriticalDeltaValue) {
		this.secondaryCriticalDeltaValue = secondaryCriticalDeltaValue;
	}
	public String getTypeCheck() {
		return typeCheck;
	}
	public void setTypeCheck(String typeCheck) {
		this.typeCheck = typeCheck;
	}
	
	

	
}
