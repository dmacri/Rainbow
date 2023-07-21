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
 * AbstractOperation entity provides the base persistence definition of the
 * Operation entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractOperation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<ServiceOperation> serviceOperations = new HashSet<ServiceOperation>(
			0);

	// Constructors

	/** default constructor */
	public AbstractOperation() {
	}

	/** minimal constructor */
	public AbstractOperation(String name) {
		this.name = name;
	}

	/** full constructor */
	public AbstractOperation(String name,
			Set<ServiceOperation> serviceOperations) {
		this.name = name;
		this.serviceOperations = serviceOperations;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "operation")
	public Set<ServiceOperation> getServiceOperations() {
		return this.serviceOperations;
	}

	public void setServiceOperations(Set<ServiceOperation> serviceOperations) {
		this.serviceOperations = serviceOperations;
	}

}