package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Holtwinters entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "holtwinters", catalog = "rainbow")
public class Holtwinters extends AbstractHoltwinters implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Holtwinters() {
	}

	/** minimal constructor */
	public Holtwinters(HoltwintersId id) {
		super(id);
	}

	/** full constructor */
	public Holtwinters(HoltwintersId id, Double sampleValue,
			Double cleanedValue, Double estimatedValue, Double at, Double ft,
			Double st, Double cleanSigmat, Double lowerBound,
			Double upperBound, Double bandSigmat) {
		super(id, sampleValue, cleanedValue, estimatedValue, at, ft, st,
				cleanSigmat, lowerBound, upperBound, bandSigmat);
	}

}
