package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * NodeRas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "node_ras", catalog = "rainbow")
public class NodeRas extends AbstractNodeRas implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public NodeRas() {
	}

	/** minimal constructor */
	public NodeRas(String description, String name) {
		super(description, name);
	}

	/** full constructor */
	public NodeRas(String description, String name,
			Set<NodoRasSen> nodoRasSens, Set<LocationGeoRas> locationGeoRases) {
		super(description, name, nodoRasSens, locationGeoRases);
	}

}
