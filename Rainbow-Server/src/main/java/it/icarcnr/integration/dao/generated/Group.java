package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Group entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "group", catalog = "rainbow")
public class Group extends AbstractGroup implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Group() {
	}

	/** minimal constructor */
	public Group(String name) {
		super(name);
	}

	/** full constructor */
	public Group(String name, Set<GroupAnf> groupAnfs, Set<User> users) {
		super(name, groupAnfs, users);
	}

}
