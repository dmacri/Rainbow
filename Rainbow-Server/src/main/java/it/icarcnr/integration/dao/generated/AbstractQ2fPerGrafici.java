package it.icarcnr.integration.dao.generated;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractQ2fPerGrafici entity provides the base persistence definition of the
 * Q2fPerGrafici entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractQ2fPerGrafici implements java.io.Serializable {

	// Fields

	private Q2fPerGraficiId id;

	// Constructors

	/** default constructor */
	public AbstractQ2fPerGrafici() {
	}

	/** full constructor */
	public AbstractQ2fPerGrafici(Q2fPerGraficiId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "sensoreId", column = @Column(name = "sensore_id", nullable = false)),
			@AttributeOverride(name = "valoriTimestamp", column = @Column(name = "valori_timestamp", nullable = false, length = 19)),
			@AttributeOverride(name = "valoriValue", column = @Column(name = "valori_value", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "valoriId", column = @Column(name = "valori_id", nullable = false)),
			@AttributeOverride(name = "sensoreDescription", column = @Column(name = "sensore_description", nullable = false, length = 45)),
			@AttributeOverride(name = "positionLat", column = @Column(name = "position_lat", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "positionLng", column = @Column(name = "position_lng", nullable = false, precision = 22, scale = 0)) })
	public Q2fPerGraficiId getId() {
		return this.id;
	}

	public void setId(Q2fPerGraficiId id) {
		this.id = id;
	}

}