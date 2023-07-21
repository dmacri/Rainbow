package it.icarcnr.integration.dao.generated;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * AbstractSensore entity provides the base persistence definition of the
 * Sensore entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSensore implements java.io.Serializable {

	// Fields

	private Integer id;
	private TipoSensore tipoSensore;
	private NodeSen nodeSen;
	private String description;
	private Set<SensoreSoglie> sensoreSoglies = new HashSet<SensoreSoglie>(0);

	// Constructors

	/** default constructor */
	public AbstractSensore() {
	}

	/** minimal constructor */
	public AbstractSensore(TipoSensore tipoSensore, NodeSen nodeSen,
			String description) {
		this.tipoSensore = tipoSensore;
		this.nodeSen = nodeSen;
		this.description = description;
	}

	/** full constructor */
	public AbstractSensore(TipoSensore tipoSensore, NodeSen nodeSen,
			String description, Set<SensoreSoglie> sensoreSoglies) {
		this.tipoSensore = tipoSensore;
		this.nodeSen = nodeSen;
		this.description = description;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo", nullable = false)
	public TipoSensore getTipoSensore() {
		return this.tipoSensore;
	}

	public void setTipoSensore(TipoSensore tipoSensore) {
		this.tipoSensore = tipoSensore;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nodo_s", nullable = false)
	public NodeSen getNodeSen() {
		return this.nodeSen;
	}

	public void setNodeSen(NodeSen nodeSen) {
		this.nodeSen = nodeSen;
	}

	@Column(name = "description", nullable = false, length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sensore")
	public Set<SensoreSoglie> getSensoreSoglies() {
		return this.sensoreSoglies;
	}

	public void setSensoreSoglies(Set<SensoreSoglie> sensoreSoglies) {
		this.sensoreSoglies = sensoreSoglies;
	}

}