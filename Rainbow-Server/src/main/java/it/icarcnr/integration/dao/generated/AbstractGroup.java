package it.icarcnr.integration.dao.generated;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * AbstractGroup entity provides the base persistence definition of the Group
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<GroupAnf> groupAnfs = new HashSet<GroupAnf>(0);
	private Set<User> users = new HashSet<User>(0);

	// Constructors

	/** default constructor */
	public AbstractGroup() {
	}

	/** minimal constructor */
	public AbstractGroup(String name) {
		this.name = name;
	}

	/** full constructor */
	public AbstractGroup(String name, Set<GroupAnf> groupAnfs, Set<User> users) {
		this.name = name;
		this.groupAnfs = groupAnfs;
		this.users = users;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	public Set<GroupAnf> getGroupAnfs() {
		return this.groupAnfs;
	}

	public void setGroupAnfs(Set<GroupAnf> groupAnfs) {
		this.groupAnfs = groupAnfs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}