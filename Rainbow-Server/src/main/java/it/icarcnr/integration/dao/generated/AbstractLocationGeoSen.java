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
 * AbstractLocationGeoSen entity provides the base persistence definition of the
 * LocationGeoSen entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractLocationGeoSen implements java.io.Serializable {

	// Fields

	private Integer id;
	private Position position;
	private NodeSen nodeSen;
	private Timestamp timestamp;

	// Constructors

	/** default constructor */
	public AbstractLocationGeoSen() {
	}

	/** full constructor */
	public AbstractLocationGeoSen(Position position, NodeSen nodeSen,
			Timestamp timestamp) {
		this.position = position;
		this.nodeSen = nodeSen;
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
	@JoinColumn(name = "id_position", nullable = false)
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_node_sen", nullable = false)
	public NodeSen getNodeSen() {
		return this.nodeSen;
	}

	public void setNodeSen(NodeSen nodeSen) {
		this.nodeSen = nodeSen;
	}

	@Column(name = "timestamp", nullable = false, length = 19)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}