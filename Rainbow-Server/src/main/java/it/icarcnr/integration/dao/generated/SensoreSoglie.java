package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SensoreSoglie entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sensore_soglie", catalog = "rainbow")
public class SensoreSoglie extends AbstractSensoreSoglie implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SensoreSoglie() {
	}

	/** full constructor */
	public SensoreSoglie(TipoSoglie tipoSoglie, Sensore sensore) {
		super(tipoSoglie, sensore);
	}

}
