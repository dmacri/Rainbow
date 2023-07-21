package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Q2fPerGraficiId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Q2fPerGraficiId implements java.io.Serializable {

	// Fields

	private Integer sensoreId;
	private Timestamp valoriTimestamp;
	private Double valoriValue;
	private Integer valoriId;
	private String sensoreDescription;
	private Double positionLat;
	private Double positionLng;

	// Constructors

	/** default constructor */
	public Q2fPerGraficiId() {
	}

	/** full constructor */
	public Q2fPerGraficiId(Integer sensoreId, Timestamp valoriTimestamp,
			Double valoriValue, Integer valoriId, String sensoreDescription,
			Double positionLat, Double positionLng) {
		this.sensoreId = sensoreId;
		this.valoriTimestamp = valoriTimestamp;
		this.valoriValue = valoriValue;
		this.valoriId = valoriId;
		this.sensoreDescription = sensoreDescription;
		this.positionLat = positionLat;
		this.positionLng = positionLng;
	}

	// Property accessors

	@Column(name = "sensore_id", nullable = false)
	public Integer getSensoreId() {
		return this.sensoreId;
	}

	public void setSensoreId(Integer sensoreId) {
		this.sensoreId = sensoreId;
	}

	@Column(name = "valori_timestamp", nullable = false, length = 19)
	public Timestamp getValoriTimestamp() {
		return this.valoriTimestamp;
	}

	public void setValoriTimestamp(Timestamp valoriTimestamp) {
		this.valoriTimestamp = valoriTimestamp;
	}

	@Column(name = "valori_value", nullable = false, precision = 22, scale = 0)
	public Double getValoriValue() {
		return this.valoriValue;
	}

	public void setValoriValue(Double valoriValue) {
		this.valoriValue = valoriValue;
	}

	@Column(name = "valori_id", nullable = false)
	public Integer getValoriId() {
		return this.valoriId;
	}

	public void setValoriId(Integer valoriId) {
		this.valoriId = valoriId;
	}

	@Column(name = "sensore_description", nullable = false, length = 45)
	public String getSensoreDescription() {
		return this.sensoreDescription;
	}

	public void setSensoreDescription(String sensoreDescription) {
		this.sensoreDescription = sensoreDescription;
	}

	@Column(name = "position_lat", nullable = false, precision = 22, scale = 0)
	public Double getPositionLat() {
		return this.positionLat;
	}

	public void setPositionLat(Double positionLat) {
		this.positionLat = positionLat;
	}

	@Column(name = "position_lng", nullable = false, precision = 22, scale = 0)
	public Double getPositionLng() {
		return this.positionLng;
	}

	public void setPositionLng(Double positionLng) {
		this.positionLng = positionLng;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Q2fPerGraficiId))
			return false;
		Q2fPerGraficiId castOther = (Q2fPerGraficiId) other;

		return ((this.getSensoreId() == castOther.getSensoreId()) || (this
				.getSensoreId() != null
				&& castOther.getSensoreId() != null && this.getSensoreId()
				.equals(castOther.getSensoreId())))
				&& ((this.getValoriTimestamp() == castOther
						.getValoriTimestamp()) || (this.getValoriTimestamp() != null
						&& castOther.getValoriTimestamp() != null && this
						.getValoriTimestamp().equals(
								castOther.getValoriTimestamp())))
				&& ((this.getValoriValue() == castOther.getValoriValue()) || (this
						.getValoriValue() != null
						&& castOther.getValoriValue() != null && this
						.getValoriValue().equals(castOther.getValoriValue())))
				&& ((this.getValoriId() == castOther.getValoriId()) || (this
						.getValoriId() != null
						&& castOther.getValoriId() != null && this
						.getValoriId().equals(castOther.getValoriId())))
				&& ((this.getSensoreDescription() == castOther
						.getSensoreDescription()) || (this
						.getSensoreDescription() != null
						&& castOther.getSensoreDescription() != null && this
						.getSensoreDescription().equals(
								castOther.getSensoreDescription())))
				&& ((this.getPositionLat() == castOther.getPositionLat()) || (this
						.getPositionLat() != null
						&& castOther.getPositionLat() != null && this
						.getPositionLat().equals(castOther.getPositionLat())))
				&& ((this.getPositionLng() == castOther.getPositionLng()) || (this
						.getPositionLng() != null
						&& castOther.getPositionLng() != null && this
						.getPositionLng().equals(castOther.getPositionLng())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSensoreId() == null ? 0 : this.getSensoreId().hashCode());
		result = 37
				* result
				+ (getValoriTimestamp() == null ? 0 : this.getValoriTimestamp()
						.hashCode());
		result = 37
				* result
				+ (getValoriValue() == null ? 0 : this.getValoriValue()
						.hashCode());
		result = 37 * result
				+ (getValoriId() == null ? 0 : this.getValoriId().hashCode());
		result = 37
				* result
				+ (getSensoreDescription() == null ? 0 : this
						.getSensoreDescription().hashCode());
		result = 37
				* result
				+ (getPositionLat() == null ? 0 : this.getPositionLat()
						.hashCode());
		result = 37
				* result
				+ (getPositionLng() == null ? 0 : this.getPositionLng()
						.hashCode());
		return result;
	}

}