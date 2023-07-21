package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Node entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "node", catalog = "rainbow")
public class Node extends AbstractNode implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Node() {
	}

	/** minimal constructor */
	public Node(Type type, Network network, Location location, String name,
			String description) {
		super(type, network, location, name, description);
	}

	/** full constructor */
	public Node(Type type, Network network, Location location, String name,
			String description, Set<Service> servicesForIdNode,
			Set<ServiceNodeSource> serviceNodeSources,
			Set<NodeRelation> nodeRelationsForIdParent,
			Set<NodeRelation> nodeRelationsForIdChild,
			Set<Service> servicesForIdReceiver) {
		super(type, network, location, name, description, servicesForIdNode,
				serviceNodeSources, nodeRelationsForIdParent,
				nodeRelationsForIdChild, servicesForIdReceiver);
	}

}
