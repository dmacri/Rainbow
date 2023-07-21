package it.icarcnr.integration.dao.generated;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractValori entity provides the base persistence definition of the Valori
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractValori implements java.io.Serializable {

	// Fields

	private ValoriId id;
	private Double value;

	// Constructors

	/** default constructor */
	public AbstractValori() {
	}

	/** full constructor */
	public AbstractValori(ValoriId id, Double value) {
		this.id = id;
		this.value = value;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "timestamp", column = @Column(name = "timestamp", nullable = false, length = 19)),
			@AttributeOverride(name = "idSensore", column = @Column(name = "id_sensore", nullable = false)) })
	public ValoriId getId() {
		return this.id;
	}

	public void setId(ValoriId id) {
		this.id = id;
	}

	@Column(name = "value", nullable = false, precision = 22, scale = 0)
	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}