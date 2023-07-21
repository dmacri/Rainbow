package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Q1fPerMappaId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Q1fPerMappaId implements java.io.Serializable {

	// Fields

	private Integer sensoreId;
	private Integer nodeSenId;
	private String nodeSenDescription;
	private Timestamp valoriTimestamp;
	private String sensoreDescription;
	private Double valoriValue;
	private Integer tipoSoglieLivello;
	private Double tipoSoglieLowerBound;
	private Double tipoSoglieUpperBound;
	private Double positionLat;
	private Double positionLng;
	private String path;

	// Constructors

	/** default constructor */
	public Q1fPerMappaId() {
	}

	/** minimal constructor */
	public Q1fPerMappaId(Integer sensoreId, Integer nodeSenId,
			String nodeSenDescription, String sensoreDescription,
			Double valoriValue, Integer tipoSoglieLivello,
			Double tipoSoglieLowerBound, Double tipoSoglieUpperBound,
			Double positionLat, Double positionLng) {
		this.sensoreId = sensoreId;
		this.nodeSenId = nodeSenId;
		this.nodeSenDescription = nodeSenDescription;
		this.sensoreDescription = sensoreDescription;
		this.valoriValue = valoriValue;
		this.tipoSoglieLivello = tipoSoglieLivello;
		this.tipoSoglieLowerBound = tipoSoglieLowerBound;
		this.tipoSoglieUpperBound = tipoSoglieUpperBound;
		this.positionLat = positionLat;
		this.positionLng = positionLng;
	}

	/** full constructor */
	public Q1fPerMappaId(Integer sensoreId, Integer nodeSenId,
			String nodeSenDescription, Timestamp valoriTimestamp,
			String sensoreDescription, Double valoriValue,
			Integer tipoSoglieLivello, Double tipoSoglieLowerBound,
			Double tipoSoglieUpperBound, Double positionLat,
			Double positionLng, String path) {
		this.sensoreId = sensoreId;
		this.nodeSenId = nodeSenId;
		this.nodeSenDescription = nodeSenDescription;
		this.valoriTimestamp = valoriTimestamp;
		this.sensoreDescription = sensoreDescription;
		this.valoriValue = valoriValue;
		this.tipoSoglieLivello = tipoSoglieLivello;
		this.tipoSoglieLowerBound = tipoSoglieLowerBound;
		this.tipoSoglieUpperBound = tipoSoglieUpperBound;
		this.positionLat = positionLat;
		this.positionLng = positionLng;
		this.path = path;
	}

	// Property accessors

	@Column(name = "sensore_id", nullable = false)
	public Integer getSensoreId() {
		return this.sensoreId;
	}

	public void setSensoreId(Integer sensoreId) {
		this.sensoreId = sensoreId;
	}

	@Column(name = "node_sen_id", nullable = false)
	public Integer getNodeSenId() {
		return this.nodeSenId;
	}

	public void setNodeSenId(Integer nodeSenId) {
		this.nodeSenId = nodeSenId;
	}

	@Column(name = "node_sen_description", nullable = false, length = 45)
	public String getNodeSenDescription() {
		return this.nodeSenDescription;
	}

	public void setNodeSenDescription(String nodeSenDescription) {
		this.nodeSenDescription = nodeSenDescription;
	}

	@Column(name = "valori_timestamp", length = 19)
	public Timestamp getValoriTimestamp() {
		return this.valoriTimestamp;
	}

	public void setValoriTimestamp(Timestamp valoriTimestamp) {
		this.valoriTimestamp = valoriTimestamp;
	}

	@Column(name = "sensore_description", nullable = false, length = 45)
	public String getSensoreDescription() {
		return this.sensoreDescription;
	}

	public void setSensoreDescription(String sensoreDescription) {
		this.sensoreDescription = sensoreDescription;
	}

	@Column(name = "valori_value", nullable = false, precision = 22, scale = 0)
	public Double getValoriValue() {
		return this.valoriValue;
	}

	public void setValoriValue(Double valoriValue) {
		this.valoriValue = valoriValue;
	}

	@Column(name = "tipo_soglie_livello", nullable = false)
	public Integer getTipoSoglieLivello() {
		return this.tipoSoglieLivello;
	}

	public void setTipoSoglieLivello(Integer tipoSoglieLivello) {
		this.tipoSoglieLivello = tipoSoglieLivello;
	}

	@Column(name = "tipo_soglie_lower_bound", nullable = false, precision = 22, scale = 0)
	public Double getTipoSoglieLowerBound() {
		return this.tipoSoglieLowerBound;
	}

	public void setTipoSoglieLowerBound(Double tipoSoglieLowerBound) {
		this.tipoSoglieLowerBound = tipoSoglieLowerBound;
	}

	@Column(name = "tipo_soglie_upper_bound", nullable = false, precision = 22, scale = 0)
	public Double getTipoSoglieUpperBound() {
		return this.tipoSoglieUpperBound;
	}

	public void setTipoSoglieUpperBound(Double tipoSoglieUpperBound) {
		this.tipoSoglieUpperBound = tipoSoglieUpperBound;
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

	@Column(name = "path", length = 100)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Q1fPerMappaId))
			return false;
		Q1fPerMappaId castOther = (Q1fPerMappaId) other;

		return ((this.getSensoreId() == castOther.getSensoreId()) || (this
				.getSensoreId() != null
				&& castOther.getSensoreId() != null && this.getSensoreId()
				.equals(castOther.getSensoreId())))
				&& ((this.getNodeSenId() == castOther.getNodeSenId()) || (this
						.getNodeSenId() != null
						&& castOther.getNodeSenId() != null && this
						.getNodeSenId().equals(castOther.getNodeSenId())))
				&& ((this.getNodeSenDescription() == castOther
						.getNodeSenDescription()) || (this
						.getNodeSenDescription() != null
						&& castOther.getNodeSenDescription() != null && this
						.getNodeSenDescription().equals(
								castOther.getNodeSenDescription())))
				&& ((this.getValoriTimestamp() == castOther
						.getValoriTimestamp()) || (this.getValoriTimestamp() != null
						&& castOther.getValoriTimestamp() != null && this
						.getValoriTimestamp().equals(
								castOther.getValoriTimestamp())))
				&& ((this.getSensoreDescription() == castOther
						.getSensoreDescription()) || (this
						.getSensoreDescription() != null
						&& castOther.getSensoreDescription() != null && this
						.getSensoreDescription().equals(
								castOther.getSensoreDescription())))
				&& ((this.getValoriValue() == castOther.getValoriValue()) || (this
						.getValoriValue() != null
						&& castOther.getValoriValue() != null && this
						.getValoriValue().equals(castOther.getValoriValue())))
				&& ((this.getTipoSoglieLivello() == castOther
						.getTipoSoglieLivello()) || (this
						.getTipoSoglieLivello() != null
						&& castOther.getTipoSoglieLivello() != null && this
						.getTipoSoglieLivello().equals(
								castOther.getTipoSoglieLivello())))
				&& ((this.getTipoSoglieLowerBound() == castOther
						.getTipoSoglieLowerBound()) || (this
						.getTipoSoglieLowerBound() != null
						&& castOther.getTipoSoglieLowerBound() != null && this
						.getTipoSoglieLowerBound().equals(
								castOther.getTipoSoglieLowerBound())))
				&& ((this.getTipoSoglieUpperBound() == castOther
						.getTipoSoglieUpperBound()) || (this
						.getTipoSoglieUpperBound() != null
						&& castOther.getTipoSoglieUpperBound() != null && this
						.getTipoSoglieUpperBound().equals(
								castOther.getTipoSoglieUpperBound())))
				&& ((this.getPositionLat() == castOther.getPositionLat()) || (this
						.getPositionLat() != null
						&& castOther.getPositionLat() != null && this
						.getPositionLat().equals(castOther.getPositionLat())))
				&& ((this.getPositionLng() == castOther.getPositionLng()) || (this
						.getPositionLng() != null
						&& castOther.getPositionLng() != null && this
						.getPositionLng().equals(castOther.getPositionLng())))
				&& ((this.getPath() == castOther.getPath()) || (this.getPath() != null
						&& castOther.getPath() != null && this.getPath()
						.equals(castOther.getPath())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSensoreId() == null ? 0 : this.getSensoreId().hashCode());
		result = 37 * result
				+ (getNodeSenId() == null ? 0 : this.getNodeSenId().hashCode());
		result = 37
				* result
				+ (getNodeSenDescription() == null ? 0 : this
						.getNodeSenDescription().hashCode());
		result = 37
				* result
				+ (getValoriTimestamp() == null ? 0 : this.getValoriTimestamp()
						.hashCode());
		result = 37
				* result
				+ (getSensoreDescription() == null ? 0 : this
						.getSensoreDescription().hashCode());
		result = 37
				* result
				+ (getValoriValue() == null ? 0 : this.getValoriValue()
						.hashCode());
		result = 37
				* result
				+ (getTipoSoglieLivello() == null ? 0 : this
						.getTipoSoglieLivello().hashCode());
		result = 37
				* result
				+ (getTipoSoglieLowerBound() == null ? 0 : this
						.getTipoSoglieLowerBound().hashCode());
		result = 37
				* result
				+ (getTipoSoglieUpperBound() == null ? 0 : this
						.getTipoSoglieUpperBound().hashCode());
		result = 37
				* result
				+ (getPositionLat() == null ? 0 : this.getPositionLat()
						.hashCode());
		result = 37
				* result
				+ (getPositionLng() == null ? 0 : this.getPositionLng()
						.hashCode());
		result = 37 * result
				+ (getPath() == null ? 0 : this.getPath().hashCode());
		return result;
	}

}