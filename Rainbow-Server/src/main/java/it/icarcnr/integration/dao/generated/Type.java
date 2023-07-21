package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Type entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "type", catalog = "rainbow")
public class Type extends AbstractType implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Type() {
	}

	/** minimal constructor */
	public Type(String name, String description) {
		super(name, description);
	}

	/** full constructor */
	public Type(Network network, String name, String description,
			Set<Node> nodes) {
		super(network, name, description, nodes);
	}

}
