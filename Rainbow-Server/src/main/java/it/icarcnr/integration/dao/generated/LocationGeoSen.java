package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * LocationGeoSen entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "location_geo_sen", catalog = "rainbow")
public class LocationGeoSen extends AbstractLocationGeoSen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public LocationGeoSen() {
	}

	/** full constructor */
	public LocationGeoSen(Position position, NodeSen nodeSen,
			Timestamp timestamp) {
		super(position, nodeSen, timestamp);
	}

}
