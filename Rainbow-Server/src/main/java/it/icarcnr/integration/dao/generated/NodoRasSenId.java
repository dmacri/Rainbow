package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * NodoRasSenId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class NodoRasSenId implements java.io.Serializable {

	// Fields

	private Integer idR;
	private Integer idS;
	private Timestamp timestamp;

	// Constructors

	/** default constructor */
	public NodoRasSenId() {
	}

	/** full constructor */
	public NodoRasSenId(Integer idR, Integer idS, Timestamp timestamp) {
		this.idR = idR;
		this.idS = idS;
		this.timestamp = timestamp;
	}

	// Property accessors

	@Column(name = "id_r", nullable = false)
	public Integer getIdR() {
		return this.idR;
	}

	public void setIdR(Integer idR) {
		this.idR = idR;
	}

	@Column(name = "id_s", nullable = false)
	public Integer getIdS() {
		return this.idS;
	}

	public void setIdS(Integer idS) {
		this.idS = idS;
	}

	@Column(name = "timestamp", nullable = false, length = 19)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NodoRasSenId))
			return false;
		NodoRasSenId castOther = (NodoRasSenId) other;

		return ((this.getIdR() == castOther.getIdR()) || (this.getIdR() != null
				&& castOther.getIdR() != null && this.getIdR().equals(
				castOther.getIdR())))
				&& ((this.getIdS() == castOther.getIdS()) || (this.getIdS() != null
						&& castOther.getIdS() != null && this.getIdS().equals(
						castOther.getIdS())))
				&& ((this.getTimestamp() == castOther.getTimestamp()) || (this
						.getTimestamp() != null
						&& castOther.getTimestamp() != null && this
						.getTimestamp().equals(castOther.getTimestamp())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdR() == null ? 0 : this.getIdR().hashCode());
		result = 37 * result
				+ (getIdS() == null ? 0 : this.getIdS().hashCode());
		result = 37 * result
				+ (getTimestamp() == null ? 0 : this.getTimestamp().hashCode());
		return result;
	}

}