package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractMwSensoreLastUpdates entity provides the base persistence definition
 * of the MwSensoreLastUpdates entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractMwSensoreLastUpdates implements
		java.io.Serializable {

	// Fields

	private Integer sensoreId;
	private Timestamp lastTimestamp;
	private Double lastValue;

	// Constructors

	/** default constructor */
	public AbstractMwSensoreLastUpdates() {
	}

	/** minimal constructor */
	public AbstractMwSensoreLastUpdates(Integer sensoreId, Double lastValue) {
		this.sensoreId = sensoreId;
		this.lastValue = lastValue;
	}

	/** full constructor */
	public AbstractMwSensoreLastUpdates(Integer sensoreId,
			Timestamp lastTimestamp, Double lastValue) {
		this.sensoreId = sensoreId;
		this.lastTimestamp = lastTimestamp;
		this.lastValue = lastValue;
	}

	// Property accessors
	@Id
	@Column(name = "sensore_id", unique = true, nullable = false)
	public Integer getSensoreId() {
		return this.sensoreId;
	}

	public void setSensoreId(Integer sensoreId) {
		this.sensoreId = sensoreId;
	}

	@Column(name = "last_timestamp", length = 19)
	public Timestamp getLastTimestamp() {
		return this.lastTimestamp;
	}

	public void setLastTimestamp(Timestamp lastTimestamp) {
		this.lastTimestamp = lastTimestamp;
	}

	@Column(name = "last_value", nullable = false, precision = 22, scale = 0)
	public Double getLastValue() {
		return this.lastValue;
	}

	public void setLastValue(Double lastValue) {
		this.lastValue = lastValue;
	}

}