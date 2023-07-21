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
 * AbstractTipoSensore entity provides the base persistence definition of the
 * TipoSensore entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTipoSensore implements java.io.Serializable {

	// Fields

	private Integer id;
	private String description;
	private String unitaMis;
	private Double min;
	private Double max;
	private String name;
	private Set<Sensore> sensores = new HashSet<Sensore>(0);

	// Constructors

	/** default constructor */
	public AbstractTipoSensore() {
	}

	/** minimal constructor */
	public AbstractTipoSensore(String description, String unitaMis, Double min,
			Double max, String name) {
		this.description = description;
		this.unitaMis = unitaMis;
		this.min = min;
		this.max = max;
		this.name = name;
	}

	/** full constructor */
	public AbstractTipoSensore(String description, String unitaMis, Double min,
			Double max, String name, Set<Sensore> sensores) {
		this.description = description;
		this.unitaMis = unitaMis;
		this.min = min;
		this.max = max;
		this.name = name;
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

	@Column(name = "unita_mis", nullable = false, length = 45)
	public String getUnitaMis() {
		return this.unitaMis;
	}

	public void setUnitaMis(String unitaMis) {
		this.unitaMis = unitaMis;
	}

	@Column(name = "min", nullable = false, precision = 22, scale = 0)
	public Double getMin() {
		return this.min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	@Column(name = "max", nullable = false, precision = 22, scale = 0)
	public Double getMax() {
		return this.max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	@Column(name = "name", unique = true, nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoSensore")
	public Set<Sensore> getSensores() {
		return this.sensores;
	}

	public void setSensores(Set<Sensore> sensores) {
		this.sensores = sensores;
	}

}