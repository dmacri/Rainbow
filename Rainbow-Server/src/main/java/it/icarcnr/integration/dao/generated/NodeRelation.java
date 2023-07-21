package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * NodeRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "node_relation", catalog = "rainbow")
public class NodeRelation extends AbstractNodeRelation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public NodeRelation() {
	}

	/** full constructor */
	public NodeRelation(Node nodeByIdChild, Node nodeByIdParent) {
		super(nodeByIdChild, nodeByIdParent);
	}

}
