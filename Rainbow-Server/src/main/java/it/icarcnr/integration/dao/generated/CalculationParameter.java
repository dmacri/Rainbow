package it.icarcnr.integration.dao.generated;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CalculationParameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "calculation_parameter", catalog = "rainbow")
public class CalculationParameter extends AbstractCalculationParameter
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CalculationParameter() {
	}

	/** full constructor */
	public CalculationParameter(ServiceOperation serviceOperation, String name,
			String value, String type) {
		super(serviceOperation, name, value, type);
	}

}
