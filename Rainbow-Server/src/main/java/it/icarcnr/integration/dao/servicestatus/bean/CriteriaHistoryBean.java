package it.icarcnr.integration.dao.servicestatus.bean;

import java.util.Date;

public class CriteriaHistoryBean implements java.io.Serializable{
	
	private Integer idServiceOperation;
	private Date date;
	private String status;
	private Double value;
	private Double valueCheckMajor;
	private Double valueCheckCritical;
	private Double valueCheckSecondaryMajor;
	private Double valueCheckSecondaryCritical;
	
	
	/**
	 * @param id
	 * @param date
	 * @param value
	 * @param valueCheckMajor
	 * @param valueCheckCritical
	 */
	public CriteriaHistoryBean(Integer idServiceOperation, Date date, Double value,
			Double valueCheckMajor, Double valueCheckCritical, Double valueCheckSecondaryMajor, Double valueCheckSecondaryCritical) {
		super();
		this.idServiceOperation = idServiceOperation;
		this.date = date;
		this.value = value;
		this.valueCheckMajor = valueCheckMajor;
		this.valueCheckCritical = valueCheckCritical;
		this.valueCheckSecondaryMajor = valueCheckSecondaryMajor;
		this.valueCheckSecondaryCritical = valueCheckSecondaryCritical;
	}



	/**
	 * @param idServiceOperation
	 * @param date
	 * @param status
	 * @param value
	 * @param valueCheckMajor
	 * @param valueCheckCritical
	 * @param valueCheckSecondaryMajor
	 * @param valueCheckSecondaryCritical
	 */
	public CriteriaHistoryBean(Integer idServiceOperation, Date date,
			String status, Double value, Double valueCheckMajor,
			Double valueCheckCritical, Double valueCheckSecondaryMajor,
			Double valueCheckSecondaryCritical) {
		super();
		this.idServiceOperation = idServiceOperation;
		this.date = date;
		this.status = status;
		this.value = value;
		this.valueCheckMajor = valueCheckMajor;
		this.valueCheckCritical = valueCheckCritical;
		this.valueCheckSecondaryMajor = valueCheckSecondaryMajor;
		this.valueCheckSecondaryCritical = valueCheckSecondaryCritical;
	}



	public Integer getIdServiceOperation() {
		return idServiceOperation;
	}



	public void setIdServiceOperation(Integer idServiceOperation) {
		this.idServiceOperation = idServiceOperation;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Double getValue() {
		return value;
	}



	public void setValue(Double value) {
		this.value = value;
	}



	public Double getValueCheckMajor() {
		return valueCheckMajor;
	}



	public void setValueCheckMajor(Double valueCheckMajor) {
		this.valueCheckMajor = valueCheckMajor;
	}



	public Double getValueCheckCritical() {
		return valueCheckCritical;
	}



	public void setValueCheckCritical(Double valueCheckCritical) {
		this.valueCheckCritical = valueCheckCritical;
	}



	public Double getValueCheckSecondaryMajor() {
		return valueCheckSecondaryMajor;
	}



	public void setValueCheckSecondaryMajor(Double valueCheckSecondaryMajor) {
		this.valueCheckSecondaryMajor = valueCheckSecondaryMajor;
	}



	public Double getValueCheckSecondaryCritical() {
		return valueCheckSecondaryCritical;
	}



	public void setValueCheckSecondaryCritical(Double valueCheckSecondaryCritical) {
		this.valueCheckSecondaryCritical = valueCheckSecondaryCritical;
	}

}
