package it.icarcnr.integration.dao.generated;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractSensoreSoglie entity provides the base persistence definition of the
 * SensoreSoglie entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSensoreSoglie implements java.io.Serializable {

	// Fields

	private Integer id;
	private TipoSoglie tipoSoglie;
	private Sensore sensore;

	// Constructors

	/** default constructor */
	public AbstractSensoreSoglie() {
	}

	/** full constructor */
	public AbstractSensoreSoglie(TipoSoglie tipoSoglie, Sensore sensore) {
		this.tipoSoglie = tipoSoglie;
		this.sensore = sensore;
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
	@JoinColumn(name = "id_soglia", nullable = false)
	public TipoSoglie getTipoSoglie() {
		return this.tipoSoglie;
	}

	public void setTipoSoglie(TipoSoglie tipoSoglie) {
		this.tipoSoglie = tipoSoglie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sensore", nullable = false)
	public Sensore getSensore() {
		return this.sensore;
	}

	public void setSensore(Sensore sensore) {
		this.sensore = sensore;
	}

}