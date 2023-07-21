package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Service entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "service", catalog = "rainbow")
public class Service extends AbstractService implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Service() {
	}

	/** minimal constructor */
	public Service(String name, String description, String file, Double value,
			Integer samplingPeriod) {
		super(name, description, file, value, samplingPeriod);
	}

	/** full constructor */
	public Service(Node nodeByIdReceiver, Function function, Request request,
			Node nodeByIdNode, String name, String description, String file,
			Double value, Timestamp date, Integer samplingPeriod, String log,
			Integer idUtility, Short startMinute,
			Set<ServiceOperation> serviceOperations,
			Set<ServiceNodeSource> serviceNodeSources) {
		super(nodeByIdReceiver, function, request, nodeByIdNode, name,
				description, file, value, date, samplingPeriod, log, idUtility,
				startMinute, serviceOperations, serviceNodeSources);
	}

}
