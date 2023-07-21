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
 * AbstractNodeSen entity provides the base persistence definition of the
 * NodeSen entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractNodeSen implements java.io.Serializable {

	// Fields

	private Integer id;
	private String description;
	private String name;
	private String serial;
	private Set<NodoRasSen> nodoRasSens = new HashSet<NodoRasSen>(0);
	private Set<LocationGeoSen> locationGeoSens = new HashSet<LocationGeoSen>(0);
	private Set<Sensore> sensores = new HashSet<Sensore>(0);

	// Constructors

	/** default constructor */
	public AbstractNodeSen() {
	}

	/** minimal constructor */
	public AbstractNodeSen(String description, String name) {
		this.description = description;
		this.name = name;
	}

	/** full constructor */
	public AbstractNodeSen(String description, String name, String serial,
			Set<NodoRasSen> nodoRasSens, Set<LocationGeoSen> locationGeoSens,
			Set<Sensore> sensores) {
		this.description = description;
		this.name = name;
		this.serial = serial;
		this.nodoRasSens = nodoRasSens;
		this.locationGeoSens = locationGeoSens;
		this.sensores = sensores;
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

	@Column(name = "description", nullable = false, length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "name", unique = true, nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "serial", length = 15)
	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nodeSen")
	public Set<NodoRasSen> getNodoRasSens() {
		return this.nodoRasSens;
	}

	public void setNodoRasSens(Set<NodoRasSen> nodoRasSens) {
		this.nodoRasSens = nodoRasSens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nodeSen")
	public Set<LocationGeoSen> getLocationGeoSens() {
		return this.locationGeoSens;
	}

	public void setLocationGeoSens(Set<LocationGeoSen> locationGeoSens) {
		this.locationGeoSens = locationGeoSens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nodeSen")
	public Set<Sensore> getSensores() {
		return this.sensores;
	}

	public void setSensores(Set<Sensore> sensores) {
		this.sensores = sensores;
	}

}