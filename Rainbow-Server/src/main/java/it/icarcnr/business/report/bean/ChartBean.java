package it.icarcnr.business.report.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ChartBean {
	
	public class ServiceValue{
		private Double value;
		private Double valueCheckMajor;
		private Double valueCheckCritical;
		private Double valueCheckSecondaryMajor;
		private Double valueCheckSecondaryCritical;
		private Date date;
		
		private String name;
		
			
		
		public Double getValue() {
			return value;
		}
		public void setValue(Double value) {
			this.value = value;
		}
		public Double getValueCheckMajor() {
			return valueCheckMajor;
		}
		public void setValueCheckMajor(Double valueCheckMajor) {
			this.valueCheckMajor = valueCheckMajor;
		}
		public Double getValueCheckCritical() {
			return valueCheckCritical;
		}
		public void setValueCheckCritical(Double valueCheckCritical) {
			this.valueCheckCritical = valueCheckCritical;
		}
		public Double getValueCheckSecondaryMajor() {
			return valueCheckSecondaryMajor;
		}
		public void setValueCheckSecondaryMajor(Double valueCheckSecondaryMajor) {
			this.valueCheckSecondaryMajor = valueCheckSecondaryMajor;
		}
		public Double getValueCheckSecondaryCritical() {
			return valueCheckSecondaryCritical;
		}
		public void setValueCheckSecondaryCritical(Double valueCheckSecondaryCritical) {
			this.valueCheckSecondaryCritical = valueCheckSecondaryCritical;
		}

		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
//		public String getNode() {
//			return node;
//		}
//		public void setNode(String node) {
//			this.node = node;
//		}
//		public String getSourceNode() {
//			return sourceNode;
//		}
//		public void setSourceNode(String sourceNode) {
//			this.sourceNode = sourceNode;
//		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
		
	}
	
	private String nodeByIdNode;
	private String nodeByIdNodeReceiver;
	private String source;
	private String service;
	private Integer samplingPeriod;
	private Double maxValue;
	private Double minValue;
	private Timestamp startDate;
	private Timestamp endDate;
	private String name;

	
	
	List<ServiceValue> serviceValueList;
	
	
	public String getNodeByIdNode() {
		return nodeByIdNode;
	}
	public void setNodeByIdNode(String nodeByIdNode) {
		this.nodeByIdNode = nodeByIdNode;
	}
	public String getNodeByIdNodeReceiver() {
		return nodeByIdNodeReceiver;
	}
	public void setNodeByIdNodeReceiver(String nodeByIdNodeReceiver) {
		this.nodeByIdNodeReceiver = nodeByIdNodeReceiver;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Integer getSamplingPeriod() {
		return samplingPeriod;
	}
	public void setSamplingPeriod(Integer samplingPeriod) {
		this.samplingPeriod = samplingPeriod;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public List<ServiceValue> getServiceValueList() {
		return serviceValueList;
	}
	public void setServiceValueList(List<ServiceValue> serviceValueList) {
		this.serviceValueList = serviceValueList;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
