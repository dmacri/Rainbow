package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Request entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "request", catalog = "rainbow")
public class Request extends AbstractRequest implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Request() {
	}

	/** minimal constructor */
	public Request(String name, String description, String descriptionExtended,
			Integer percentageGapMajor, Integer percentageGapCritical) {
		super(name, description, descriptionExtended, percentageGapMajor,
				percentageGapCritical);
	}

	/** full constructor */
	public Request(String name, String description, String descriptionExtended,
			Integer percentageGapMajor, Integer percentageGapCritical,
			Set<Service> services) {
		super(name, description, descriptionExtended, percentageGapMajor,
				percentageGapCritical, services);
	}

}
