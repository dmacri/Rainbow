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
 * AbstractPosition entity provides the base persistence definition of the
 * Position entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractPosition implements java.io.Serializable {

	// Fields

	private Integer id;
	private Double lat;
	private Double lng;
	private String indirizzo;
	private Set<LocationGeoSen> locationGeoSens = new HashSet<LocationGeoSen>(0);
	private Set<LocationGeoRas> locationGeoRases = new HashSet<LocationGeoRas>(
			0);

	// Constructors

	/** default constructor */
	public AbstractPosition() {
	}

	/** minimal constructor */
	public AbstractPosition(Double lat, Double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	/** full constructor */
	public AbstractPosition(Double lat, Double lng, String indirizzo,
			Set<LocationGeoSen> locationGeoSens,
			Set<LocationGeoRas> locationGeoRases) {
		this.lat = lat;
		this.lng = lng;
		this.indirizzo = indirizzo;
		this.locationGeoSens = locationGeoSens;
		this.locationGeoRases = locationGeoRases;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "lat", nullable = false, precision = 22, scale = 0)
	public Double getLat() {
		return this.lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Column(name = "lng", nullable = false, precision = 22, scale = 0)
	public Double getLng() {
		return this.lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Column(name = "indirizzo", length = 45)
	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "position")
	public Set<LocationGeoSen> getLocationGeoSens() {
		return this.locationGeoSens;
	}

	public void setLocationGeoSens(Set<LocationGeoSen> locationGeoSens) {
		this.locationGeoSens = locationGeoSens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "position")
	public Set<LocationGeoRas> getLocationGeoRases() {
		return this.locationGeoRases;
	}

	public void setLocationGeoRases(Set<LocationGeoRas> locationGeoRases) {
		this.locationGeoRases = locationGeoRases;
	}

}