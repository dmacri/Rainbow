package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Utility entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "utility", catalog = "rainbow")
public class Utility extends AbstractUtility implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Utility() {
	}

	/** minimal constructor */
	public Utility(UtilityGroup utilityGroup, Network network, String script,
			String description) {
		super(utilityGroup, network, script, description);
	}

	/** full constructor */
	public Utility(UtilityGroup utilityGroup, Network network, String script,
			String description, Set<UtilityAnf> utilityAnfs,
			Set<Cronjob> cronjobs) {
		super(utilityGroup, network, script, description, utilityAnfs, cronjobs);
	}

}
