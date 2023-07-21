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
 * AbstractCalculationParameter entity provides the base persistence definition
 * of the CalculationParameter entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractCalculationParameter implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private ServiceOperation serviceOperation;
	private String name;
	private String value;
	private String type;

	// Constructors

	/** default constructor */
	public AbstractCalculationParameter() {
	}

	/** full constructor */
	public AbstractCalculationParameter(ServiceOperation serviceOperation,
			String name, String value, String type) {
		this.serviceOperation = serviceOperation;
		this.name = name;
		this.value = value;
		this.type = type;
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
	@JoinColumn(name = "ID_ServiceOperation", nullable = false)
	public ServiceOperation getServiceOperation() {
		return this.serviceOperation;
	}

	public void setServiceOperation(ServiceOperation serviceOperation) {
		this.serviceOperation = serviceOperation;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value", nullable = false)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "type", nullable = false)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}