package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CriteriaHistoryPk3 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "criteria_history_pk3", catalog = "rainbow")
public class CriteriaHistoryPk3 extends AbstractCriteriaHistoryPk3 implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CriteriaHistoryPk3() {
	}

	/** minimal constructor */
	public CriteriaHistoryPk3(CriteriaHistoryPk3Id id, String status,
			Double value) {
		super(id, status, value);
	}

	/** full constructor */
	public CriteriaHistoryPk3(CriteriaHistoryPk3Id id, String status,
			Double value, Double valueCheckMajor, Double valueCheckCritical,
			Double valueCheckSecondaryMajor, Double valueCheckSecondaryCritical) {
		super(id, status, value, valueCheckMajor, valueCheckCritical,
				valueCheckSecondaryMajor, valueCheckSecondaryCritical);
	}

}
