package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ActionNetworkFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "action_network_function", catalog = "rainbow")
public class ActionNetworkFunction extends AbstractActionNetworkFunction
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ActionNetworkFunction() {
	}

	/** minimal constructor */
	public ActionNetworkFunction(NetworkFunction networkFunction, Action action) {
		super(networkFunction, action);
	}

	/** full constructor */
	public ActionNetworkFunction(NetworkFunction networkFunction,
			Action action, Set<GroupAnf> groupAnfs, Set<UtilityAnf> utilityAnfs) {
		super(networkFunction, action, groupAnfs, utilityAnfs);
	}

}
