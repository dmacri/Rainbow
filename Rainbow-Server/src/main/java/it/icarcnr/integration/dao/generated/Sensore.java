package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Sensore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sensore", catalog = "rainbow")
public class Sensore extends AbstractSensore implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Sensore() {
	}

	/** minimal constructor */
	public Sensore(TipoSensore tipoSensore, NodeSen nodeSen, String description) {
		super(tipoSensore, nodeSen, description);
	}

	/** full constructor */
	public Sensore(TipoSensore tipoSensore, NodeSen nodeSen,
			String description, Set<SensoreSoglie> sensoreSoglies) {
		super(tipoSensore, nodeSen, description, sensoreSoglies);
	}

}
