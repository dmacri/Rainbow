package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Location entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "location", catalog = "rainbow")
public class Location extends AbstractLocation implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Location() {
	}

	/** minimal constructor */
	public Location(String name, String province) {
		super(name, province);
	}

	/** full constructor */
	public Location(String name, String province, Set<Node> nodes) {
		super(name, province, nodes);
	}

}
