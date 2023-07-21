package it.icarcnr.integration.dao.servicestatus.bean;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CriteriaSensorValue {
	
	private Integer idSensore;
	private Date timestamp;
	private Double value;
	private Integer id;
	private Integer samplingPeriod;
	private Double maxValue;
	private Double minValue;
	private String name;
	private String nodeSensor;
	private String sensor;
    private Double lat;
	private Double lng;


	public CriteriaSensorValue(Integer idSensore, Date timpestamp,
			Double value, Integer id, String name,Double lat,Double lng) {
		this.idSensore = idSensore;
		this.timestamp = timpestamp;
		this.value = value;
		this.id = id;
		this.name = name;
		this.lat=lat;
		this.lng=lng;
	}
	public Integer getIdSensore() {
		return idSensore;
	}
	public void setIdSensore(Integer idSensore) {
		this.idSensore = idSensore;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
//		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//		String alfa=isoFormat.format(timestamp);
//	    isoFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
//	    
//	    Date date=timestamp;
//		try {
//			date = isoFormat.parse(alfa);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		this.timestamp = date;
		this.timestamp = timestamp;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNodeSensor() {
		return nodeSensor;
	}
	public void setNodeSensor(String nodeSensor) {
		this.nodeSensor = nodeSensor;
	}
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}

}

