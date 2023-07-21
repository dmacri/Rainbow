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
 * AbstractNodeRas entity provides the base persistence definition of the
 * NodeRas entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractNodeRas implements java.io.Serializable {

	// Fields

	private Integer id;
	private String description;
	private String name;
	private Set<NodoRasSen> nodoRasSens = new HashSet<NodoRasSen>(0);
	private Set<LocationGeoRas> locationGeoRases = new HashSet<LocationGeoRas>(
			0);

	// Constructors

	/** default constructor */
	public AbstractNodeRas() {
	}

	/** minimal constructor */
	public AbstractNodeRas(String description, String name) {
		this.description = description;
		this.name = name;
	}

	/** full constructor */
	public AbstractNodeRas(String description, String name,
			Set<NodoRasSen> nodoRasSens, Set<LocationGeoRas> locationGeoRases) {
		this.description = description;
		this.name = name;
		this.nodoRasSens = nodoRasSens;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nodeRas")
	public Set<NodoRasSen> getNodoRasSens() {
		return this.nodoRasSens;
	}

	public void setNodoRasSens(Set<NodoRasSen> nodoRasSens) {
		this.nodoRasSens = nodoRasSens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nodeRas")
	public Set<LocationGeoRas> getLocationGeoRases() {
		return this.locationGeoRases;
	}

	public void setLocationGeoRases(Set<LocationGeoRas> locationGeoRases) {
		this.locationGeoRases = locationGeoRases;
	}

}