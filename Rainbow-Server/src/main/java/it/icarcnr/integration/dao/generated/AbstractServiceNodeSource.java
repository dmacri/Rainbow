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
 * AbstractServiceNodeSource entity provides the base persistence definition of
 * the ServiceNodeSource entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractServiceNodeSource implements java.io.Serializable {

	// Fields

	private Integer id;
	private Service service;
	private Node node;

	// Constructors

	/** default constructor */
	public AbstractServiceNodeSource() {
	}

	/** full constructor */
	public AbstractServiceNodeSource(Service service, Node node) {
		this.service = service;
		this.node = node;
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
	@JoinColumn(name = "ID_Service", nullable = false)
	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Node", nullable = false)
	public Node getNode() {
		return this.node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

}