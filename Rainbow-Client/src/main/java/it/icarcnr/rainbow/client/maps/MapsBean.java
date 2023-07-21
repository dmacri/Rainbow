package it.icarcnr.rainbow.client.maps;

import java.util.Date;

public class MapsBean {
		
	  private String idSensore;
	  private String idNodeSensore;
	  private String  descrizioneNode;
	  private String dateSampling;
	  private String nameDescription;
	  private String value;
	  private String livello;
	  private String lowerBound;
	  private String upperBound;
	  private String Lat;
	  private String Lng;
	  private String colorLevel;
			  
	public MapsBean() {

	}

	public MapsBean(String idSensore, String idNodeSensore,
			String descrizioneNode, String dateSampling,
			String nameDescription, String value, String livello,
			String lowerBound, String upperBound, String lat, String lng,
			String colorLevel) {
		super();
		this.idSensore = idSensore;
		this.idNodeSensore = idNodeSensore;
		this.descrizioneNode = descrizioneNode;
		this.dateSampling = dateSampling;
		this.nameDescription = nameDescription;
		this.value = value;
		this.livello = livello;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		Lat = lat;
		Lng = lng;
		this.colorLevel = colorLevel;
	}


	public String getIdSensore() {
		return idSensore;
	}


	public void setIdSensore(String idSensore) {
		this.idSensore = idSensore;
	}


	public String getIdNodeSensore() {
		return idNodeSensore;
	}


	public void setIdNodeSensore(String idNodeSensore) {
		this.idNodeSensore = idNodeSensore;
	}


	public String getDescrizioneNode() {
		return descrizioneNode;
	}


	public void setDescrizioneNode(String descrizioneNode) {
		this.descrizioneNode = descrizioneNode;
	}


	public String getDateSampling() {
		return dateSampling;
	}


	public void setDateSampling(String dateSampling) {
		this.dateSampling = dateSampling;
	}


	public String getNameDescription() {
		return nameDescription;
	}


	public void setNameDescription(String nameDescription) {
		this.nameDescription = nameDescription;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getLivello() {
		return livello;
	}


	public void setLivello(String livello) {
		this.livello = livello;
	}


	public String getLowerBound() {
		return lowerBound;
	}


	public void setLowerBound(String lowerBound) {
		this.lowerBound = lowerBound;
	}


	public String getUpperBound() {
		return upperBound;
	}


	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}


	public String getLat() {
		return Lat;
	}


	public void setLat(String lat) {
		Lat = lat;
	}


	public String getLng() {
		return Lng;
	}


	public void setLng(String lng) {
		Lng = lng;
	}


	public String getColorLevel() {
		return colorLevel;
	}


	public void setColorLevel(String colorLevel) {
		this.colorLevel = colorLevel;
	}
	
	  
	  
	  
	  
	  
}
