package it.icarcnr.integration.dao.generated;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ServiceOperation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "service_operation", catalog = "rainbow")
public class ServiceOperation extends AbstractServiceOperation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ServiceOperation() {
	}

	/** minimal constructor */
	public ServiceOperation(Service service, Operation operation) {
		super(service, operation);
	}

	/** full constructor */
	public ServiceOperation(Service service, Operation operation,
			Set<CalculationParameter> calculationParameters,
			Set<CriteriaChangeStatus> criteriaChangeStatuses,
			Set<Criteria> criterias) {
		super(service, operation, calculationParameters,
				criteriaChangeStatuses, criterias);
	}

}
