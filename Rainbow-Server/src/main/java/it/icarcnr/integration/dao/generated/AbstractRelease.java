package it.icarcnr.integration.dao.generated;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractRelease entity provides the base persistence definition of the
 * Release entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractRelease implements java.io.Serializable {

	// Fields

	private Integer id;
	private String version;

	// Constructors

	/** default constructor */
	public AbstractRelease() {
	}

	/** full constructor */
	public AbstractRelease(String version) {
		this.version = version;
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

	@Column(name = "version", nullable = false, length = 45)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}