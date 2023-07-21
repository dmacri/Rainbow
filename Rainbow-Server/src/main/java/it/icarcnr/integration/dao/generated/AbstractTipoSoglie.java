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
 * AbstractTipoSoglie entity provides the base persistence definition of the
 * TipoSoglie entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTipoSoglie implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descrizione;
	private Double lowerBound;
	private Double upperBound;
	private Integer livello;
	private String pathIcon;
	private Set<SensoreSoglie> sensoreSoglies = new HashSet<SensoreSoglie>(0);

	// Constructors

	/** default constructor */
	public AbstractTipoSoglie() {
	}

	/** minimal constructor */
	public AbstractTipoSoglie(String descrizione, Double lowerBound,
			Double upperBound, Integer livello) {
		this.descrizione = descrizione;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.livello = livello;
	}

	/** full constructor */
	public AbstractTipoSoglie(String descrizione, Double lowerBound,
			Double upperBound, Integer livello, String pathIcon,
			Set<SensoreSoglie> sensoreSoglies) {
		this.descrizione = descrizione;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.livello = livello;
		this.pathIcon = pathIcon;
		this.sensoreSoglies = sensoreSoglies;
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

	@Column(name = "descrizione", nullable = false, length = 45)
	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Column(name = "lower_bound", nullable = false, precision = 22, scale = 0)
	public Double getLowerBound() {
		return this.lowerBound;
	}

	public void setLowerBound(Double lowerBound) {
		this.lowerBound = lowerBound;
	}

	@Column(name = "upper_bound", nullable = false, precision = 22, scale = 0)
	public Double getUpperBound() {
		return this.upperBound;
	}

	public void setUpperBound(Double upperBound) {
		this.upperBound = upperBound;
	}

	@Column(name = "livello", nullable = false)
	public Integer getLivello() {
		return this.livello;
	}

	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	@Column(name = "path_icon", length = 100)
	public String getPathIcon() {
		return this.pathIcon;
	}

	public void setPathIcon(String pathIcon) {
		this.pathIcon = pathIcon;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoSoglie")
	public Set<SensoreSoglie> getSensoreSoglies() {
		return this.sensoreSoglies;
	}

	public void setSensoreSoglies(Set<SensoreSoglie> sensoreSoglies) {
		this.sensoreSoglies = sensoreSoglies;
	}

}