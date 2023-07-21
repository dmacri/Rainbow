package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Release entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "release", catalog = "rainbow")
public class Release extends AbstractRelease implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Release() {
	}

	/** full constructor */
	public Release(String version) {
		super(version);
	}

}
