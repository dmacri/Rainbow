package it.icarcnr.integration.dao.generated;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * AbstractType entity provides the base persistence definition of the Type
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractType implements java.io.Serializable {

	// Fields

	private Integer id;
	private Network network;
	private String name;
	private String description;
	private Set<Node> nodes = new HashSet<Node>(0);

	// Constructors

	/** default constructor */
	public AbstractType() {
	}

	/** minimal constructor */
	public AbstractType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/** full constructor */
	public AbstractType(Network network, String name, String description,
			Set<Node> nodes) {
		this.network = network;
		this.name = name;
		this.description = description;
		this.nodes = nodes;
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
	@JoinColumn(name = "ID_Root_Network")
	public Network getNetwork() {
		return this.network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	@Column(name = "name", unique = true, nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false, length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "type")
	public Set<Node> getNodes() {
		return this.nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

}