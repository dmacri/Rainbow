package it.icarcnr.integration.dao.generated;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractQ1fPerMappa entity provides the base persistence definition of the
 * Q1fPerMappa entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractQ1fPerMappa implements java.io.Serializable {

	// Fields

	private Q1fPerMappaId id;

	// Constructors

	/** default constructor */
	public AbstractQ1fPerMappa() {
	}

	/** full constructor */
	public AbstractQ1fPerMappa(Q1fPerMappaId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "sensoreId", column = @Column(name = "sensore_id", nullable = false)),
			@AttributeOverride(name = "nodeSenId", column = @Column(name = "node_sen_id", nullable = false)),
			@AttributeOverride(name = "nodeSenDescription", column = @Column(name = "node_sen_description", nullable = false, length = 45)),
			@AttributeOverride(name = "valoriTimestamp", column = @Column(name = "valori_timestamp", length = 19)),
			@AttributeOverride(name = "sensoreDescription", column = @Column(name = "sensore_description", nullable = false, length = 45)),
			@AttributeOverride(name = "valoriValue", column = @Column(name = "valori_value", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "tipoSoglieLivello", column = @Column(name = "tipo_soglie_livello", nullable = false)),
			@AttributeOverride(name = "tipoSoglieLowerBound", column = @Column(name = "tipo_soglie_lower_bound", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "tipoSoglieUpperBound", column = @Column(name = "tipo_soglie_upper_bound", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "positionLat", column = @Column(name = "position_lat", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "positionLng", column = @Column(name = "position_lng", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "path", column = @Column(name = "path", length = 100)) })
	public Q1fPerMappaId getId() {
		return this.id;
	}

	public void setId(Q1fPerMappaId id) {
		this.id = id;
	}

}