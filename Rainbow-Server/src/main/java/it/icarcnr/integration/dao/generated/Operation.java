package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Operation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "operation", catalog = "rainbow")
public class Operation extends AbstractOperation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Operation() {
	}

	/** minimal constructor */
	public Operation(String name) {
		super(name);
	}

	/** full constructor */
	public Operation(String name, Set<ServiceOperation> serviceOperations) {
		super(name, serviceOperations);
	}

}
