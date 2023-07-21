package it.icarcnr.integration.dao.generated;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "rainbow")
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String name, String surname, String companyId, Boolean enabled,
			String username, String password) {
		super(name, surname, companyId, enabled, username, password);
	}

	/** full constructor */
	public User(Group group, String name, String surname, String companyId,
			Boolean enabled, String username, String password, Date expire,
			Set<UserPassword> userPasswords) {
		super(group, name, surname, companyId, enabled, username, password,
				expire, userPasswords);
	}

}
