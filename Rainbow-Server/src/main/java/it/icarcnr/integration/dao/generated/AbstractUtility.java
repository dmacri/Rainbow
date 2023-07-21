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
 * AbstractUtility entity provides the base persistence definition of the
 * Utility entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUtility implements java.io.Serializable {

	// Fields

	private Integer id;
	private UtilityGroup utilityGroup;
	private Network network;
	private String script;
	private String description;
	private Set<UtilityAnf> utilityAnfs = new HashSet<UtilityAnf>(0);
	private Set<Cronjob> cronjobs = new HashSet<Cronjob>(0);

	// Constructors

	/** default constructor */
	public AbstractUtility() {
	}

	/** minimal constructor */
	public AbstractUtility(UtilityGroup utilityGroup, Network network,
			String script, String description) {
		this.utilityGroup = utilityGroup;
		this.network = network;
		this.script = script;
		this.description = description;
	}

	/** full constructor */
	public AbstractUtility(UtilityGroup utilityGroup, Network network,
			String script, String description, Set<UtilityAnf> utilityAnfs,
			Set<Cronjob> cronjobs) {
		this.utilityGroup = utilityGroup;
		this.network = network;
		this.script = script;
		this.description = description;
		this.utilityAnfs = utilityAnfs;
		this.cronjobs = cronjobs;
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
	@JoinColumn(name = "ID_UtilityGroup", nullable = false)
	public UtilityGroup getUtilityGroup() {
		return this.utilityGroup;
	}

	public void setUtilityGroup(UtilityGroup utilityGroup) {
		this.utilityGroup = utilityGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Network", nullable = false)
	public Network getNetwork() {
		return this.network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	@Column(name = "script", nullable = false)
	public String getScript() {
		return this.script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utility")
	public Set<UtilityAnf> getUtilityAnfs() {
		return this.utilityAnfs;
	}

	public void setUtilityAnfs(Set<UtilityAnf> utilityAnfs) {
		this.utilityAnfs = utilityAnfs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utility")
	public Set<Cronjob> getCronjobs() {
		return this.cronjobs;
	}

	public void setCronjobs(Set<Cronjob> cronjobs) {
		this.cronjobs = cronjobs;
	}

}