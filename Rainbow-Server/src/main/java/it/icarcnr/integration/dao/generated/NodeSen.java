package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * NodeSen entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "node_sen", catalog = "rainbow")
public class NodeSen extends AbstractNodeSen implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public NodeSen() {
	}

	/** minimal constructor */
	public NodeSen(String description, String name) {
		super(description, name);
	}

	/** full constructor */
	public NodeSen(String description, String name, String serial,
			Set<NodoRasSen> nodoRasSens, Set<LocationGeoSen> locationGeoSens,
			Set<Sensore> sensores) {
		super(description, name, serial, nodoRasSens, locationGeoSens, sensores);
	}

}
