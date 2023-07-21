package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TipoSoglie entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipo_soglie", catalog = "rainbow")
public class TipoSoglie extends AbstractTipoSoglie implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TipoSoglie() {
	}

	/** minimal constructor */
	public TipoSoglie(String descrizione, Double lowerBound, Double upperBound,
			Integer livello) {
		super(descrizione, lowerBound, upperBound, livello);
	}

	/** full constructor */
	public TipoSoglie(String descrizione, Double lowerBound, Double upperBound,
			Integer livello, String pathIcon, Set<SensoreSoglie> sensoreSoglies) {
		super(descrizione, lowerBound, upperBound, livello, pathIcon,
				sensoreSoglies);
	}

}
