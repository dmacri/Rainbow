package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * AbstractUserPassword entity provides the base persistence definition of the
 * UserPassword entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUserPassword implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String value;
	private Timestamp date;

	// Constructors

	/** default constructor */
	public AbstractUserPassword() {
	}

	/** full constructor */
	public AbstractUserPassword(User user, String value, Timestamp date) {
		this.user = user;
		this.value = value;
		this.date = date;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_User", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "value", nullable = false)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "date", nullable = false, length = 19)
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}