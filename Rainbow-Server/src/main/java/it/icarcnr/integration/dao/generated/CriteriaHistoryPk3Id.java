package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CriteriaHistoryPk3Id entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CriteriaHistoryPk3Id implements java.io.Serializable {

	// Fields

	private Integer idServiceOperation;
	private Timestamp date;

	// Constructors

	/** default constructor */
	public CriteriaHistoryPk3Id() {
	}

	/** full constructor */
	public CriteriaHistoryPk3Id(Integer idServiceOperation, Timestamp date) {
		this.idServiceOperation = idServiceOperation;
		this.date = date;
	}

	// Property accessors

	@Column(name = "ID_ServiceOperation", nullable = false)
	public Integer getIdServiceOperation() {
		return this.idServiceOperation;
	}

	public void setIdServiceOperation(Integer idServiceOperation) {
		this.idServiceOperation = idServiceOperation;
	}

	@Column(name = "date", nullable = false, length = 19)
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CriteriaHistoryPk3Id))
			return false;
		CriteriaHistoryPk3Id castOther = (CriteriaHistoryPk3Id) other;

		return ((this.getIdServiceOperation() == castOther
				.getIdServiceOperation()) || (this.getIdServiceOperation() != null
				&& castOther.getIdServiceOperation() != null && this
				.getIdServiceOperation().equals(
						castOther.getIdServiceOperation())))
				&& ((this.getDate() == castOther.getDate()) || (this.getDate() != null
						&& castOther.getDate() != null && this.getDate()
						.equals(castOther.getDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdServiceOperation() == null ? 0 : this
						.getIdServiceOperation().hashCode());
		result = 37 * result
				+ (getDate() == null ? 0 : this.getDate().hashCode());
		return result;
	}

}