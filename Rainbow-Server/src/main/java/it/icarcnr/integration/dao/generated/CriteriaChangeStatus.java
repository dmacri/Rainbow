package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CriteriaChangeStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "criteria_change_status", catalog = "rainbow")
public class CriteriaChangeStatus extends AbstractCriteriaChangeStatus
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CriteriaChangeStatus() {
	}

	/** minimal constructor */
	public CriteriaChangeStatus(ServiceOperation serviceOperation,
			String statusFrom, String statusTo, Double valueAfter) {
		super(serviceOperation, statusFrom, statusTo, valueAfter);
	}

	/** full constructor */
	public CriteriaChangeStatus(ServiceOperation serviceOperation,
			String statusFrom, String statusTo, Timestamp date,
			Double valueAfter, Timestamp dateDetection) {
		super(serviceOperation, statusFrom, statusTo, date, valueAfter,
				dateDetection);
	}

}
