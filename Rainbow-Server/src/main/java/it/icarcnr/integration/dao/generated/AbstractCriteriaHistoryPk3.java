package it.icarcnr.integration.dao.generated;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractCriteriaHistoryPk3 entity provides the base persistence definition of
 * the CriteriaHistoryPk3 entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractCriteriaHistoryPk3 implements
		java.io.Serializable {

	// Fields

	private CriteriaHistoryPk3Id id;
	private String status;
	private Double value;
	private Double valueCheckMajor;
	private Double valueCheckCritical;
	private Double valueCheckSecondaryMajor;
	private Double valueCheckSecondaryCritical;

	// Constructors

	/** default constructor */
	public AbstractCriteriaHistoryPk3() {
	}

	/** minimal constructor */
	public AbstractCriteriaHistoryPk3(CriteriaHistoryPk3Id id, String status,
			Double value) {
		this.id = id;
		this.status = status;
		this.value = value;
	}

	/** full constructor */
	public AbstractCriteriaHistoryPk3(CriteriaHistoryPk3Id id, String status,
			Double value, Double valueCheckMajor, Double valueCheckCritical,
			Double valueCheckSecondaryMajor, Double valueCheckSecondaryCritical) {
		this.id = id;
		this.status = status;
		this.value = value;
		this.valueCheckMajor = valueCheckMajor;
		this.valueCheckCritical = valueCheckCritical;
		this.valueCheckSecondaryMajor = valueCheckSecondaryMajor;
		this.valueCheckSecondaryCritical = valueCheckSecondaryCritical;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "idServiceOperation", column = @Column(name = "ID_ServiceOperation", nullable = false)),
			@AttributeOverride(name = "date", column = @Column(name = "date", nullable = false, length = 19)) })
	public CriteriaHistoryPk3Id getId() {
		return this.id;
	}

	public void setId(CriteriaHistoryPk3Id id) {
		this.id = id;
	}

	@Column(name = "status", nullable = false, length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "value", nullable = false, precision = 22, scale = 0)
	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Column(name = "value_check_major", precision = 22, scale = 0)
	public Double getValueCheckMajor() {
		return this.valueCheckMajor;
	}

	public void setValueCheckMajor(Double valueCheckMajor) {
		this.valueCheckMajor = valueCheckMajor;
	}

	@Column(name = "value_check_critical", precision = 22, scale = 0)
	public Double getValueCheckCritical() {
		return this.valueCheckCritical;
	}

	public void setValueCheckCritical(Double valueCheckCritical) {
		this.valueCheckCritical = valueCheckCritical;
	}

	@Column(name = "value_check_secondary_major", precision = 22, scale = 0)
	public Double getValueCheckSecondaryMajor() {
		return this.valueCheckSecondaryMajor;
	}

	public void setValueCheckSecondaryMajor(Double valueCheckSecondaryMajor) {
		this.valueCheckSecondaryMajor = valueCheckSecondaryMajor;
	}

	@Column(name = "value_check_secondary_critical", precision = 22, scale = 0)
	public Double getValueCheckSecondaryCritical() {
		return this.valueCheckSecondaryCritical;
	}

	public void setValueCheckSecondaryCritical(
			Double valueCheckSecondaryCritical) {
		this.valueCheckSecondaryCritical = valueCheckSecondaryCritical;
	}

}