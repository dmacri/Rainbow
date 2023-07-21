package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserPassword entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_password", catalog = "rainbow")
public class UserPassword extends AbstractUserPassword implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserPassword() {
	}

	/** full constructor */
	public UserPassword(User user, String value, Timestamp date) {
		super(user, value, date);
	}

}
