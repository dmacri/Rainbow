package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UtilityAnf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "utility_anf", catalog = "rainbow")
public class UtilityAnf extends AbstractUtilityAnf implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UtilityAnf() {
	}

	/** full constructor */
	public UtilityAnf(Utility utility,
			ActionNetworkFunction actionNetworkFunction, Integer maxProcesses) {
		super(utility, actionNetworkFunction, maxProcesses);
	}

}
