package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "position", catalog = "rainbow")
public class Position extends AbstractPosition implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Position() {
	}

	/** minimal constructor */
	public Position(Double lat, Double lng) {
		super(lat, lng);
	}

	/** full constructor */
	public Position(Double lat, Double lng, String indirizzo,
			Set<LocationGeoSen> locationGeoSens,
			Set<LocationGeoRas> locationGeoRases) {
		super(lat, lng, indirizzo, locationGeoSens, locationGeoRases);
	}

}
