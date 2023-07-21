package it.icarcnr.integration.dao.generated;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * AbstractNetwork entity provides the base persistence definition of the
 * Network entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractNetwork implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private Set<Type> types = new HashSet<Type>(0);
	private Set<NetworkFunction> networkFunctions = new HashSet<NetworkFunction>(
			0);
	private Set<Node> nodes = new HashSet<Node>(0);
	private Set<Utility> utilities = new HashSet<Utility>(0);

	// Constructors

	/** default constructor */
	public AbstractNetwork() {
	}

	/** minimal constructor */
	public AbstractNetwork(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/** full constructor */
	public AbstractNetwork(String name, String description, Set<Type> types,
			Set<NetworkFunction> networkFunctions, Set<Node> nodes,
			Set<Utility> utilities) {
		this.name = name;
		this.description = description;
		this.types = types;
		this.networkFunctions = networkFunctions;
		this.nodes = nodes;
		this.utilities = utilities;
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

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "network")
	public Set<Type> getTypes() {
		return this.types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "network")
	public Set<NetworkFunction> getNetworkFunctions() {
		return this.networkFunctions;
	}

	public void setNetworkFunctions(Set<NetworkFunction> networkFunctions) {
		this.networkFunctions = networkFunctions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "network")
	public Set<Node> getNodes() {
		return this.nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "network")
	public Set<Utility> getUtilities() {
		return this.utilities;
	}

	public void setUtilities(Set<Utility> utilities) {
		this.utilities = utilities;
	}

}