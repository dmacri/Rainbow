package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Function entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "function", catalog = "rainbow")
public class Function extends AbstractFunction implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Function() {
	}

	/** minimal constructor */
	public Function(String name) {
		super(name);
	}

	/** full constructor */
	public Function(String name, Set<NetworkFunction> networkFunctions,
			Set<Service> services) {
		super(name, networkFunctions, services);
	}

}
