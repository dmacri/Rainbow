package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Network entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "network", catalog = "rainbow")
public class Network extends AbstractNetwork implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Network() {
	}

	/** minimal constructor */
	public Network(String name, String description) {
		super(name, description);
	}

	/** full constructor */
	public Network(String name, String description, Set<Type> types,
			Set<NetworkFunction> networkFunctions, Set<Node> nodes,
			Set<Utility> utilities) {
		super(name, description, types, networkFunctions, nodes, utilities);
	}

}
