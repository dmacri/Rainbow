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
 * AbstractActionNetworkFunction entity provides the base persistence definition
 * of the ActionNetworkFunction entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractActionNetworkFunction implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private NetworkFunction networkFunction;
	private Action action;
	private Set<GroupAnf> groupAnfs = new HashSet<GroupAnf>(0);
	private Set<UtilityAnf> utilityAnfs = new HashSet<UtilityAnf>(0);

	// Constructors

	/** default constructor */
	public AbstractActionNetworkFunction() {
	}

	/** minimal constructor */
	public AbstractActionNetworkFunction(NetworkFunction networkFunction,
			Action action) {
		this.networkFunction = networkFunction;
		this.action = action;
	}

	/** full constructor */
	public AbstractActionNetworkFunction(NetworkFunction networkFunction,
			Action action, Set<GroupAnf> groupAnfs, Set<UtilityAnf> utilityAnfs) {
		this.networkFunction = networkFunction;
		this.action = action;
		this.groupAnfs = groupAnfs;
		this.utilityAnfs = utilityAnfs;
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
	@JoinColumn(name = "ID_NF", nullable = false)
	public NetworkFunction getNetworkFunction() {
		return this.networkFunction;
	}

	public void setNetworkFunction(NetworkFunction networkFunction) {
		this.networkFunction = networkFunction;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Action", nullable = false)
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actionNetworkFunction")
	public Set<GroupAnf> getGroupAnfs() {
		return this.groupAnfs;
	}

	public void setGroupAnfs(Set<GroupAnf> groupAnfs) {
		this.groupAnfs = groupAnfs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actionNetworkFunction")
	public Set<UtilityAnf> getUtilityAnfs() {
		return this.utilityAnfs;
	}

	public void setUtilityAnfs(Set<UtilityAnf> utilityAnfs) {
		this.utilityAnfs = utilityAnfs;
	}

}