package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MwSensoreLastUpdates entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mw_sensore_last_updates", catalog = "rainbow")
public class MwSensoreLastUpdates extends AbstractMwSensoreLastUpdates
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public MwSensoreLastUpdates() {
	}

	/** minimal constructor */
	public MwSensoreLastUpdates(Integer sensoreId, Double lastValue) {
		super(sensoreId, lastValue);
	}

	/** full constructor */
	public MwSensoreLastUpdates(Integer sensoreId, Timestamp lastTimestamp,
			Double lastValue) {
		super(sensoreId, lastTimestamp, lastValue);
	}

}
