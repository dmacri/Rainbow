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
 * AbstractUtilityGroup entity provides the base persistence definition of the
 * UtilityGroup entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUtilityGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer maxProcesses;
	private String name;
	private Set<Utility> utilities = new HashSet<Utility>(0);

	// Constructors

	/** default constructor */
	public AbstractUtilityGroup() {
	}

	/** minimal constructor */
	public AbstractUtilityGroup(Integer maxProcesses, String name) {
		this.maxProcesses = maxProcesses;
		this.name = name;
	}

	/** full constructor */
	public AbstractUtilityGroup(Integer maxProcesses, String name,
			Set<Utility> utilities) {
		this.maxProcesses = maxProcesses;
		this.name = name;
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

	@Column(name = "max_processes", nullable = false)
	public Integer getMaxProcesses() {
		return this.maxProcesses;
	}

	public void setMaxProcesses(Integer maxProcesses) {
		this.maxProcesses = maxProcesses;
	}

	@Column(name = "name", unique = true, nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utilityGroup")
	public Set<Utility> getUtilities() {
		return this.utilities;
	}

	public void setUtilities(Set<Utility> utilities) {
		this.utilities = utilities;
	}

}