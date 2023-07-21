package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * LocationGeoRas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "location_geo_ras", catalog = "rainbow")
public class LocationGeoRas extends AbstractLocationGeoRas implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public LocationGeoRas() {
	}

	/** full constructor */
	public LocationGeoRas(NodeRas nodeRas, Position position,
			Timestamp timestamp) {
		super(nodeRas, position, timestamp);
	}

}
