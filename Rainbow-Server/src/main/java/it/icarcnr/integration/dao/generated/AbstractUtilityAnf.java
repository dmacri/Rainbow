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
 * AbstractUtilityAnf entity provides the base persistence definition of the
 * UtilityAnf entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUtilityAnf implements java.io.Serializable {

	// Fields

	private Integer id;
	private Utility utility;
	private ActionNetworkFunction actionNetworkFunction;
	private Integer maxProcesses;

	// Constructors

	/** default constructor */
	public AbstractUtilityAnf() {
	}

	/** full constructor */
	public AbstractUtilityAnf(Utility utility,
			ActionNetworkFunction actionNetworkFunction, Integer maxProcesses) {
		this.utility = utility;
		this.actionNetworkFunction = actionNetworkFunction;
		this.maxProcesses = maxProcesses;
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
	@JoinColumn(name = "ID_Utility", nullable = false)
	public Utility getUtility() {
		return this.utility;
	}

	public void setUtility(Utility utility) {
		this.utility = utility;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ANF", nullable = false)
	public ActionNetworkFunction getActionNetworkFunction() {
		return this.actionNetworkFunction;
	}

	public void setActionNetworkFunction(
			ActionNetworkFunction actionNetworkFunction) {
		this.actionNetworkFunction = actionNetworkFunction;
	}

	@Column(name = "max_processes", nullable = false)
	public Integer getMaxProcesses() {
		return this.maxProcesses;
	}

	public void setMaxProcesses(Integer maxProcesses) {
		this.maxProcesses = maxProcesses;
	}

}