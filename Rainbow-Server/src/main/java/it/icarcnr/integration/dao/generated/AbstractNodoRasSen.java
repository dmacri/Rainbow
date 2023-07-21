package it.icarcnr.integration.dao.generated;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractNodoRasSen entity provides the base persistence definition of the
 * NodoRasSen entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractNodoRasSen implements java.io.Serializable {

	// Fields

	private NodoRasSenId id;
	private NodeSen nodeSen;
	private NodeRas nodeRas;

	// Constructors

	/** default constructor */
	public AbstractNodoRasSen() {
	}

	/** full constructor */
	public AbstractNodoRasSen(NodoRasSenId id, NodeSen nodeSen, NodeRas nodeRas) {
		this.id = id;
		this.nodeSen = nodeSen;
		this.nodeRas = nodeRas;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "idR", column = @Column(name = "id_r", nullable = false)),
			@AttributeOverride(name = "idS", column = @Column(name = "id_s", nullable = false)),
			@AttributeOverride(name = "timestamp", column = @Column(name = "timestamp", nullable = false, length = 19)) })
	public NodoRasSenId getId() {
		return this.id;
	}

	public void setId(NodoRasSenId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_s", nullable = false, insertable = false, updatable = false)
	public NodeSen getNodeSen() {
		return this.nodeSen;
	}

	public void setNodeSen(NodeSen nodeSen) {
		this.nodeSen = nodeSen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_r", nullable = false, insertable = false, updatable = false)
	public NodeRas getNodeRas() {
		return this.nodeRas;
	}

	public void setNodeRas(NodeRas nodeRas) {
		this.nodeRas = nodeRas;
	}

}