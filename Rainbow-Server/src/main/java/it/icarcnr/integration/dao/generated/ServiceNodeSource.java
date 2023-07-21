package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ServiceNodeSource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "service_node_source", catalog = "rainbow")
public class ServiceNodeSource extends AbstractServiceNodeSource implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ServiceNodeSource() {
	}

	/** full constructor */
	public ServiceNodeSource(Service service, Node node) {
		super(service, node);
	}

}
