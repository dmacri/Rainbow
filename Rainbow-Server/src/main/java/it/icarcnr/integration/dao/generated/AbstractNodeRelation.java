package it.icarcnr.integration.dao.generated;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractNodeRelation entity provides the base persistence definition of the
 * NodeRelation entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractNodeRelation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Node nodeByIdChild;
	private Node nodeByIdParent;

	// Constructors

	/** default constructor */
	public AbstractNodeRelation() {
	}

	/** full constructor */
	public AbstractNodeRelation(Node nodeByIdChild, Node nodeByIdParent) {
		this.nodeByIdChild = nodeByIdChild;
		this.nodeByIdParent = nodeByIdParent;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Child", nullable = false)
	public Node getNodeByIdChild() {
		return this.nodeByIdChild;
	}

	public void setNodeByIdChild(Node nodeByIdChild) {
		this.nodeByIdChild = nodeByIdChild;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Parent", nullable = false)
	public Node getNodeByIdParent() {
		return this.nodeByIdParent;
	}

	public void setNodeByIdParent(Node nodeByIdParent) {
		this.nodeByIdParent = nodeByIdParent;
	}

}