package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ValoriId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ValoriId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp timestamp;
	private Integer idSensore;

	// Constructors

	/** default constructor */
	public ValoriId() {
	}

	/** full constructor */
	public ValoriId(Integer id, Timestamp timestamp, Integer idSensore) {
		this.id = id;
		this.timestamp = timestamp;
		this.idSensore = idSensore;
	}

	// Property accessors

	@Column(name = "id", nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "timestamp", nullable = false, length = 19)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "id_sensore", nullable = false)
	public Integer getIdSensore() {
		return this.idSensore;
	}

	public void setIdSensore(Integer idSensore) {
		this.idSensore = idSensore;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ValoriId))
			return false;
		ValoriId castOther = (ValoriId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTimestamp() == castOther.getTimestamp()) || (this
						.getTimestamp() != null
						&& castOther.getTimestamp() != null && this
						.getTimestamp().equals(castOther.getTimestamp())))
				&& ((this.getIdSensore() == castOther.getIdSensore()) || (this
						.getIdSensore() != null
						&& castOther.getIdSensore() != null && this
						.getIdSensore().equals(castOther.getIdSensore())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTimestamp() == null ? 0 : this.getTimestamp().hashCode());
		result = 37 * result
				+ (getIdSensore() == null ? 0 : this.getIdSensore().hashCode());
		return result;
	}

}