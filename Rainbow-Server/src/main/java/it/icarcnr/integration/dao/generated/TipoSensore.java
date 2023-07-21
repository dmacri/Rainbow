package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TipoSensore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipo_sensore", catalog = "rainbow")
public class TipoSensore extends AbstractTipoSensore implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TipoSensore() {
	}

	/** minimal constructor */
	public TipoSensore(String description, String unitaMis, Double min,
			Double max, String name) {
		super(description, unitaMis, min, max, name);
	}

	/** full constructor */
	public TipoSensore(String description, String unitaMis, Double min,
			Double max, String name, Set<Sensore> sensores) {
		super(description, unitaMis, min, max, name, sensores);
	}

}
