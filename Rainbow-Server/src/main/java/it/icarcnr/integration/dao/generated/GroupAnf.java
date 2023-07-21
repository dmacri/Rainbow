package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * GroupAnf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "group_anf", catalog = "rainbow")
public class GroupAnf extends AbstractGroupAnf implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public GroupAnf() {
	}

	/** minimal constructor */
	public GroupAnf(Group group, Boolean read, Boolean write) {
		super(group, read, write);
	}

	/** full constructor */
	public GroupAnf(ActionNetworkFunction actionNetworkFunction, Group group,
			Boolean read, Boolean write) {
		super(actionNetworkFunction, group, read, write);
	}

}
