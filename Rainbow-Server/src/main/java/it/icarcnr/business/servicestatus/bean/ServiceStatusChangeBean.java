package it.icarcnr.business.servicestatus.bean;

import java.io.Serializable;
import java.util.List;

public class ServiceStatusChangeBean implements Serializable {
	Integer serviceId;
	Integer networkId;
	String fieldSearch;
	String serviceDescription;
	String source;
	String reference;
	String nodeFrom;
	String nodeTo;
	String network;
	String serviceType;
	List<CriteriaStatusChangeSequenceBean> criteriaStatusChangeSequenceBeans;
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getNetworkId() {
		return networkId;
	}
	public void setNetworkId(Integer networkId) {
		this.networkId = networkId;
	}
	public String getFieldSearch() {
		return fieldSearch;
	}
	public void setFieldSearch(String fieldSearch) {
		this.fieldSearch = fieldSearch;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getNodeFrom() {
		return nodeFrom;
	}
	public void setNodeFrom(String nodeFrom) {
		this.nodeFrom = nodeFrom;
	}
	public String getNodeTo() {
		return nodeTo;
	}
	public void setNodeTo(String nodeTo) {
		this.nodeTo = nodeTo;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public List<CriteriaStatusChangeSequenceBean> getCriteriaStatusChangeSequenceBeans() {
		return criteriaStatusChangeSequenceBeans;
	}
	public void setCriteriaStatusChangeSequenceBeans(
			List<CriteriaStatusChangeSequenceBean> criteriaStatusChangeSequenceBeans) {
		this.criteriaStatusChangeSequenceBeans = criteriaStatusChangeSequenceBeans;
	}
	
}
