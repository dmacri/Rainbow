package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * NetworkFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "network_function", catalog = "rainbow")
public class NetworkFunction extends AbstractNetworkFunction implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public NetworkFunction() {
	}

	/** minimal constructor */
	public NetworkFunction(Network network) {
		super(network);
	}

	/** full constructor */
	public NetworkFunction(Function function, Network network,
			Set<ActionNetworkFunction> actionNetworkFunctions) {
		super(function, network, actionNetworkFunctions);
	}

}
