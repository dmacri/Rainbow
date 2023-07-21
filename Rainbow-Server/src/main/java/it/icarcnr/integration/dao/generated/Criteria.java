package it.icarcnr.integration.dao.generated;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Criteria entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "criteria", catalog = "rainbow")
public class Criteria extends AbstractCriteria implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Criteria() {
	}

	/** minimal constructor */
	public Criteria(ServiceOperation serviceOperation, Double value,
			Boolean monday, Boolean tuesday, Boolean wednesday,
			Boolean thursday, Boolean friday, Boolean saturday, Boolean sunday,
			String description, String status, String typeCheck,
			Boolean enabled, Boolean suspended, String calculation) {
		super(serviceOperation, value, monday, tuesday, wednesday, thursday,
				friday, saturday, sunday, description, status, typeCheck,
				enabled, suspended, calculation);
	}

	/** full constructor */
	public Criteria(ServiceOperation serviceOperation, Double value,
			Boolean monday, Boolean tuesday, Boolean wednesday,
			Boolean thursday, Boolean friday, Boolean saturday, Boolean sunday,
			Time startTime, Time endTime, Double valueCheckMajor,
			String description, String status, Timestamp date,
			String typeCheck, Double valueCheckCritical, Boolean enabled,
			Date startDate, Date endDate, Double valueCheckSecondaryMajor,
			Double valueCheckSecondaryCritical, Boolean suspended,
			String calculation) {
		super(serviceOperation, value, monday, tuesday, wednesday, thursday,
				friday, saturday, sunday, startTime, endTime, valueCheckMajor,
				description, status, date, typeCheck, valueCheckCritical,
				enabled, startDate, endDate, valueCheckSecondaryMajor,
				valueCheckSecondaryCritical, suspended, calculation);
	}

}
