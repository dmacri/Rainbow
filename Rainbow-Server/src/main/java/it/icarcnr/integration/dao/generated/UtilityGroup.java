package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UtilityGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "utility_group", catalog = "rainbow")
public class UtilityGroup extends AbstractUtilityGroup implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UtilityGroup() {
	}

	/** minimal constructor */
	public UtilityGroup(Integer maxProcesses, String name) {
		super(maxProcesses, name);
	}

	/** full constructor */
	public UtilityGroup(Integer maxProcesses, String name,
			Set<Utility> utilities) {
		super(maxProcesses, name, utilities);
	}

}
