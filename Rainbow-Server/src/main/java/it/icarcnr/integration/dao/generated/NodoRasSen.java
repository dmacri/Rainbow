package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * NodoRasSen entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nodo_ras_sen", catalog = "rainbow")
public class NodoRasSen extends AbstractNodoRasSen implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public NodoRasSen() {
	}

	/** full constructor */
	public NodoRasSen(NodoRasSenId id, NodeSen nodeSen, NodeRas nodeRas) {
		super(id, nodeSen, nodeRas);
	}

}
