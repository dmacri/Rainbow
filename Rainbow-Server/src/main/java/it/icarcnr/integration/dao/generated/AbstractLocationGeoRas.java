package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractLocationGeoRas entity provides the base persistence definition of the
 * LocationGeoRas entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractLocationGeoRas implements java.io.Serializable {

	// Fields

	private Integer id;
	private NodeRas nodeRas;
	private Position position;
	private Timestamp timestamp;

	// Constructors

	/** default constructor */
	public AbstractLocationGeoRas() {
	}

	/** full constructor */
	public AbstractLocationGeoRas(NodeRas nodeRas, Position position,
			Timestamp timestamp) {
		this.nodeRas = nodeRas;
		this.position = position;
		this.timestamp = timestamp;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_node_ras", nullable = false)
	public NodeRas getNodeRas() {
		return this.nodeRas;
	}

	public void setNodeRas(NodeRas nodeRas) {
		this.nodeRas = nodeRas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_position", nullable = false)
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Column(name = "timestamp", nullable = false, length = 19)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}