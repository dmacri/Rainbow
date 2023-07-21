package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Valori entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "valori", catalog = "rainbow")
public class Valori extends AbstractValori implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Valori() {
	}

	/** full constructor */
	public Valori(ValoriId id, Double value) {
		super(id, value);
	}

}
