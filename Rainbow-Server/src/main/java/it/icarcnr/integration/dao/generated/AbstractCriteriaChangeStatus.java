package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractCriteriaChangeStatus entity provides the base persistence definition
 * of the CriteriaChangeStatus entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractCriteriaChangeStatus implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private ServiceOperation serviceOperation;
	private String statusFrom;
	private String statusTo;
	private Timestamp date;
	private Double valueAfter;
	private Timestamp dateDetection;

	// Constructors

	/** default constructor */
	public AbstractCriteriaChangeStatus() {
	}

	/** minimal constructor */
	public AbstractCriteriaChangeStatus(ServiceOperation serviceOperation,
			String statusFrom, String statusTo, Double valueAfter) {
		this.serviceOperation = serviceOperation;
		this.statusFrom = statusFrom;
		this.statusTo = statusTo;
		this.valueAfter = valueAfter;
	}

	/** full constructor */
	public AbstractCriteriaChangeStatus(ServiceOperation serviceOperation,
			String statusFrom, String statusTo, Timestamp date,
			Double valueAfter, Timestamp dateDetection) {
		this.serviceOperation = serviceOperation;
		this.statusFrom = statusFrom;
		this.statusTo = statusTo;
		this.date = date;
		this.valueAfter = valueAfter;
		this.dateDetection = dateDetection;
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

	@Column(name = "status_from", nullable = false, length = 10)
	public String getStatusFrom() {
		return this.statusFrom;
	}

	public void setStatusFrom(String statusFrom) {
		this.statusFrom = statusFrom;
	}

	@Column(name = "status_to", nullable = false, length = 10)
	public String getStatusTo() {
		return this.statusTo;
	}

	public void setStatusTo(String statusTo) {
		this.statusTo = statusTo;
	}

	@Column(name = "date", length = 19)
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Column(name = "value_after", nullable = false, precision = 22, scale = 0)
	public Double getValueAfter() {
		return this.valueAfter;
	}

	public void setValueAfter(Double valueAfter) {
		this.valueAfter = valueAfter;
	}

	@Column(name = "date_detection", length = 19)
	public Timestamp getDateDetection() {
		return this.dateDetection;
	}

	public void setDateDetection(Timestamp dateDetection) {
		this.dateDetection = dateDetection;
	}

}